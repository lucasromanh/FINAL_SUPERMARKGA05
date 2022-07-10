package com.supermark.servicios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import supermark.Registro;

public class CRUDIniciarSeion {
	private ConexionBDD conexion;
	private String sql;
	
	public CRUDIniciarSeion() {
		super();
		this.conexion = new ConexionBDD ("supermark_ga05");
		this.conexion.connect();
		this.sql = "";
	}
		
		public boolean iniciarSesion (Registro registro) {
			this.sql = "SELECT * FROM registro WHERE registro.mail='"+
					registro.getMail()+
						"' AND registro.contraseña = '"+
					registro.getContraseña()+"'";
			boolean resultado = false;
				try {
					conexion.setRs(conexion.getStmt().executeQuery(sql));
					while(conexion.getRs().next()) {
						resultado = true;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				
			
			}
				return resultado;
			
		}	
		
		public Registro insertar (Registro registro)  {
			
			this.sql =  "INSERT INTO registro" + " (mail,contraseña,id_domicilio,id_cliente) " +
					 "VALUE ( ', '" + registro.getMail() + 
					"',  '" + registro.getContraseña () + " ', " + registro.getDomicilio().getId()+ ",  " + 
					 registro.getCliente().getId() 
			+"  )"; 
			try {
				PreparedStatement stmt = conexion.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int filas = stmt.executeUpdate();
				if(filas>0) {
					ResultSet rs = stmt.getGeneratedKeys();
					while(rs.next()) {
						registro.setId(rs.getInt(1));
					}
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
				
			} finally {
				System.out.println ("Domicilio registrado");
				
			} return registro;
		
		}	
		
		
		
		
	}
	
