package com.supermark.servicios;

import java.sql.SQLException;

import supermark.Comprobante;


public class CRUDTransferencia {
	private ConexionBDD conexion;
	private String sql;
	
	public CRUDTransferencia() {
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
		
		this.sql = "INSERT INTO transferencia (cvu,cbu,alias,id_comprobante,id_cliente) "+
				"VALUE ('"+comprobante.getMetododepago().getTransferencia().getCvu()+"', "+
				comprobante.getMetododepago().getTransferencia().getCbu()+",'"+
				comprobante.getMetododepago().getTransferencia().getAlias()+"', "+
				comprobante.getId()+","+
				comprobante.getDestinatario().getId()+")";
		try {
			conexion.getStmt().executeUpdate(this.sql);
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println ("Pago con Transferencia aceptado");
			
		}
	
	}
}

