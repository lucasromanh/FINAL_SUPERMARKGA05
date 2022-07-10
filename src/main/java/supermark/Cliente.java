package supermark;

public class Cliente {
	
	private String nombre;
	private String apellido;
	private long dni;
	private Domicilio domicilio;
	private int edad;
	private int id; 
	private int rol;
	private TarjetaDescuento tarjetadescuento;
	


	public Cliente(String nombre, String apellido, long dni, int id) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.id = id;
	}

	public TarjetaDescuento getTarjetadescuento() {
		return tarjetadescuento;
	}

	public void setTarjetadescuento(TarjetaDescuento tarjetadescuento) {
		this.tarjetadescuento = tarjetadescuento;
	}

	public Cliente(TarjetaDescuento tarjetadescuento) {
		super();
		this.tarjetadescuento = tarjetadescuento;
	}

	public Cliente(String nombre, String apellido, long dni, Domicilio domicilio, int edad, int id, int rol) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.domicilio = domicilio;
		this.edad = edad;
		this.id = id;
		this.rol = rol;
	}

	public Cliente(String nom,String ape,long dni,Domicilio dom,int edad, int id) {
		this.nombre = nom;
		this.apellido = ape;
		this.dni = dni;
		this.domicilio = dom;
		this.edad = edad;
		this.id = id;
	}
	
	public Cliente(String nom,String ape,long dni,Domicilio dom) {
		this.nombre = nom;
		this.apellido = ape;
		this.dni = dni;
		this.domicilio = dom;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public Domicilio getDomicilio() {
		return this.domicilio;
	}
	
	public long getDNI() {
		return this.dni;
	}
	
	public void mostrar(){
		System.out.println("Mi nombre es "+this.nombre+" "+this.apellido);
		System.out.println("DNI: "+this.dni+" - Domicilio: "+this.domicilio);
	}
	


	public String getApellido() {
		
		return this.apellido;
	}

	public int getEdad() {
		
		return this.edad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContrasenia() {
		
		return this.getContrasenia();
	}

	public String getmail() {
	
		return null;
	}
	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}
}