/*package es.deusto.spq.cliente;

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
	
	
	@Test
	@PerfTest(invocations = 100, threads = 40)

	public void testaddPelicula() {
		
		List<String> peliculasP = new ArrayList<>();
		when(textFieldTitulo.getText()).thenReturn("La Mosca");
		peliculasP.add(textFieldTitulo.getText());
		when(textFieldCategoria.getText()).thenReturn("Terror");
		peliculasP.add(textFieldCategoria.getText());
		when(textFieldPrecio.getText()).thenReturn("5");
		peliculasP.add(textFieldPrecio.getText());
		when(textFieldFecha.getText()).thenReturn("13-08-2014");
		peliculasP.add(textFieldFecha.getText());
		when(textFieldDesc.getText()).thenReturn("Pelicula sobre moscas asesinas");
		peliculasP.add(textFieldDesc.getText());
		WebTarget productInsTarget = productTarget.path("ins");
		productInsTarget.request().post(Entity.entity(peliculasP, MediaType.APPLICATION_JSON));
		
		WebTarget productNomTarget = productTarget.path("nom").queryParam("La Mosca", "Terror");
		GenericType<List<Pelicula>> genericType = new GenericType<List<Pelicula>>() {
		};
		List<Pelicula> peliculas = productNomTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		
		assertEquals("La Mosca", peliculas.get(0).getNombrePelicula());
		
	}
}*/
