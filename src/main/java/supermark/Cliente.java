package supermark;

public class Cliente {
	
	private String nombre;
	private String apellido;
	private long dni;
	private Domicilio domicilio;
	private int edad;
	private int id; 
	
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
}