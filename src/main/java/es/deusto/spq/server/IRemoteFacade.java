package es.deusto.spq.server;

import javax.ws.rs.core.Response;

import es.deusto.spq.pojo.Usuario;

public interface IRemoteFacade {
	
	Response agregarUsuarioGestionPelis(Usuario usuario);
	

}
