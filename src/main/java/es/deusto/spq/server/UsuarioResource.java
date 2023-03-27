package es.deusto.spq.server;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.ArrayList;
import es.deusto.spq.pojo.Usuario;


@Path("/usuarios")
public class UsuarioResource {
	
 @GET
 @Produces(MediaType.APPLICATION_JSON)
 public List<Usuario> getUsuarios() {
 // This data could be retrieved from a database
 List<Usuario> usuarios = new ArrayList<Usuario>();
 usuarios.add(new Usuario("samuelkiwi", "samuel.martin@opendeusto.es", "password_samu", "XXXX-XXXX-XXXX-XXXX"));
 usuarios.add(new Usuario("jotajota", "jose@opendeusto.es", "password_jose", "XXXX-XXXX-XXXX-XXXX"));
 usuarios.add(new Usuario("markelinho", "mark@opendeusto.es", "password_mark", "XXXX-XXXX-XXXX-XXXX"));
 usuarios.add(new Usuario("yonan99", "yonander@opendeusto.es", "password_yonan", "XXXX-XXXX-XXXX-XXXX"));
 usuarios.add(new Usuario("nachete", "ignacio@opendeusto.es", "password_nacho", "XXXX-XXXX-XXXX-XXXX"));


 return usuarios;
 }
 
 @POST
 @Consumes(MediaType.APPLICATION_JSON)
 public void addUsuario(Usuario usuario) {
     System.out.println("Received new user: " + usuario);
     //DBManager.getInstance().agregarUsuarioGestionPelis(usuario);
     System.out.println("insertando usuario...");
     DBManager.getInstance().store(usuario);
  
 }

 @DELETE
 @Path("/{code}")
 public Response deleteUser(@PathParam("code") int code) {
     if (code == 10) {
         System.out.println("Deleting user...");
         return Response.status(Response.Status.OK).build();
     } else {
         return Response.status(Response.Status.NOT_FOUND).build();
     }
 }
}

