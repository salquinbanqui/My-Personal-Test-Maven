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
 usuarios.add(new Usuario("samuelkiwi", "password_samu", "samuel.martin@opendeusto.es", "XXXX-XXXX-XXXX-XXXX"));
 usuarios.add(new Usuario("jotajota", "password_jose", "jose@opendeusto.es", "XXXX-XXXX-XXXX-XXXX"));
 usuarios.add(new Usuario("markelinho", "password_mark", "mark@opendeusto.es", "XXXX-XXXX-XXXX-XXXX"));
 usuarios.add(new Usuario("yonan99", "password_yonan", "yonander@opendeusto.es", "XXXX-XXXX-XXXX-XXXX"));
 usuarios.add(new Usuario("nachete", "password_nacho", "ignacio@opendeusto.es", "XXXX-XXXX-XXXX-XXXX"));


 return usuarios;
 }
 
 @POST
 @Consumes(MediaType.APPLICATION_JSON)
 public void addUsuario(Usuario usuario) {
     System.out.println("Received new user: " + usuario);
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

