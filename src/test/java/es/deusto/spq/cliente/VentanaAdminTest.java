package es.deusto.spq.cliente;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.cliente.gui.VentanaAdmin;

public class VentanaAdminTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		try {
			VentanaAdmin va = new VentanaAdmin(null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	}