package es.deusto.spq.cliente;

import static org.junit.Assert.*;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.junit.Test;
import org.mockito.Mock;

import es.deusto.spq.pojo.Usuario;
import es.deusto.spq.server.DBManager;
import es.deusto.spq.server.RemoteFacade;

public class RemoteFacadeTest {

	RemoteFacade rf = new RemoteFacade();
	
	Usuario u = new Usuario("jose","jose@gmail.com","1234","nº_tarjeta",false);
	
	@Mock
	DBManager db;
	
	@Test
	public void testloginGestionPelis() {
		assertNotNull(rf.loginGestionPelis(u));
	}

	
	@Test
	public void testagregarUsuarioGestionPelis() {
		assertNotNull(rf.agregarUsuarioGestionPelis(u));
	}
}
