package supermark;


public class Registro {
	private Cliente cliente;
	private Domicilio domicilio;
	private int id;
	private String mail;
	private String contraseña;
	
	


	public Registro(Cliente cliente, Domicilio domicilio, int id, String mail, String contraseña) {
		super();
		this.setCliente(cliente);
		this.setDomicilio(domicilio);
		this.id = id;
		this.mail = mail;
		this.contraseña = contraseña;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getContraseña() {
		return contraseña;
	}


	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Domicilio getDomicilio() {
		return domicilio;
	}


	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}
	
}
