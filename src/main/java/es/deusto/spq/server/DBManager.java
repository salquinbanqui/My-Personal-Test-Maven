package es.deusto.spq.server;

import java.sql.Connection;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

public class DBManager {

	private static DBManager instance = null;
	private PersistenceManagerFactory pmf = null;
	private static boolean inicializado = false;
	private static Connection conn;

	private DBManager() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	/**
	 * Obtencion de la instancia del DBManager, si no existe la crea y si existe la devuelve
	 * @return instancia de DBManager
	 */

	public static DBManager getInstance() {
//		if (instance == null) {
		instance = new DBManager();
//			System.out.println("Nuevo DBManager");
//		}

		return instance;
	}

	public void storeObjectInDB(Object object) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();


		try {
			tx.begin();
			pm.makePersistent(object);
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error storing an object: " + ex.getMessage());
			System.out.println("Object:" + object);
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

	public void deleteObjectFromDB(Object object) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();

			pm.deletePersistent(object);

			tx.commit();
		} catch (Exception ex) {
			System.out.println(" $ Error deleting an object: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		}

}