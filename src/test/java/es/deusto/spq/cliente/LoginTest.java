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

public class LoginTest {

	private JTextField usu;
	private JPasswordField pass;
	private JTextField usu2;
	private JPasswordField pass2;
	private JTextField usu3;
	private JPasswordField pass3;

	private VentanaLogin vl;
	private VentanaLogin vl2;
	private VentanaLogin vl3;

	private HttpServer server;
	private WebTarget appTarget;
	private WebTarget loginTarget;
	
	
	@Before
	public void setUp() {
		usu = new JTextField();
		pass = new JPasswordField();
		usu2 = new JTextField();
		pass2 = new JPasswordField();
		usu3 = new JTextField();
		pass3 = new JPasswordField();

		vl = new VentanaLogin();
		
		server = Main.startServer();
		Client cliente = ClientBuilder.newClient();
		appTarget = cliente.target("http://localhost:8080/myapp");
		loginTarget = appTarget.path("usuarios");
	}
	
	@SuppressWarnings("deprecation")
	@After
	public void tearDown() throws Exception {
		server.stop();
	}
	
	@Test
	public void testMain() {
		VentanaLogin.main(new String[0]);
	}

}
