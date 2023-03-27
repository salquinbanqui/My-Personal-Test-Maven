package es.deusto.spq.cliente;

import java.util.List;

import javax.ws.rs.core.Response;

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
	
	//public boolean agregarReservaInstalacion(String IDReserva, String IDInstalacion, String emailUsuario, int anyo, int mes, int dia, int hora) {
	//	return serviceLocator.agregarReservaInstalacion(IDReserva, IDInstalacion, emailUsuario, anyo, mes, dia , hora);
	//}
	//obtener todas las instalaciones
	public List<Pelicula> obtenerPeliculas() {
		return serviceLocator.obtenerPeliculas();
	}
	//obtener todas las reservas
	//public List<ReservaInstalaciones> obtenerReservas() {
	//	return serviceLocator.obtenerReservas();
	//}
	//borrar reserva
	//public Response borrarReserva(ReservaInstalaciones reserva) {
	//	return serviceLocator.borrarReserva(reserva);
	//obtener una reserva en concreto
	//}
	//public ReservaInstalaciones buscarReservaInstalacion(String IDReserva) {
	//	return serviceLocator.buscarReservaInstalacion(IDReserva);
	//}


}