package es.deusto.spq.cliente;

import java.util.List;

public class Controller {
	
public ServiceLocator serviceLocator;
	
	public Controller(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	
	public int loginGestionPelis(String email, String contrasenya){
		return serviceLocator.loginGestionPelis(email, contrasenya);
	}
	
	public boolean agregarUsuarioGestionPelis(String nombreUsuario, String password, String email, String tarjeta){
		return serviceLocator.agregarUsuarioGestionPelis(nombreUsuario, email, password, tarjeta);
	}
	
	
	//obtener todas las peliculas
	public List<Pelicula> obtenerPeliculas() {
		return serviceLocator.obtenerPeliculas();
	}
	


}