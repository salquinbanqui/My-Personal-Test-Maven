package es.deusto.spq.server;

import javax.xml.ws.Response;

import es.deusto.spq.pojo.Usuario;

public interface IRemoteFacade {
	
	Response agregarUsuarioGestionPelis(Usuario usuario);
	

}
