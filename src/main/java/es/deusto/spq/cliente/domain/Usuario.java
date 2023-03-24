package es.deusto.spq.cliente.domain;

public class Usuario {
	private String id;
	private String nombreUsuario;
	private String password;
	private String email;
	private String tarjeta;
	
	
	public Usuario(String nombreUsuario, String password, String email, String tarjeta) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.email = email;
		this.tarjeta = tarjeta;
	}
	
	public Usuario() {
		super();
		this.nombreUsuario = "";
		this.password = "";
		this.email = "";
		this.tarjeta = "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
		return "Usuario [id=" + id + ", nombreUsuario=" + nombreUsuario + ", password=" + password + ", email=" + email
				+ ", tarjeta=" + tarjeta + "]";
	}
	
	
	
	
}
