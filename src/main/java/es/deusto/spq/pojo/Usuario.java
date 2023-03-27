package es.deusto.spq.pojo;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Usuario {
	public static int idd = 0;
	
	public int id;
	private String nombreUsuario;
	private String password;
	private String email;
	private String tarjeta;

	
	

	
	public Usuario(String nombreUsuario, String email, String password, String tarjeta) {
		super();
		this.id = ++idd;
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.password = password;
		this.tarjeta = tarjeta;
	}
	
	public Usuario() {
		super();
		this.nombreUsuario = "";
		this.email = "";
		this.password = "";
		this.tarjeta = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombreUsuario=" + nombreUsuario + ", email=" + email
				 + ", password=" + password + ", tarjeta=" + tarjeta + "]";
	}
	
	
	
	
}
