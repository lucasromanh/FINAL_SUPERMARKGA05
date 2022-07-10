package supermark;



public class IniciarSesion extends Registro {
	
	
	public IniciarSesion(Cliente cliente, Domicilio domicilio, int id, String mail, String contraseña, int usuario,
			String clave) {
		super(cliente, domicilio, id, mail, contraseña);
		this.usuario = usuario;
		this.clave = clave;
	}


	private int usuario;
	public int getUsuario() {
		return usuario;
	}


	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}


	public String getClave() {
		return clave;
	}


	public void setClave(String clave) {
		this.clave = clave;
	}


	private String clave;



}
