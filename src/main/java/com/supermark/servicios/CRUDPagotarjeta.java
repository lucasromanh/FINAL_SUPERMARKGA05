package com.supermark.servicios;

import java.sql.SQLException;
import supermark.Comprobante;


public class CRUDPagotarjeta {
	private ConexionBDD conexion;
	private String sql;
	
	public CRUDPagotarjeta() {
		super();
		this.conexion = new ConexionBDD ("supermark_ga05");
		this.conexion.connect();
		this.setSql("");
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public void registrar (Comprobante comprobante )  {
		
		this.sql = "INSERT INTO pagotarjeta (banco_emisor,numero_tarjeta,nombre_tarjeta,fecha_vencimiento,cod_seg,id_comprobante,id_cliente) "+
				"VALUE ('"+comprobante.getMetododepago().getTarjeta().getBanco_emisor()+"', "+
				comprobante.getMetododepago().getTarjeta().getNumero_tarjeta()+",'"+
				comprobante.getMetododepago().getTarjeta().getFecha_vencimiento()+"', "+
				comprobante.getMetododepago().getTarjeta().getCod_Seg()+","+
				comprobante.getId()+","+
				comprobante.getDestinatario().getId()+")";
		try {
			conexion.getStmt().executeUpdate(this.sql);
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println ("Pago con Tarjeta aceptado");
			
		}
	
	}
}	
		
		
		
		
		
		
//INSERT INTO pagotarjeta (cvu,cbu,alias,id_comprobante,id_cliente) 
//VALUE (comprobante.getMeotodoPago().getCVU(),comprobante.getMeotodoPago().getCBU(),
//		comprobante.getMeotodoPago().getAlias(),comprobante.getId(),
//		comprobante.getDestinatario().getId())		
//		
		
		
		
		
		
		
		
		
		
		
		
		
		


