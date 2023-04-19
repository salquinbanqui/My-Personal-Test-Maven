package es.deusto.spq.pojo;

public class Pelicula {

	private String nombre;
	private String categoria;
	private double precio;
	private String fecha;
	private String descripcion;
	
	
	public Pelicula(String nombre, String categoria, double precio, String fecha, String descripcion) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.fecha = fecha;
		this.descripcion = descripcion;
	}
	
	
	
	public Pelicula() {
		super();
		this.nombre = "";
		this.categoria = "";
		this.precio = 0;
		this.fecha = "";
		this.descripcion = "";
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getCategoria() {
		return categoria;
	}



	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}



	public double getPrecio() {
		return precio;
	}



	public void setPrecio(double precio) {
		this.precio = precio;
	}



	public String getFecha() {
		return fecha;
	}



	public void setFecha(String fecha) {
		this.fecha = fecha;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	@Override
	public String toString() {
		return "Pelicula [nombre=" + nombre + ", categoria=" + categoria + ", precio=" + precio + ", fecha=" + fecha
				+ ", descripcion=" + descripcion + "]";
	}
	
	
	
	
	
	
	
}
