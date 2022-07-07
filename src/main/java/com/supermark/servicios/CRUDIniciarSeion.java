package com.supermark.servicios;

import java.sql.SQLException;

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
	}
	
