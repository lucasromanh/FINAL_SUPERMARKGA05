package supermark;


import java.sql.Date;

public class MetodosdePago {
	
	
	private PagoTarjeta Tarjeta;
	private Transferencia transferencia;
	private Float monto_a_pagar;
	private Object Cliente;
	private Date FechaPago; 
	private int id;
	
	

	public MetodosdePago( PagoTarjeta tarjeta, Transferencia transferencia,
			Float monto_a_pagar, supermark.Cliente cliente, Date fechaPago) {
		super();
		
		Tarjeta = tarjeta;
		this.transferencia = transferencia;
		this.monto_a_pagar = monto_a_pagar;
		Cliente = cliente;
		FechaPago = fechaPago;
	}
	public Float getMonto_a_pagar() {
		return monto_a_pagar;
	}
	public void setMonto_a_pagar(Float monto_a_pagar) {
		this.monto_a_pagar = monto_a_pagar;
	}
	public Object getCliente() {
		return Cliente;
	}
	public void setCliente(Cliente cliente) {
		Cliente = cliente;
	}
	public Date getFechaPago() {
		return FechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		FechaPago = fechaPago;
	}
	
	public MetodosdePago() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PagoTarjeta getTarjeta() {
		return Tarjeta;
	}
	public void setTarjeta(PagoTarjeta tarjeta) {
		Tarjeta = tarjeta;
	}
	public Transferencia getTransferencia() {
		return transferencia;
	}
	public void setTransferencia(Transferencia transferencia) {
		this.transferencia = transferencia;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	

}
