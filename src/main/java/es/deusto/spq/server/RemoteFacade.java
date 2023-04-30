package es.deusto.spq.server;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import es.deusto.spq.pojo.Usuario;

@Path("server")
@Produces(MediaType.APPLICATION_JSON)
public class RemoteFacade  {

	private DBManager dbmanager = null;
	private Logger logger = Logger.getLogger(RemoteFacade.class.getName());
	private static final long SESSION_TIMEOUT_MS = 10 * 60 * 1000; // 10 minutes
    private static final Map<String, Long> userSessions = new ConcurrentHashMap<>();

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
    public Response loginGestionPelis(Usuario usuario) {
        Usuario user = dbmanager.getUsuario(usuario.getNombreUsuario());
        if (user != null && user.getPassword().equals(usuario.getPassword())) {
            String sessionToken = UUID.randomUUID().toString();
            userSessions.put(sessionToken, System.currentTimeMillis());
            if (user.isAdmin()) {
                return Response.status(Response.Status.OK).entity(sessionToken).build();
            } else {
                return Response.status(Response.Status.ACCEPTED).entity(sessionToken).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
	
	
	 @POST
	    @Path("/logoutGestionPelis")
	    @Consumes(MediaType.TEXT_PLAIN)
	    public Response logoutGestionPelis(String sessionToken) {
	        if (userSessions.containsKey(sessionToken)) {
	            userSessions.remove(sessionToken);
	            return Response.status(Response.Status.OK).build();
	        } else {
	            return Response.status(Response.Status.UNAUTHORIZED).build();
	        }
	    }



	

	

}