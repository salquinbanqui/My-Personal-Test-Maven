package es.deusto.spq.cliente;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.pojo.Usuario;

public class UsuarioTest {

	private Usuario u;
	
	@Before
    public void crearusuario() {
        u = new Usuario("jose","jose@gmail.com","1234","n_tarjeta",false);
    }
	
	@Test
	public void testGetNombreUsuario() {
		assertEquals("jose", u.getNombreUsuario());
	}
	
	@Test
	public void testSetNombreUsuario() {
		u.setNombreUsuario("samu");
		assertEquals("samu", u.getNombreUsuario());
	}
	
	@Test
	public void testGetPassword() {
		assertEquals("1234", u.getPassword());
	}
	
	@Test
	public void testSetPassword() {
		u.setPassword("4321");
		assertEquals("4321", u.getPassword());
	}
	
}
