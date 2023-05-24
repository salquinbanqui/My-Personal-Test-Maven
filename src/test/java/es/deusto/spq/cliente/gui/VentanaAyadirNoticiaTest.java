package es.deusto.spq.cliente.gui;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.client.Entity;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import es.deusto.spq.pojo.Pelicula;
import es.deusto.spq.server.Main;

public class VentanaAyadirNoticiaTest {

	private HttpServer server;
	private WebTarget appTarget;
	WebTarget productTarget;
	
	private JTextField textFieldTitulo = Mockito.mock(JTextField.class);
	private JTextField textFieldCategoria = Mockito.mock(JTextField.class);
	private JTextField textFieldPrecio = Mockito.mock(JTextField.class);
	private JTextField textFieldFecha = Mockito.mock(JTextField.class);
	private JTextField textFieldDesc = Mockito.mock(JTextField.class);
	
	
	 @Before
	    public void setUp() throws Exception {
		 
	      
	        server = Main.startServer();
	        Client c = ClientBuilder.newClient();
	        appTarget = c.target(Main.BASE_URI);
	        productTarget = appTarget.path("noticias");
	        
	        
	    }

	    @After
	    public void tearDown() throws Exception {
	    	
	    	
			List<String> pelis = Arrays.asList("Peliculas");
	    	WebTarget productElimTarget = productTarget.path("elim");
	        productElimTarget.request().post(Entity.entity(pelis, MediaType.APPLICATION_JSON));
	        
	    	
	        server.stop();
	        
	    }
	    
	    
/*   @Test
	public void testAnyadirNoticia() {
			
	    	
	private JPanel contentPane;
	private JTextField textFieldTitulo;
	private JTextField textFieldCategoria;
	private JTextField textFieldPrecio;
	private JTextField textFieldFecha;
	private JTextField textFieldDescripcion;
			
			List<String> noticiasN = new ArrayList<>();
			when(textFieldTitulo.getText()).thenReturn("Noticia 1");
			noticiasN.add(textFieldTitulo.getText());
			when(textFieldCategoria.getText()).thenReturn("Deportes");
			noticiasN.add(textFieldCategoria.getText());
			when(textFieldPrecio.getText()).thenReturn("10");
			noticiasN.add(textFieldPrecio.getText());
			when(textFieldFecha.getText()).thenReturn("13-08-2019");
			noticiasN.add(textFieldFecha.getText());
			when(textFieldDesc.getText()).thenReturn("Noticias acerca de nuevos estrenos");
			noticiasN.add(textFieldDesc.getText());
			WebTarget productInsTarget = productTarget.path("not");
			productInsTarget.request().post(Entity.entity(noticiasN, MediaType.APPLICATION_JSON));
			
			WebTarget productNomTarget = productTarget.path("nom").queryParam("La Mosca", "Terror");
			GenericType<List<Pelicula>> genericType = new GenericType<List<Pelicula>>() {
			};
			List<Pelicula> peliculas = productNomTarget.request(MediaType.APPLICATION_JSON).get(genericType);
			
			assertEquals("La Mosca", peliculas.get(0).getNombrePelicula());
			
		}
		*/
}
