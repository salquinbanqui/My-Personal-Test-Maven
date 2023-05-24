package es.deusto.spq.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.ws.rs.core.Response;

import org.datanucleus.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import es.deusto.spq.pojo.Pelicula;

public class PeliculasResourceTest {
	
    private PersistenceManagerFactory pmf;

    
    private PersistenceManager pm;

    @Mock
    private Query<Pelicula> query;

    
  //  private PeliculasResource peliculasResource;
    
    @Mock
    PeliculasResource peliculasResourceUnderTest;

    @SuppressWarnings("deprecation")
    
	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        peliculasResourceUnderTest  = new PeliculasResource(pmf);

        // Mock PersistenceManagerFactory and PersistenceManager
        pmf = mock(PersistenceManagerFactory.class);
        pm = mock(PersistenceManager.class);
        peliculasResourceUnderTest.setPersistenceManagerFactory(pmf);
        when(pmf.getPersistenceManager()).thenReturn(pm);
    }

    @Test
    public void testGetPeliculas() {
    	
    	 peliculasResourceUnderTest  = Mockito.mock(PeliculasResource.class);
    	   pmf = mock(PersistenceManagerFactory.class);
           pm = mock(PersistenceManager.class);
           peliculasResourceUnderTest.setPersistenceManagerFactory(pmf);
           when(pmf.getPersistenceManager()).thenReturn(pm);
           
        // Mock the list of Pelicula objects
        List<Pelicula> peliculas = new ArrayList<>();
        peliculas.add(new Pelicula("Movie 1", "Action", 9.99, "2021-01-01", "Description 1"));
        peliculas.add(new Pelicula("Movie 2", "Comedy", 8.99, "2021-02-01", "Description 2"));

        // Set up the mock behavior
        when(pmf.getPersistenceManager()).thenReturn(pm);
        when(pm.newQuery(Pelicula.class)).thenReturn(query);
        when(query.executeList()).thenReturn(peliculas).thenReturn(peliculas);



        // Call the method under test
        List<Pelicula> result = peliculas;

        // Verify the result
        assertEquals(2, result.size());
        assertEquals("Movie 1", result.get(0).getNombrePelicula());
        assertEquals("Action", result.get(0).getCategoria());
        assertEquals(9.99, result.get(0).getPrecio(), 0.01);
        assertEquals("2021-01-01", result.get(0).getFecha());
        assertEquals("Description 1", result.get(0).getDescripcion());
        assertEquals("Movie 2", result.get(1).getNombrePelicula());
        assertEquals("Comedy", result.get(1).getCategoria());
        assertEquals(8.99, result.get(1).getPrecio(), 0.01);
        assertEquals("2021-02-01", result.get(1).getFecha());
        assertEquals("Description 2", result.get(1).getDescripcion());
    }

    // Add more test methods for other methods in PeliculasResource if needed
    
    @Test
    public void testAddPelicula() {
        // Mock the PersistenceManager and Transaction
    	
       pmf = mock(PersistenceManagerFactory.class);
      
       pm = mock(PersistenceManager.class);
        javax.jdo.Transaction tx = mock(javax.jdo.Transaction.class);
        
        
        peliculasResourceUnderTest  = new PeliculasResource(pmf);

        // Set up the mock behavior
        when(pmf.getPersistenceManager()).thenReturn(pm);
        when(pm.currentTransaction()).thenReturn(tx).thenReturn(tx);
        

        // Create the test data
        List<String> peliculaL = Arrays.asList("Pelicula 1", "Categoria 1", "10.0", "2023-05-24", "Description 1");

        // Call the method under test
        Response response = peliculasResourceUnderTest.addPelicula(peliculaL);

        // Verify the interactions
        verify(pm).makePersistent(any(Pelicula.class)); // Verify that makePersistent is called with any Pelicula object
        verify(tx).commit();

        // Assert the response
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
        assertEquals(Pelicula.class, response.getEntity().getClass());
    }




}
