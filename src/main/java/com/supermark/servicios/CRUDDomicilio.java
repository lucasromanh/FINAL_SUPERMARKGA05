package com.supermark.servicios;

import java.sql.SQLException;

import supermark.Domicilio;

public class CRUDDomicilio {
	private ConexionBDD conexion;
	private String sql;
	
	public CRUDDomicilio() {
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
	
	public void insertar (Domicilio domicilio)  {
		this.sql =  "INSERT INTO domicilio" + " (barrio,calle,numero,piso,depNum) " +
				 "VALUE ( ', '" + domicilio.getBarrio() + 
				"',  '" + domicilio.getCalle () + " ', " + domicilio.getNumero() + ",  " + domicilio.getPiso() + ",  " + 
				 domicilio.getDepNum() + " ,"
		+"  )"; 
		try {
			conexion.getStmt().executeUpdate(this.sql);
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println ("Domicilio registrado");
			
		}
	
	}
	
	
}
 