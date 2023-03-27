package es.deusto.spq.cliente;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.apache.log4j.Logger;

public class ServiceLocator {

	private Client c;
	private static Logger logger = Logger.getLogger(ServiceLocator.class.getName());
	private WebTarget wt;
	
	
	public ServiceLocator() {
		c = ClientBuilder.newClient();
		wt = c.target(cogerUrl());
	}
	
	public static String cogerUrl() {
		GetProperties gp = new GetProperties();
		String url = " ";
		
		try {
			url = gp.getUrl();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
}