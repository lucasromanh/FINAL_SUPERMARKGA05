package com.supermark.servicios;
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.HashMap;
//
//import supermark.Comprobante;
//import supermark.Detalle;
import supermark.MetodosdePago;
//import supermark.PagoTarjeta;
//import supermark.Producto;
//import supermark.Transferencia;

public class CRUDMetodosdePago {
		private ConexionBDD conexion;
		private String sql;
		
		public CRUDMetodosdePago() {
			super();
			this.conexion = new ConexionBDD ("supermark_ga05");
			this.conexion.connect();
			this.sql = "";
		}
		
		public MetodosdePago getMetodosdePago (MetodosdePago metododepago, MetodosdePago CRUDMetodosdePago){
			CRUDMetodosdePago = new MetodosdePago();
			this.sql = "SELECT * FROM MetododePago WHERE id_metododepago ="+
					metododepago.getId();
	
			ResultSet rs;
			try {
				rs = conexion.getStmt().executeQuery(sql);
				while (rs.next()) {
					metododepago = new MetodosdePago ();}
				
				if (metododepago.getId() == 1) {
					System.out.println ("Se selecciono Tarjeta como metodo de pago");
				} else {
					System.out.println ("Se selecciono Transferencia como metodo de pago");
				}
			}	
			
				
			 catch (SQLException e) {
				e.printStackTrace();
			}
			return metododepago;
			
			
		}
}

