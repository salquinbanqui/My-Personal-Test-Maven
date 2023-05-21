package es.deusto.spq.cliente.gui;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.deusto.spq.cliente.gui.VentanaModPeliculaForm;
import es.deusto.spq.pojo.Pelicula;
import es.deusto.spq.server.Main;

public class VentanaModPeliculaFormTest {
	
	
	@Mock
	private HttpServer server;

    @Mock
    private Client client;

    @Mock
    private WebTarget appTarget;

    @Mock
    private WebTarget pelisTarget;

    @Mock
    private Pelicula peliculaMock; // Mocked Pelicula object

    @InjectMocks
    private VentanaModPeliculaForm ventanaModPeliculaFormUnderTest;

    @SuppressWarnings("deprecation")
	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
      server = Main.startServer();
        client = ClientBuilder.newClient();      
        appTarget = client.target(Main.BASE_URI);
        pelisTarget = appTarget.path("peliculas");
        // Set the mocked Pelicula object on the ventanaModPeliculaFormUnderTest instance
        ventanaModPeliculaFormUnderTest.setPelicula(peliculaMock);
        
       
    }
    
    
	@SuppressWarnings("deprecation")
	@After
	public void tearDown() throws Exception {
		server.stop();
	}

    @Test
    public void testBtnGuardarCambiosActionPerformed() {
        // Create a mocked JTextField for the movie title
    
    	
        JTextField textFieldTituloMock = mock(JTextField.class);
        when(textFieldTituloMock.getText()).thenReturn("New Title");

        // Create a mocked JTextField for the movie category
        JTextField textFieldCategoriaMock = mock(JTextField.class);
        when(textFieldCategoriaMock.getText()).thenReturn("New Category");

        // Create a mocked JTextField for the movie price
        JTextField textFieldPrecioMock = mock(JTextField.class);
        when(textFieldPrecioMock.getText()).thenReturn("9.99");

        // Create a mocked JTextField for the movie release date
        JTextField textFieldFechaMock = mock(JTextField.class);
        when(textFieldFechaMock.getText()).thenReturn("2023-05-21");

        // Create a mocked JTextField for the movie description
        JTextField textFieldDescripcionMock = mock(JTextField.class);
        when(textFieldDescripcionMock.getText()).thenReturn("New Description");

        // Set the mocked JTextFields on the VentanaModPeliculaForm under test
        ventanaModPeliculaFormUnderTest.setTextFieldTitulo(textFieldTituloMock);
        ventanaModPeliculaFormUnderTest.setTextFieldCategoria(textFieldCategoriaMock);
        ventanaModPeliculaFormUnderTest.setTextFieldPrecio(textFieldPrecioMock);
        ventanaModPeliculaFormUnderTest.setTextFieldFecha(textFieldFechaMock);
        ventanaModPeliculaFormUnderTest.setTextFieldDescripcion(textFieldDescripcionMock);

        // Create a mocked JButton for the "Guardar cambios" button
        JButton btnGuardarCambiosMock = mock(JButton.class);

        // Create an ActionListener for the "Guardar cambios" button
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// Action event handling logic goes here
            	Pelicula newPeli = new Pelicula(ventanaModPeliculaFormUnderTest.getTextFieldTitulo().getText(), ventanaModPeliculaFormUnderTest.getTextFieldCategoria().getText(), Double.parseDouble(ventanaModPeliculaFormUnderTest.getTextFieldPrecio().getText()), ventanaModPeliculaFormUnderTest.getTextFieldFecha().getText(), ventanaModPeliculaFormUnderTest.getTextFieldDescripcion().getText());
            	
            	 peliculaMock.setNombrePelicula("New Title");
                 peliculaMock.setCategoria("New Category");
                 peliculaMock.setPrecio(9.99);
                 peliculaMock.setFecha("2023-05-21");
                 peliculaMock.setDescripcion("New Description");
                 
            	WebTarget peliRegTarget = pelisTarget.path("reg");
            	List<String> peliL = new ArrayList<>();
            	peliL.add(newPeli.getNombrePelicula());
            	peliL.add(newPeli.getCategoria());
            	peliL.add(String.valueOf(newPeli.getPrecio()));
            	peliL.add(newPeli.getFecha());
            	peliL.add(newPeli.getDescripcion());

            	peliRegTarget.request().post(Entity.entity(peliL, MediaType.APPLICATION_JSON));

            }
            };

        // Simulate the button click event
        ActionEvent actionEvent = mock(ActionEvent.class);
        when(actionEvent.getSource()).thenReturn(btnGuardarCambiosMock);

        // Invoke the actionPerformed method
        actionListener.actionPerformed(actionEvent);

        // Verify the expected interactions with the mocked objects
        verify(peliculaMock).setNombrePelicula("New Title");
        verify(peliculaMock).setCategoria("New Category");
        verify(peliculaMock).setPrecio(9.99);
        verify(peliculaMock).setFecha("2023-05-21");
        verify(peliculaMock).setDescripcion("New Description");

        // Add additional verification or assertions based on the behavior you want to test
    }
    
    
}
