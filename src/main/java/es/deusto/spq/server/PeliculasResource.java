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
import java.util.HashMap;

import es.deusto.spq.pojo.Pelicula;



@Path("peliculas")
public class PeliculasResource {
	private final static Logger LOGGER = Logger.getLogger(Logger.class);
	private Pelicula p1;
	
	HashMap<String,Pelicula> mp = new HashMap<>();
	
	
 @GET
 @Path("all")
 @Produces(MediaType.APPLICATION_JSON)
 public List<Pelicula> getPeliculas() {
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
 	List<Pelicula> peliculas = new ArrayList<Pelicula>();

	try {
		Query<Pelicula> q = pm.newQuery(Pelicula.class);
		peliculas = q.executeList();
	} catch (Exception e) {
		LOGGER.error(e.getMessage());
	} finally {
		pm.close();
	}
	return peliculas;
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
 @Produces(MediaType.APPLICATION_JSON)
 public Response addPelicula(List<String> peliculaL) {
	 	String nombrePelicula = peliculaL.get(0);
		String categoria = peliculaL.get(1);
		String precio = peliculaL.get(2);
		String fecha = peliculaL.get(3);
		String descripcion = peliculaL.get(4);
		
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Pelicula pelicula1 = new Pelicula(nombrePelicula, categoria, Double.parseDouble(precio), fecha, descripcion);
			pm.makePersistent(pelicula1);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return Response.ok(new Pelicula()).build();
  
 }
 


 @DELETE
 @Path("/{nombrePelicula}")
 public Response deletePelicula(@PathParam("nombrePelicula") String nombrePelicula) {
	 
//	 u1 = new Usuario();
//	 u1 = DBManager.getInstance().getUsuario(username).getNombreUsuario();
	 
     if (DBManager.getInstance().getPelicula(nombrePelicula).getNombrePelicula() != null) {
    	
         System.out.println("Deleting movie..." + nombrePelicula);
       
       //  DBManager.getInstance().delete(DBManager.getInstance().getUsuario(username));
       //  Pelicula p = DBManager.getInstance().getPelicula(nombrePelicula);
        
         
         //DBManager.getInstance().delete( DBManager.getInstance().getPelicula(nombrePelicula));
         DBManager.getInstance().borrarPelicula(nombrePelicula);
         
         return Response.status(Response.Status.OK).build();
     } else {
         return Response.status(Response.Status.NOT_FOUND).build();
     }
 }
 
 
 
 
 
}

