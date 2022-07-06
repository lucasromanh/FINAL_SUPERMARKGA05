package supermark;

import java.util.*;

public class PagoTarjeta extends MetodosdePago{
	
	

	private String banco_emisor;
	private Integer numero_tarjeta;
	private String nombre_tarjeta;
	private Date fecha_vencimiento;
	private Integer Cod_Seg;
	private Integer cuotas;
	
	
	public PagoTarjeta( 
			String banco_emisor, Integer numero_tarjeta, String nombre_tarjeta, Date fecha_vencimiento, Integer cod_Seg,
			Float monto_a_pagar, Integer cuotas) {
		super();
		
	
		this.banco_emisor = banco_emisor;
		this.numero_tarjeta = numero_tarjeta;
		this.nombre_tarjeta = nombre_tarjeta;
		this.fecha_vencimiento = fecha_vencimiento;
		Cod_Seg = cod_Seg;
		this.cuotas = cuotas;
	}
	
	public String getBanco_emisor() {
		return banco_emisor;
	}
	public void setBanco_emisor(String banco_emisor) {
		this.banco_emisor = banco_emisor;
	}
	public Integer getNumero_tarjeta() {
		return numero_tarjeta;
	}
	public void setNumero_tarjeta(Integer numero_tarjeta) {
		this.numero_tarjeta = numero_tarjeta;
	}
	public String getNombre_tarjeta() {
		return nombre_tarjeta;
	}
	public void setNombre_tarjeta(String nombre_tarjeta) {
		this.nombre_tarjeta = nombre_tarjeta;
	}
	public Date getFecha_vencimiento() {
		return fecha_vencimiento;
	}
	public void setFecha_vencimiento(Date fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}
	public Integer getCod_Seg() {
		return Cod_Seg;
	}
	public void setCod_Seg(Integer cod_Seg) {
		Cod_Seg = cod_Seg;
	}

	public Integer getCuotas() {
		return cuotas;
	}
	public void setCuotas(Integer cuotas) {
		this.cuotas = cuotas;
	}

}
