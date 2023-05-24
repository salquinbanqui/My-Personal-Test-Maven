package es.deusto.spq.server;

import static org.junit.Assert.*;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import es.deusto.spq.pojo.Usuario;
import es.deusto.spq.server.DBManager;
import es.deusto.spq.server.RemoteFacade;

public class RemoteFacadeTest {

	RemoteFacade rf = new RemoteFacade();
	
	Usuario u = new Usuario("jose","jose@gmail.com","1234","nº_tarjeta",false);
	
	@Rule 
	public ContiPerfRule i = new ContiPerfRule();
	
	@Mock
	DBManager db;
	
	@Test
	@PerfTest(invocations = 10, threads =2)
	@Required(max = 1200, average = 250)
	public void testloginGestionPelis() {
		assertNotNull(rf.loginGestionPelis(u));
	}

/*	@Test
	@PerfTest(invocations = 10, threads =2)
	@Required(max = 1200, average = 250)
	public void testagregarUsuarioGestionPelis() {
		assertNotNull(rf.agregarUsuarioGestionPelis(u));
	}
	
	@Test
	@PerfTest(invocations = 10, threads =2)
	//@Required(max = 1200, average = 250)
	public void testlogoutGestionPelis() {
		assertNotNull(rf.logoutGestionPelis(null));
	}*/
}
