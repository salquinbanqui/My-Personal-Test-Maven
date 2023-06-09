package es.deusto.spq.server;
/*package es.deusto.spq.cliente;

import static org.junit.Assert.assertEquals;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import es.deusto.spq.pojo.Pelicula;
import es.deusto.spq.pojo.Usuario;
import es.deusto.spq.server.Main;

public class UsuariosResourceTest {

	@Rule
	public ContiPerfRule rule = new ContiPerfRule();
	private HttpServer server;
	private WebTarget appTarget;
	private Client c;

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
	//@PerfTest(invocations = 100, threads = 40)
	public void testgetUsers() {
		WebTarget usersTarget = appTarget.path("users");
		WebTarget usersallTarget = usersTarget.path("allusers");

		List<Usuario> listausers = Arrays.asList(new Usuario("Unai", "unai@unai.com", "passwordunai", "33-33-33-33", false));

		
		
		GenericType<List<Usuario>> genericType = new GenericType<List<Usuario>>() {
		};
		List<Usuario> users = usersallTarget.request(MediaType.APPLICATION_JSON).get(genericType);

		
		
		assertEquals(listausers.get(0).getNombreUsuario(), users.get(0).getNombreUsuario());
	}



}*/

