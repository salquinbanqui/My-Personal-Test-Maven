package es.deusto.spq.cliente;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import es.deusto.spq.pojo.Pelicula;
import es.deusto.spq.server.Main;

public class PeliculasResourceTest {

	
	private HttpServer server;
	private WebTarget appTarget;
	private Client c;
	
	@Rule
	public ContiPerfRule rule = new ContiPerfRule();
	
	@Before
	public void setUp() throws Exception {
		server = Main.startServer();
		c = ClientBuilder.newClient();
		appTarget = c.target(Main.BASE_URI);
	}

	@SuppressWarnings("deprecation")
	@After
	public void tearDown() throws Exception {
		server.stop();
	}
	
	@Ignore
	@Test
	//@PerfTest(invocations = 100, threads = 40)
	public void testgetPelicula() {
		WebTarget peliculasTarget = appTarget.path("peliculas");

		List<Pelicula> listaPeliculas = Arrays.asList(new Pelicula("Tarzan", "dibujos",3.5,"26-03-2009","pelicula de dibujos para niños "), new Pelicula("Tarzan", "dibujos",3.5,"26-03-2009","pelicula de dibujos para niños "), 
				new Pelicula("Tarzan", "dibujos",3.5,"26-03-2009","pelicula de dibujos para niños "));

		GenericType<List<Pelicula>> genericType = new GenericType<List<Pelicula>>() {
		};
		List<Pelicula> pelicula1 = peliculasTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		List<Pelicula> pelicula2 = new ArrayList<Pelicula>();
		for (int i = 0; i < pelicula1.size(); i++) {

			if (pelicula1.get(i).getNombre().equals(listaPeliculas.get(0).getNombre())) {

				pelicula2.add(pelicula1.get(i));
				assertEquals(listaPeliculas.get(0).getNombre(), pelicula2.get(0).getNombre());
			}

		}
		assertEquals(listaPeliculas.get(0).getNombre(), pelicula1.get(0).getNombre());
		assertEquals(listaPeliculas.get(1).getNombre(), pelicula1.get(1).getNombre());
		assertEquals(listaPeliculas.get(2).getNombre(), pelicula1.get(2).getNombre());

	}
}
