package es.deusto.spq.cliente.gui;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import es.deusto.spq.pojo.Pelicula;
import es.deusto.spq.server.Main;

public class VentanaModPeliculasTest {

    @Mock
    private HttpServer server;
	
    @Mock
    private Client client;

    @Mock
    private WebTarget appTarget;

    @Mock
    private WebTarget pelisTarget;

    @Mock
    private WebTarget peliculasAllTarget;

    @Mock
    private WebTarget addPeliTarget;

    @Mock
    private Response response;

    @Mock
    private Pelicula selectedPelicula;

    @Mock
    private JList<Pelicula> listaPelis;

    @Mock
    private JTextField tfTitulo;

    @Mock
    private JTextField tfCategoria;

    @Mock
    private JTextField tfPrecio;

    @Mock
    private JTextField tfFecha;

    @Mock
    private JTextField tfDescripcion;

    @InjectMocks
    private VentanaModPeliculas ventanaModPeliculasUnderTest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
      //  server = Main.startServer();
        client = ClientBuilder.newClient();      
        appTarget = client.target(Main.BASE_URI);
        pelisTarget = appTarget.path("peliculas");

        // Configure the mocks for the web targets and response
  //     when(client.target(anyString())).thenReturn(appTarget);
  //     when(appTarget.path(anyString())).thenReturn(pelisTarget);
  //     when(pelisTarget.path(anyString())).thenReturn(addPeliTarget);
  //     when(pelisTarget.path("all")).thenReturn(peliculasAllTarget);
  //     when(peliculasAllTarget.request(MediaType.APPLICATION_JSON)).thenReturn(mock(Invocation.Builder.class));
  //     when(response.getStatus()).thenReturn(Status.OK.getStatusCode());

      //  ventanaModPeliculasUnderTest.setPeliListModel(new DefaultListModel<Pelicula>());
    }


	@SuppressWarnings("deprecation")
	@After
	public void tearDown() throws Exception {
		server.stop();
	}
    @SuppressWarnings("unchecked")
	@Test
    public void testBtnGetPeliculasActionPerformed() {
        // Mock the list of movies to be returned by the server
        List<Pelicula> peliculas = new ArrayList<>();
        peliculas.add(new Pelicula("Title 1", "Category 1", 9.99, "2023-05-21", "Description 1"));
        peliculas.add(new Pelicula("Title 2", "Category 2", 8.99, "2023-05-22", "Description 2"));

        // Configure the mocks for the web targets and response
        when(peliculasAllTarget.request(MediaType.APPLICATION_JSON)).thenReturn(mock(Invocation.Builder.class));
        when(((Invocation.Builder) peliculasAllTarget.request(MediaType.APPLICATION_JSON)).get(any(GenericType.class))).thenReturn(peliculas);

        // Invoke the button action event
        ventanaModPeliculasUnderTest.btnGetPeliculasActionPerformed(null);

        // Verify that the movie list is updated with the retrieved movies
        assertEquals(2, ventanaModPeliculasUnderTest.getPeliListModel().size());
    }

    /*
    @Test
    public void testBtnEliminarPeliculaActionPerformed() {
        // Mock the selected movie
        Pelicula selectedPelicula = new Pelicula("Title 1", "Category 1", 9.99, "2023-05-21", "Description 1");

        // Add the selected movie to the list model
        DefaultListModel<Pelicula> peliListModel = ventanaModPeliculasUnderTest.getPeliListModel();
        peliListModel.addElement(selectedPelicula);

        // Set the selected movie in the JList
        ventanaModPeliculasUnderTest.setListSelection(selectedPelicula);

        // Mock the deleteTarget and configure its behavior
        WebTarget deleteTarget = pelisTarget.path("/{"+selectedPelicula.getNombrePelicula()+"}");
        when(pelisTarget.path(selectedPelicula.getNombrePelicula())).thenReturn(deleteTarget);

        Invocation.Builder deleteRequest = mock(Invocation.Builder.class);
        when(deleteTarget.request()).thenReturn(deleteRequest);
        Invocation deleteInvocation = mock(Invocation.class);
        when(deleteRequest.buildDelete()).thenReturn(deleteInvocation);

        // Mock the response
        Response response = mock(Response.class);
        when(deleteInvocation.invoke()).thenReturn(response);

        // Stub the setSelectedValue method
        doNothing().when(listaPelis).setSelectedValue(any(), anyBoolean());

        // Invoke the button action event
        ventanaModPeliculasUnderTest.btnEliminarPeliculaActionPerformed(null);

        // Verify that the movie is removed from the list model
        assertEquals(0, peliListModel.size());

        // Verify that the delete operation was invoked
        verify(deleteInvocation, times(1)).invoke();
    }
*/







}
