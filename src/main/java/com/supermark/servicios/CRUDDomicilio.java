package com.supermark.servicios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	public Domicilio insertar (Domicilio domicilio)  {
		
		this.sql =  "INSERT INTO domicilio" + " (barrio,calle,numero,piso,depNum) " +
				 "VALUE ( ', '" + domicilio.getBarrio() + 
				"',  '" + domicilio.getCalle () + " ', " + domicilio.getNumero() + ",  " + domicilio.getPiso() + ",  " + 
				 domicilio.getDepNum() + " ,"
		+"  )"; 
		try {
			PreparedStatement stmt = conexion.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int filas = stmt.executeUpdate();
			if(filas>0) {
				ResultSet rs = stmt.getGeneratedKeys();
				while(rs.next()) {
					domicilio.setId(rs.getInt(1));
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			System.out.println ("Domicilio registrado");
			
		} return domicilio;
	
	}

	public Domicilio getDomicilio(Integer id) {
		this.sql = "SELECT barrio,calle,numero,piso,depNum FROM domicilio "+
				"WHERE domicilio.id="+id;
		Domicilio dom = null;
		try {
			conexion.setRs(conexion.getStmt().executeQuery(sql));
			dom = new Domicilio(
		
						conexion.getRs().getString("barrio"),
						conexion.getRs().getString("calle"),
						conexion.getRs().getInt("numero"),
						conexion.getRs().getInt("piso"),
						conexion.getRs().getInt("depNum")
						);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dom;
	
	
}

}

 