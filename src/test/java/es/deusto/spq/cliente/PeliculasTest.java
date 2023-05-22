package es.deusto.spq.cliente;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.pojo.Pelicula;

public class PeliculasTest {

	private Pelicula p;
	
	@Before
    public void crearPelicula() {
        p = new Pelicula("Titanic","Drama",12,"1997","Pelicula nominada a los Oscar");
    }
	
	@Test
	public void testGetNombre() {
		assertEquals("Titanic", p.getNombrePelicula());
	}
	
	@Test
	public void testSetNombre() {
		p.setNombrePelicula("UP");
		assertEquals("UP", p.getNombrePelicula());
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
	
	@Test
	public void testGetFecha() {
		assertEquals("1997", p.getFecha());
	}
	
	@Test
	public void testSetFecha() {
		p.setFecha("2000");
		assertEquals("2000", p.getFecha());
	}
	
}
