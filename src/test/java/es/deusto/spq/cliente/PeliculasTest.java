package es.deusto.spq.cliente;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.pojo.Pelicula;

public class PeliculasTest {

	private Pelicula p;
	
	@Before
    public void crearusuario() {
        p = new Pelicula("Titanic","Drama",12,"1997","Pelicula nominada a los Oscar");
    }
	
	@Test
	public void testGetNombre() {
		assertEquals("Titanic", p.getNombre());
	}
	
	@Test
	public void testSetNombre() {
		p.setNombre("UP");
		assertEquals("UP", p.getNombre());
	}
	
	@Test
	public void testGetCategoria() {
		assertEquals("Drama", p.getCategoria());
	}
	
	@Test
	public void testSetCategoria() {
		p.setCategoria("Diversion");
		assertEquals("Diversion", p.getCategoria());
	}
	
}
