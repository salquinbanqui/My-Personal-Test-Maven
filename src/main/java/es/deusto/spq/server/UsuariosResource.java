package es.deusto.spq.server;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.ArrayList;
import es.deusto.spq.pojo.Usuario;


@Path("/usuarios")
public class UsuariosResource {
	private final static Logger LOGGER = Logger.getLogger(Logger.class);
	
	
 @GET
 @Path("/all")
 @Produces(MediaType.APPLICATION_JSON)
 public List<Usuario> getUsuarios() {
 // This data could be retrieved from a database
	// DBManager.getInstance().getUsuario("*");
	 
	 PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	 PersistenceManager pm = pmf.getPersistenceManager();
	 //List<Usuario> usuarios = DBManager.getInstance().getUsuarios();
// usuarios.add(new Usuario("samuelkiwi", "samuel.martin@opendeusto.es", "password_samu", "XXXX-XXXX-XXXX-XXXX"));
// usuarios.add(new Usuario("jotajota", "jose@opendeusto.es", "password_jose", "XXXX-XXXX-XXXX-XXXX"));
// usuarios.add(new Usuario("markelinho", "mark@opendeusto.es", "password_mark", "XXXX-XXXX-XXXX-XXXX"));
// usuarios.add(new Usuario("yonan99", "yonander@opendeusto.es", "password_yonan", "XXXX-XXXX-XXXX-XXXX"));
// usuarios.add(new Usuario("nachete", "ignacio@opendeusto.es", "password_nacho", "XXXX-XXXX-XXXX-XXXX"));
 	List<Usuario> usuarios = null;

	try {
		Query<Usuario> q = pm.newQuery(Usuario.class);
		usuarios = q.executeList();
	} catch (Exception e) {
		LOGGER.error(e.getMessage());
	} finally {
		pm.close();
	}
	return usuarios;
 }
 
 
 /*
 @POST
 @Path("reg")
 @Consumes(MediaType.APPLICATION_JSON)
 public void addUsuario(Usuario usuario) {
     System.out.println("Received new user: " + usuario);
     //DBManager.getInstance().agregarUsuarioGestionPelis(usuario);
     System.out.println("insertando usuario...");
     DBManager.getInstance().store(usuario);
    // DBManager.getInstance().agregarUsuarioGestionPelis(usuario);
     
    
     
     System.out.println(DBManager.getInstance().getUsuario(usuario.getNombreUsuario().toString()));
     
     //System.out.println(DBManager.getInstance().getUsuario("myketowers"));
  
 }
 
 */
 
 @POST
 @Path("reg")
 @Consumes(MediaType.APPLICATION_JSON)
 public void addUsuario(List<String> usuarioL) {
	 	String nick = usuarioL.get(0);
		String email = usuarioL.get(1);
		String contraseña = usuarioL.get(2);
		String tarjeta = usuarioL.get(3);
		//String admin = usuarioL.get(4).toString();
		
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Usuario usuario1 = new Usuario(nick, contraseña,email, tarjeta, true);
			pm.makePersistent(usuario1);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
  
 }

 @DELETE
 @Path("/{nombreUsuario}")
 public Response deleteUser(@PathParam("nombreUsuario") String username) {
	 
	 
     if (DBManager.getInstance().getUsuario(username).getNombreUsuario() != null) {
    	
         System.out.println("Deleting user..." + DBManager.getInstance().getUsuario(username));
       
       //  DBManager.getInstance().delete(DBManager.getInstance().getUsuario(username));
         DBManager.getInstance().borrarUsuario(DBManager.getInstance().getUsuario(username));
         return Response.status(Response.Status.OK).build();
     } else {
         return Response.status(Response.Status.NOT_FOUND).build();
     }
 }
 
 
}

