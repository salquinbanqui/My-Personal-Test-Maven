package es.deusto.spq.pojo;

import javax.jdo.annotations.PersistenceAware;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
@PersistenceAware
public class Usuario {
	public static int idd = 0;
	
	public int id;
	private String nombreUsuario;
	private String email;
	private String password;
	private String tarjeta;
	private boolean admin;
	
	
	
	public Usuario(String nombreUsuario, String email, String password, String tarjeta, boolean admin) {
		super();
		this.id = ++idd;
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.password = password;
		this.tarjeta = tarjeta;
		this.admin = admin;
	}
	
	public Usuario() {
		super();
		this.nombreUsuario = "";
		this.email = "";
		this.password = "";
		this.tarjeta = "";
		this.admin = false;
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
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombreUsuario=" + nombreUsuario + ", email=" + email
				 + ", password=" + password + ", tarjeta=" + tarjeta +", admin = "+ admin+  "]";
	}
	
	
	
	
}
