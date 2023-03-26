package es.deusto.spq.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.ArrayList;
import es.deusto.spq.pojo.Usuario;


@Path("usuarios")
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
}

