package es.deusto.spq.server;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

@Path("server")
@Produces(MediaType.APPLICATION_JSON)
public class RemoteFacade {

	private DBManager dbmanager = null;
	private Logger logger = Logger.getLogger(RemoteFacade.class.getName());

	public RemoteFacade() {
		this.dbmanager = DBManager.getInstance();
	}

	private static final long serialVersionUID = 1L;
	private static RemoteFacade instance;

}