package es.deusto.spq.pojo;

import javax.jdo.annotations.PersistenceAware;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
@PersistenceAware
public class Pelicula {
	private static int idd = 0;
	
	private int id;
	private String nombrePelicula;
	private String categoria;
	private double precio;
	private String fecha;
	private String descripcion;
	
	
	public Pelicula(String nombrePelicula, String categoria, double precio, String fecha, String descripcion) {
		super();
		this.id = ++idd;
		this.nombrePelicula = nombrePelicula;
		this.categoria = categoria;
		this.precio = precio;
		this.fecha = fecha;
		this.descripcion = descripcion;
	}
	
	
	
	public Pelicula() {
		super();
		this.id = idd;
		this.nombrePelicula = "";
		this.categoria = "";
		this.precio = 0;
		this.fecha = "";
		this.descripcion = "";
	}


	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombrePelicula() {
		return nombrePelicula;
	}



	public void setNombrePelicula(String nombre) {
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
		return "[ "+fecha + " ]: "+nombrePelicula + "\t| cat.: " + categoria + "\t| prec.: " + precio + "€" + 
				"\t| desc.: " + descripcion + ".";
	}
	
	
	
	
	
	
	
}
