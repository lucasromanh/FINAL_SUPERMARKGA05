package supermark;

import java.sql.Timestamp;
import java.util.*;

public class Comprobante {
	private Integer id;
	private Float total;
	private Timestamp fecha;
	private HashMap <Integer , Detalle > detalle;
	private Cliente destinatario;
	private String Tipo;
	private MetodosdePago metododepago;
	
	private HashMap <Integer , Detalle >  convertir (ArrayList <Detalle> detalles){
		HashMap <Integer , Detalle > det = new HashMap <Integer , Detalle > ();
		for (int i=0; i < detalles.size();i++) {
			det.put(i,detalles.get(i));
		}
		return det;
	}
	

	



	public Comprobante(Integer id, Float total, Timestamp fecha, String tipo) {
		super();
		this.id = id;
		this.total = total;
		this.fecha = fecha;
		Tipo = tipo;
	}






	public Comprobante(Integer id, Float total, Timestamp fecha,HashMap <Integer , Detalle > detalle, Cliente destinatario, String tipo) {
		
		super();
		this.id = id;
		this.total = total;
		this.fecha = fecha;
		this.destinatario = destinatario;
		this.Tipo = tipo;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	public Cliente getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(Cliente destinatario) {
		this.destinatario = destinatario;
	}
	public HashMap <Integer , Detalle > getDetalle() {
		return detalle;
	}
	public void setDetalle(ArrayList <Detalle>  detalle) {
		this.detalle = convertir (detalle);
	}
	
	public void setDetalle(HashMap <Integer , Detalle> detalle ) {
		this.detalle =  (detalle);
	}
	
//	public void compra_producto(double precio, int cantidad,String nomProducto, String marca){
//		double pago=precio*cantidad;
//		double iva=0.21;
//		double pagoProducto,impuesto;
//		impuesto=pago*iva;
//		pagoProducto= pago+impuesto;
//		System.out.println(nomProducto+" "+marca+"\n"+cantidad+"x"+precio+
//				"     "+pagoProducto);
//		
//	}

	public void encabezadoComprobante( ) {

		System.out.println("-------------COMPROBANTE "+getTipo()+"------------");
		System.out.println("SUPERMARK"+"\n"+ "30-406728882-12"+ "\n"
		+"Parque General Belgrano"+"\n"+"Salta"+
		"\n"+"4400"+"\n"+"387449473"+
		"------------------------------"+"\n Comprobante: "+getId()+
		"\n Fecha: "+ getFecha() +
		"\n ------------------------------");
		

	}
	public MetodosdePago getMetododepago() {
		return metododepago;
	}
	public void setMetododepago(MetodosdePago metododepago) {
		this.metododepago = metododepago;
	}
	}

