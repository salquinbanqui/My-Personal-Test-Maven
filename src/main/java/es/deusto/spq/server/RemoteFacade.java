package es.deusto.spq.server;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import es.deusto.spq.pojo.Usuario;

@Path("server")
@Produces(MediaType.APPLICATION_JSON)
public class RemoteFacade {

	private DBManager dbmanager = null;
	private Logger logger = Logger.getLogger(RemoteFacade.class.getName());

	public RemoteFacade() {
		this.dbmanager = DBManager.getInstance();
		return;
	}

	private static final long serialVersionUID = 1L;
	private static RemoteFacade instance;
	
	@POST
	@Path("/registroGestionPelis")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response agregarUsuarioGestionPelis(Usuario usuario) {
		Usuario c = dbmanager.getUsuario(usuario.getNombreUsuario());
		if(c== null) {
			dbmanager.store(usuario);
			return Response.status(Response.Status.OK).build();
		}return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
	@POST
	@Path("/loginGestionPelis")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response loginPolideportivo(Usuario usuario) {
		Usuario user = dbmanager.getUsuario(usuario.getEmail());
		if(user!= null && user.getPassword().equals(usuario.getPassword())) {
			if(user.isAdmin()) {
				return Response.status(Response.Status.OK).build();
			}else {
				return Response.status(Response.Status.ACCEPTED).build();
			}	
		}return Response.status(Response.Status.BAD_REQUEST).build();
	}

}