package es.deusto.spq.cliente;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import es.deusto.spq.pojo.Usuario;

public class Controller {
	
public ServiceLocator serviceLocator;
	
	public Controller(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	
	public int loginGestionPelis(String email, String contrasenya){
		return serviceLocator.loginGestionPelis(email, contrasenya);
	}
	
	public void logUsuario(String name, String password) {
		WebTarget webTarget = ClientBuilder.newClient().target(String.format("http://%s:%s/rest/server", "", ""));
        WebTarget registerUserWebTarget = webTarget.path("login");
        Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(name);
        usuario.setPassword(password);
        Response response = invocationBuilder.post(Entity.entity(usuario, MediaType.APPLICATION_JSON));
        if (response.getStatus() != Status.OK.getStatusCode()) {
            //logger.error("Error connecting with the server. Code: {}", response.getStatus());
        } else {
            //logger.info("User correctly logged");

        }
    }
	
	public boolean agregarUsuarioGestionPelis(String nombreUsuario, String password, String email, String tarjeta){
		return serviceLocator.agregarUsuarioGestionPelis(nombreUsuario, email, password, tarjeta);
	}
	
	
	//obtener todas las peliculas
	public List<Pelicula> obtenerPeliculas() {
		return serviceLocator.obtenerPeliculas();
	}
	


}