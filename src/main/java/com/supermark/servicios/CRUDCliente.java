package com.supermark.servicios;
import java.sql.SQLException;
import supermark.Cliente;


public class CRUDCliente {
	private ConexionBDD conexion;
	private String sql;
	
	public CRUDCliente() {
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
	
	public void insertar (Cliente cliente)  {
		this.sql =  "INSERT INTO cliente" + " (nombre,apellido,dni,id_domicilio,edad,id) " +
				 "VALUE ( ' " + cliente.getNombre () + "', '" + cliente.getApellido() + 
				"',  " + cliente.getDNI () + ", " + cliente.getDomicilio().getId() + ",  " + cliente.getEdad() + " ,"
		+ cliente.getId() +"  )"; 
		try {
			conexion.getStmt().executeUpdate(this.sql);
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println ("Cliente registrado");
			
		}
	
	}
	public void eliminar (Cliente cliente)  {
		this.sql =  "DROP INTO cliente" + " (nombre,apellido,dni,id_domicilio,edad,id) " +
				 "VALUE ( ' " + cliente.getNombre () + "', '" + cliente.getApellido() + 
				"',  " + cliente.getDNI () + ", " + cliente.getDomicilio().getId() + ",  " + cliente.getEdad() + " ,"
		+ cliente.getId() +"  )"; 
		try {
			conexion.getStmt().executeUpdate(this.sql);
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println ("CLiente eliminado");
			
		}
	
	}
	
	public void modificar (Cliente cliente)  {
		this.sql =  "ALTER INTO cliente" + " (nombre,apellido,dni,id_domicilio,edad,id) " +
				 "VALUE ( ' " + cliente.getNombre () + "', '" + cliente.getApellido() + 
				"',  " + cliente.getDNI () + ", " + cliente.getDomicilio().getId() + ",  " + cliente.getEdad() + " ,"
		+ cliente.getId() +"  )"; 
		try {
			conexion.getStmt().executeUpdate(this.sql);
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println ("Cliente Modificado");
			
		}
	
	}
	
}
 


