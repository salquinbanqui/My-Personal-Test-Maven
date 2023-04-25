package es.deusto.spq.pojo;

import javax.jdo.annotations.PersistenceAware;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
@PersistenceAware
public class Pelicula {

	private String nombrePelicula;
	private String categoria;
	private double precio;
	private String fecha;
	private String descripcion;
	
	
	public Pelicula(String nombrePelicula, String categoria, double precio, String fecha, String descripcion) {
		super();
		this.nombrePelicula = nombrePelicula;
		this.categoria = categoria;
		this.precio = precio;
		this.fecha = fecha;
		this.descripcion = descripcion;
	}
	
	
	
	public Pelicula() {
		super();
		this.nombrePelicula = "";
		this.categoria = "";
		this.precio = 0;
		this.fecha = "";
		this.descripcion = "";
	}



	public String getNombre() {
		return nombrePelicula;
	}



	public void setNombre(String nombre) {
		this.nombrePelicula = nombre;
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
		return "Pelicula [nombre=" + nombrePelicula + ", categoria=" + categoria + ", precio=" + precio + ", fecha=" + fecha
				+ ", descripcion=" + descripcion + "]";
	}
	
	
	
	
	
	
	
}
