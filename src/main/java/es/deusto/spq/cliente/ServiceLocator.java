package es.deusto.spq.cliente;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import es.deusto.spq.pojo.Usuario;

public class ServiceLocator {

	private Client c;
	private static Logger logger = Logger.getLogger(ServiceLocator.class.getName());
	private WebTarget wt;
	
	
	public ServiceLocator() {
		c = ClientBuilder.newClient();
		wt = c.target(cogerUrl());
	}
	
	public static String cogerUrl() {
		GetProperties gp = new GetProperties();
		String url = " ";
		
		try {
			url = gp.getUrl();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
	
	public boolean agregarUsuarioGestionPelis(String nombreUsuario, String email, String password, String tarjeta) {
		WebTarget registerUserWebTarget = wt.path("server/registroGestionPelis");
		Usuario u = new Usuario();
		u.setNombreUsuario(nombreUsuario);
		u.setEmail(email);
		u.setPassword(password);
		u.setTarjeta(tarjeta);
		
		Entity<Usuario> entity = Entity.entity(u, MediaType.APPLICATION_JSON);
		Response response = registerUserWebTarget.request().post(entity);
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error conectando con el servidor. Codigo: " + response.getStatus());
			return false;
		} else {
			logger.info("Usuario registrado correctamente");
			return true;
		}
	}
	
	
	/**Método logearse en el polideportivo
	 * LLama al metodo al RemoteFacade del servidor con la URL establecida
	 * @param email  de la cuenta del cliente 
	 * @param contrasenya de la cuenta del cliente
	 * @return Devuelve true si todo ha salido correctamente
	 */
	public int loginGestionPelis(String email, String password) {
		WebTarget webTarget1 = wt.path("server/loginGestionPelis");
		Invocation.Builder invocationBuilder = webTarget1.request(MediaType.APPLICATION_JSON);

		Usuario u = new Usuario();
		u.setEmail(email);
		u.setPassword(password);

		Response response = invocationBuilder.post(Entity.entity(c, MediaType.APPLICATION_JSON));
		if (response.getStatus() == Status.OK.getStatusCode()) {
			return 2;

		} else if (response.getStatus() == Status.ACCEPTED.getStatusCode()) {
			return 1;
		}
		return 0;
	}
	
	
	public List<Pelicula> obtenerPeliculas() {
        WebTarget webTarget4 = wt.path("server/getPeliculas");
		Invocation.Builder invocationBuilder = webTarget4.request(MediaType.APPLICATION_JSON);

		List<Pelicula> instalaciones = new ArrayList<Pelicula>();

		GenericType<List<Pelicula>> genericType = new GenericType<List<Pelicula>>() {};
		instalaciones = webTarget4.request(MediaType.APPLICATION_JSON).get(genericType);

		return instalaciones;
    }
	
}