package supermark;

public class Carrito {
	private Integer id;
	private Cliente cliente;
	private Producto producto;
	private Integer cantidad;
	
	public Carrito(Cliente cliente, Producto producto, Integer cantidad) {
		super();
		this.cliente = cliente;
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public Cliente getUsuario() {
		return cliente;
	}

	public void setUsuario(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
}
