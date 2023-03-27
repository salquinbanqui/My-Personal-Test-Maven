package es.deusto.spq.cliente;

public class Main {
	public static void main(String[] args) {

	ServiceLocator servicelocator = new ServiceLocator();
	Controller controller = new Controller(servicelocator);
	
	
	}
	
}