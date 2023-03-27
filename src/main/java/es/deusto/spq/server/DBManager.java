package es.deusto.spq.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.spq.pojo.Usuario;

public class DBManager {

	private static DBManager instance = null;
	private PersistenceManagerFactory pmf = null;
	private static boolean inicializado = false;
	private static Connection conn;

	private DBManager() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	/**
	 * Obtencion de la instancia del DBManager, si no existe la crea y si existe la devuelve
	 * @return instancia de DBManager
	 */

	public static DBManager getInstance() {
//		if (instance == null) {
		instance = new DBManager();
//			System.out.println("Nuevo DBManager");
//		}

		return instance;
	}

	public void storeObjectInDB(Object object) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();


		try {
			tx.begin();
			pm.makePersistent(object);
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error storing an object: " + ex.getMessage());
			System.out.println("Object:" + object);
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

	public void deleteObjectFromDB(Object object) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();

			pm.deletePersistent(object);

			tx.commit();
		} catch (Exception ex) {
			System.out.println(" $ Error deleting an object: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		}
	
	public Usuario getUsuario(String nombreUsuario) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		Usuario user = null;

		try {
			tx.begin();

			Query<?> query = pm.newQuery("SELECT FROM " + Usuario.class.getName() + " WHERE nombreUsuario == '" + nombreUsuario + "'");
			query.setUnique(true);
			user = (Usuario) query.execute();

			tx.commit();
		} catch (Exception ex) {
			System.out.println(" $ Error cogiendo el usuario de la BD: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return user;	
		}
	
	public void store(Usuario usuario) {
		DBManager.getInstance().storeObjectInDB(usuario);
	}

	public void delete(Usuario usuario) {
		DBManager.getInstance().deleteObjectFromDB(usuario);
	}
	
	public void agregarUsuarioGestionPelis(List<Usuario> usuarios) {
		PreparedStatement preparedStatement = null;

	        try {
	            
	        	for (Usuario u : usuarios) {
	        		String query = " INSERT INTO USUARIO (USERNAME, EMAIL, PASSWORD, CARD)"
		                    + " VALUES (?, ?, ?, ?)";

		            preparedStatement = conn.prepareStatement(query);

		            preparedStatement.setString(1, u.getNombreUsuario());
		            preparedStatement.setString(2, u.getEmail());
		            preparedStatement.setString(3, u.getPassword());
		            preparedStatement.setString(4, u.getTarjeta());
		            preparedStatement.execute();

		            System.out.println("Usuarios agregados correctamente");
				}
	        	

	        } catch (Exception e) {
	            System.out.println("Error agregando los usuarios");
	            System.out.println(e);
	        }
	}

}