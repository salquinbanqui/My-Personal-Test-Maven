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

import es.deusto.spq.cliente.gui.VentanaAdmin;
import es.deusto.spq.pojo.Pelicula;
import es.deusto.spq.pojo.Usuario;

public class ServiceLocator {

	private Client c;
	private static Logger logger = Logger.getLogger(ServiceLocator.class.getName());
	private WebTarget wt;
	
	
	public ServiceLocator() {
		c = ClientBuilder.newClient();
		wt = c.target("http://localhost:8080/webapi");
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
	
	/**Método para agregar un usuario a gestion pelis
	 * LLama al metodo al RemoteFacade del servidor con la URL establecida
	 * @param nombre del usuario que se registra
	 * @param email del usuario que se registra
	 * @param password del usuario que se registra
	 * @param tarjeta del usuario que se registra
	 * @return Devuelve true si todo ha salido correctamente
	 */
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
	
	
	/**Método logearse 
	 * LLama al metodo al RemoteFacade del servidor con la URL establecida
	 * @param email  de la cuenta del usuario 
	 * @param contraseña de la cuenta del usuario
	 * @return Devuelve true si todo ha salido correctamente
	 */
	public int loginGestionPelis(String nombreUsuario, String password) {
		WebTarget webTarget1 = wt.path("server/loginGestionPelis");
		Invocation.Builder invocationBuilder = webTarget1.request(MediaType.APPLICATION_JSON);

		Usuario u = new Usuario();
		u.setNombreUsuario(nombreUsuario);
		u.setPassword(password);

		Response response = invocationBuilder.post(Entity.entity(u, MediaType.APPLICATION_JSON));
		if (response.getStatus() == Status.OK.getStatusCode()) {
			System.out.println("Login correcto como ADMINISTRADOR");//VISUALIZACION DE ADMINISTRADOR
			VentanaAdmin ventanaAdmin = new VentanaAdmin(c);
			ventanaAdmin.setVisible(true);
			return 2;
			

		} else if (response.getStatus() == Status.ACCEPTED.getStatusCode()) {
			System.out.println("Login correcto como USUARIO");//VISUALIZACION DE USUARIO
			return 1;
		}
		return 0;
	}
	
	
	public int logoutGestionPelis() {
		WebTarget webTarget1 = wt.path("server/logoutGestionPelis");
		Invocation.Builder invocationBuilder = webTarget1.request(MediaType.TEXT_PLAIN);

	//	Usuario u = new Usuario();
	//	u.setNombreUsuario(nombreUsuario);
	//	u.setPassword(password);

		Response response = invocationBuilder.post(null);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			System.out.println("LOGOUT correcto como ADMINISTRADOR");//VISUALIZACION DE ADMINISTRADOR
			//VentanaAdmin ventanaAdmin = new VentanaAdmin(c);
			//ventanaAdmin.setVisible(true);
			return 2;
			

		} else if (response.getStatus() == Status.ACCEPTED.getStatusCode()) {
			System.out.println("LOGOUT correcto como USUARIO");//VISUALIZACION DE USUARIO
			return 1;
		}
		return 0;
	}
	
	/**Método obtener todas las peliculass de la bd
	 * LLama al metodo al RemoteFacade del servidor con la URL establecida
	 * @return peliculas de la bd
	 */
	
	public List<Pelicula> obtenerPeliculas() {
        WebTarget webTarget4 = wt.path("server/obtenerPeliculas");
		Invocation.Builder invocationBuilder = webTarget4.request(MediaType.APPLICATION_JSON);

		List<Pelicula> instalaciones = new ArrayList<Pelicula>();

		GenericType<List<Pelicula>> genericType = new GenericType<List<Pelicula>>() {};
		instalaciones = webTarget4.request(MediaType.APPLICATION_JSON).get(genericType);

		return instalaciones;
    }
	
}