package es.deusto.spq.cliente;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import es.deusto.spq.pojo.Pelicula;
import es.deusto.spq.pojo.Usuario;

public class Controller {
	
private ServiceLocator serviceLocator;


	
	public Controller(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	
	public int loginGestionPelis(String nombreUsuario, String contrasenya){
		return serviceLocator.loginGestionPelis(nombreUsuario, contrasenya);
	}
	
	public int logoutGestionPelis(){
		return serviceLocator.logoutGestionPelis();
	}
	
	/*
	public void logUsuario(String name, String password) {
		WebTarget webTarget = ClientBuilder.newClient().target("http://localhost:8080/webapi");
		//WebTarget appTarget = client.target("http://localhost:8080/webapi");
		WebTarget registerUserWebTarget = webTarget.path("server/loginGestionPelis");
        Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(name);
        usuario.setPassword(password);
        Response response = invocationBuilder.post(Entity.entity(usuario, MediaType.APPLICATION_JSON));
        if (response.getStatus() != Status.OK.getStatusCode()) {
            //logger.error("Error connecting with the server. Code: {}", response.getStatus());
        	System.out.println("Login INCORRECTO." + name);	//en si no es el login
        } else {
            //logger.info("User correctly logged");
        	System.out.println("Login CORRECTO." + name);	//en si no es el login

        }
    }
	
	*/

	public boolean agregarUsuarioGestionPelis(String nombreUsuario, String password, String email, String tarjeta){
		return serviceLocator.agregarUsuarioGestionPelis(nombreUsuario, email, password, tarjeta);
	}
	
	
	//obtener todas las peliculas
	public List<Pelicula> obtenerPeliculas() {
		return serviceLocator.obtenerPeliculas();
	}
	
	
	


}