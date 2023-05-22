package es.deusto.spq.cliente;


import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.server.Main;

public class MainTest {

	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@Before
	public void setUp() {
		new Main();
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testMain() {
		try {
			Main.main(new String[0]);
		} catch (Exception e) {
			logger.log(Level.WARNING, "IOException", e);
		}
	}
}
