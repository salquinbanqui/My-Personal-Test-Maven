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

import es.deusto.spq.pojo.Pelicula;
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
	
	
	public Pelicula getPelicula(String nombrePelicula) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		Pelicula peli = null;

		try {
			tx.begin();

			Query<?> query = pm.newQuery("SELECT FROM " + Pelicula.class.getName() + " WHERE nombrePelicula == '" + nombrePelicula + "'");
			query.setUnique(true);
			peli = (Pelicula) query.execute();

			tx.commit();
		} catch (Exception ex) {
			System.out.println(" $ Error cogiendo la pelicula de la BD: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return peli;	
		}
	
	public void store(Usuario usuario) {
		DBManager.getInstance().storeObjectInDB(usuario);
	}
	
	public void storePelicula(Pelicula pelicula) {
		DBManager.getInstance().storeObjectInDB(pelicula);
	}

	public void delete(Usuario usuario) {
		DBManager.getInstance().deleteObjectFromDB(usuario);
	}
	
	public void delete(Pelicula pelicula) {
		DBManager.getInstance().deleteObjectFromDB(pelicula);
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
		Usuario u6 = new Usuario("admin", "admin@admin.es", "admin", "XXXX-XXXX-XXXX-XXXX", true);  
		
		//PELICULAS...
		
		
		Pelicula p1 = new Pelicula("Indiana Jones", "Accion", 3, "26/04/2023", "DESCRIPCION");   
		Pelicula p2 = new Pelicula("Star Wars", "Scifi", 3, "27/04/2023", "DESCRIPCION");              
		Pelicula p3 = new Pelicula("Batman", "Accion", 3, "28/04/2023", "DESCRIPCION");            
		Pelicula p4 = new Pelicula("Los Vengadores", "Accion", 3, "29/04/2023", "DESCRIPCION");          
		Pelicula p5 = new Pelicula("[V.O.] El Triangulo de la Tristeza", "Comedia", 3, "30/04/2023", "DESCRIPCION");    
		
		
		try {
			 store(u1);
			 store(u2);
			 store(u3);
			 store(u4);
			 store(u5);
			 store(u6);
			 
			 storePelicula(p1);
			 storePelicula(p2);
			 storePelicula(p3);
			 storePelicula(p4);
			 storePelicula(p5);
	 
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println(" $ Error inicializando los datos: " + ex.getMessage());
			ex.printStackTrace(); 
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
	
	//Metodo para borrar usuario
	
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
			ex.printStackTrace(); 
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}
	}
	
	//Metodo para borrar las peliculas
	public void borrarPelicula(String nombrePelicula) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Pelicula.class.getName() + " WHERE nombrePelicula == '" + nombrePelicula + "'");
			System.out.println(" * '" + query.deletePersistentAll() + "' pelicula borrada de la DB.");
			System.out.println(" * '" + nombrePelicula + "' *");
			tx.commit();
		} catch (Exception ex) {
			System.out.println(" $ Error querying a Movie: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}
	}
	
	//Metodo para borrar los usuarios al iniciar
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
			System.out.println("  $ Error retrieving all the Users: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	
	
	
	
}
	public void borrarPeliculas() {
		List<Pelicula> peliculas = new ArrayList<Pelicula>();
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();

		try {

			tx.begin();

			Extent<Pelicula> extent = pm.getExtent(Pelicula.class, true);

			for (Pelicula pelicula : extent) {
				pm.deletePersistent(pelicula);
			}

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the Movies: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	
	
	
	
}
	
	public List<Usuario> getlistaAmigos() {
		List<Usuario> amigos = new ArrayList<Usuario>();
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(5);
		Transaction tx = pm.currentTransaction();

		try {

			tx.begin();

			Extent<Usuario> extent = pm.getExtent(Usuario.class, true);

			for (Usuario usuario : extent) {
				amigos.add(usuario);
			}

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving list of friends: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return amigos;
	}
}