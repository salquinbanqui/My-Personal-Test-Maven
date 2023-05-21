/*package es.deusto.spq.cliente.gui;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.SyncInvoker;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.deusto.spq.pojo.Pelicula;
import es.deusto.spq.server.Main;

public class VentanaAnyadirPeliculaTest {

	
	@Mock
	private HttpServer server;
	
	@Mock
	private Client client;

	@Mock
	private WebTarget appTarget;

	@Mock
	private WebTarget pelisTarget;

	@Mock
	private WebTarget webTarget;

	@InjectMocks
	private VentanaAnyadirPelicula ventanaAnyadirPelicula;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		 server = Main.startServer();
	     client = ClientBuilder.newClient();      
	     appTarget = client.target(Main.BASE_URI);
	     pelisTarget = appTarget.path("peliculas");
		//when(client.target(anyString())).thenReturn(webTarget);
	}

	  
		@SuppressWarnings("deprecation")
		@After
		public void tearDown() throws Exception {
			server.stop();
		}
	
	
		@Test
		public void testBtnAnyadirActionPerformed() {
		    JTextField textFieldTitulo = mock(JTextField.class);
		    when(ventanaAnyadirPelicula.getTextFieldTitulo()).thenReturn(textFieldTitulo);
		    when(textFieldTitulo.getText()).thenReturn("Test Title");

		    JTextField textFieldCategoria = mock(JTextField.class);
		    when(ventanaAnyadirPelicula.getTextFieldCategoria()).thenReturn(textFieldCategoria);
		    when(textFieldCategoria.getText()).thenReturn("Test Category");

		    JTextField textFieldPrecio = mock(JTextField.class);
		    when(ventanaAnyadirPelicula.getTextFieldPrecio()).thenReturn(textFieldPrecio);
		    when(textFieldPrecio.getText()).thenReturn("9.99");

		    JTextField textFieldFecha = mock(JTextField.class);
		    when(ventanaAnyadirPelicula.getTextFieldFecha()).thenReturn(textFieldFecha);
		    when(textFieldFecha.getText()).thenReturn("2023-05-21");

		    JTextField textFieldDescripcion = mock(JTextField.class);
		    when(ventanaAnyadirPelicula.getTextFieldDescripcion()).thenReturn(textFieldDescripcion);
		    when(textFieldDescripcion.getText()).thenReturn("Test Description");

		    ((SyncInvoker) doNothing().when(webTarget)).post(any(Entity.class));

		    // Call the method under test
		    ventanaAnyadirPelicula.btnAnyadirActionPerformed(null);

		    // Verification code
		    verify(textFieldTitulo).getText();
		    verify(textFieldCategoria).getText();
		    verify(textFieldPrecio).getText();
		    verify(textFieldFecha).getText();
		    verify(textFieldDescripcion).getText();
		    verify(webTarget, times(5)).path(anyString());
		    verify(webTarget, times(5)).request(anyString());
		    ((SyncInvoker) verify(webTarget, times(1))).post(any(Entity.class));
		}


}
*/
