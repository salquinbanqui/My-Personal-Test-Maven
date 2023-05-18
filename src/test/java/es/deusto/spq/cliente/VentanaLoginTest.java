package es.deusto.spq.cliente;

import static org.junit.Assert.*;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.cliente.gui.VentanaLogin;
import es.deusto.spq.server.Main;

public class VentanaLoginTest {

	private JTextField tUsuario1;
	private JPasswordField pass1;

	private JTextField tUsuario2;
	private JPasswordField pass2;
	
	private JTextField tUsuario3;
	private JPasswordField pass3;

	private VentanaLogin l1;
	private VentanaLogin l2;
	private VentanaLogin l3;

	private HttpServer server;
	private WebTarget appTarget;
	private WebTarget loginTarget;
	
	@Before
	public void setUp() throws Exception {
		tUsuario1 = new JTextField();
		pass1 = new JPasswordField();

		tUsuario2 = new JTextField();
		pass2 = new JPasswordField();

		tUsuario3 = new JTextField();
		pass3 = new JPasswordField();

		l1 = new VentanaLogin();
		
		server = Main.startServer();
		Client cliente = ClientBuilder.newClient();
		appTarget = cliente.target("http://localhost:8080/webapi");
		loginTarget = appTarget.path("usuarios");
	}

	@After
	public void tearDown() throws Exception {
		server.stop();
	}

	@Test
	public void testMain() {
		VentanaLogin.main(new String[0]);
	}

}
