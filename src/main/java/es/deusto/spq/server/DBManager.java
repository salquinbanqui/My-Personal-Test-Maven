package es.deusto.spq.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.deusto.spq.pojo.Usuario;

public class DBManager {

	private static DBManager instance = null;
	private PersistenceManagerFactory pmf = null;
	private static boolean inicializado = false;
	private static Connection conn;
	private  WebTarget webTarget;

	private DBManager() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	/**
	 * Obtencion de la instancia del DBManager, si no existe la crea y si existe la devuelve
	 * @return instancia de DBManager
	 */

	public static DBManager getInstance() {
		if (instance == null) {
		instance = new DBManager();
//			System.out.println("Nuevo DBManager");
		}

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
	
	/*public void agregarUsuarioGestionPelis(Usuario u) {
		PreparedStatement preparedStatement = null;

	        try {
	           
	        	//for (Usuario u : usuarios) {
	        		String query = " INSERT INTO USUARIO (USERNAME, EMAIL, PASSWORD, CARD, ADMIN)"
		                    + " VALUES (?, ?, ?, ?, ?)";

		            preparedStatement = conn.prepareStatement(query);

		            preparedStatement.setString(1, u.getNombreUsuario());
		            preparedStatement.setString(2, u.getEmail());
		            preparedStatement.setString(3, u.getPassword());
		            preparedStatement.setString(4, u.getTarjeta());
		            preparedStatement.setBoolean(5, u.isAdmin());
		            preparedStatement.execute();

		            System.out.println("Usuario agregado correctamente");
				//}
	        	

	        } catch (Exception e) {
	            System.out.println("Error agregando el usuario");
	            System.out.println(e);
	        }
	}
	*/
	
	public void inicializarDatos() {
		System.out.println(" * Inicializando base de datos");
		
		//USUARIOS...
		
		Usuario u1 = new Usuario("samuelkiwi", "samuel.martin@opendeusto.es", "password_samu", "XXXX-XXXX-XXXX-XXXX", false);   
		Usuario u2 = new Usuario("jotajota", "jose@opendeusto.es", "password_jose", "XXXX-XXXX-XXXX-XXXX", false);              
		Usuario u3 = new Usuario("markelinho", "mark@opendeusto.es", "password_mark", "XXXX-XXXX-XXXX-XXXX", false);            
		Usuario u4 = new Usuario("yonan99", "yonander@opendeusto.es", "password_yonan", "XXXX-XXXX-XXXX-XXXX", false);          
		Usuario u5 = new Usuario("nachete", "ignacio@opendeusto.es", "password_nacho", "XXXX-XXXX-XXXX-XXXX", false);           
		
		//PELICULAS...
		
		try {
			 store(u1);
			 store(u2);
			 store(u3);
			 store(u4);
			 store(u5);
			 
			// store(p1);
			// store(p2);
			// store(p3);
			// store(p4);
			// store(p5);
	 
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println(" $ Error inicializando los datos: " + ex.getMessage());
			ex.printStackTrace(); // Sensitive
		}
	}
	
	 @POST
	 @Path("/login")
	 public Response loginUsuario(Usuario usuario) {
		 PersistenceManager pm = pmf.getPersistenceManager();
    	 pm.getFetchPlan().setMaxFetchDepth(4);
 		 Transaction tx = pm.currentTransaction();
	     try
	     {
	         tx.begin();
	         System.out.println("Checking whether the user already exits or not: '{}'" + usuario.getNombreUsuario());
	         Usuario user = null;
	         try {
	             user = pm.getObjectById(Usuario.class, usuario.getNombreUsuario());
	         } catch (javax.jdo.JDOObjectNotFoundException jonfe) {
	             System.out.println("Exception launched: {}" + jonfe.getMessage());
	         }
	         System.out.println("User: {}" + user);
	         if (user != null) {
	             if(!user.getPassword().equals(usuario.getPassword())) {
	                 return Response.serverError().build();
	             }
	         } else {
	             return Response.serverError().build();
	         }
	         tx.commit();
	         return Response.ok().build();
	     }
	     finally
	     {
	         if (tx.isActive())
	         {
	             tx.rollback();
	         }

	     }
	 }
	
	public List<Usuario> getUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(5);
		Transaction tx = pm.currentTransaction();

		try {

			tx.begin();

			Extent<Usuario> extent = pm.getExtent(Usuario.class, true);

			for (Usuario usuario : extent) {
				usuarios.add(usuario);
			}

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the Categories: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return usuarios;
	}
//mirad este metodo ?¿

	
/*	
	public Response borrarUsuario(Usuario usuario) {
		WebTarget webTarget1 = webTarget.path("server/borrarUsuario");	
		Entity<Usuario> entity = Entity.entity(usuario, MediaType.APPLICATION_JSON);
		Response response = webTarget1.request().post(entity);
		return response;
	
	}
	*/
	
	
	public void borrarUsuario(String username) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Usuario.class.getName() + " WHERE nombreUsuario == '" + username + "'");
			System.out.println(" * '" + query.deletePersistentAll() + "' usuario borrado de la DB.");
			System.out.println(" * '" + username + "' *");
			tx.commit();
		} catch (Exception ex) {
			System.out.println(" $ Error querying a Reserva: " + ex.getMessage());
			ex.printStackTrace(); // Sensitive
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}
	}
	
	
	public void borrarUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();

		try {

			tx.begin();

			Extent<Usuario> extent = pm.getExtent(Usuario.class, true);

			for (Usuario usuario : extent) {
				pm.deletePersistent(usuario);
			}

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the Categories: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	
	
	
	
}
}