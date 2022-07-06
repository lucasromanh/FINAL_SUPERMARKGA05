package com.supermark.servicios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import supermark.Comprobante;
import supermark.Detalle;
import supermark.Cliente;


public class CRUDComprobante {
	private ConexionBDD conexion;
	private String sql;
	
	public CRUDComprobante() {
		super();
		this.conexion = new ConexionBDD ("supermark");
		this.conexion.connect();
		this.sql = "";
	}
	
	public void registrarCompra(Comprobante comprobante) {
		HashMap<Integer, Detalle> detalles = comprobante.getDetalle();
		this.sql = "INSERT INTO comprobante "+
				"(fecha,id_cliente,id_metododepago) "+
				"VALUE ('"+
				
				comprobante.getFecha()+"',"+
				comprobante.getDestinatario().getId()+","+
				comprobante.getMetododepago().getId()+","+
				")";
		try {
			int row = conexion.getStmt().executeUpdate(sql);
			agregarDetallesAComprobante(detalles,row);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			System.out.println("Comprobante registrado");
		}
	}
	
	
	public void agregarDetallesAComprobante(HashMap<Integer, Detalle> detalles,Integer id_comprobante) {
		CRUDDetalle cd = new CRUDDetalle();
		CRUDProducto cp = new CRUDProducto();
		for(Detalle det:detalles.values()) {
			if(det.getProducto().getStock()-det.getCantidad()>=0) {
				cd.registrarDetalle(det, id_comprobante);
				cp.actualizarStock(det.getProducto(), -det.getCantidad());
			}else {
				System.out.println("No se dispone del stock necesario para realizar esta venta");
			}
		}
	}
	
	// PORCENTAJE DESCUENTO 
	
	public ArrayList<Comprobante> getComprobantes(Cliente cliente) {
		ArrayList<Comprobante> comprobantes = new ArrayList<Comprobante>();
		this.sql = "SELECT * FROM comprobante WHERE id_usuario ="+
					cliente.getDNI();
		try {
			conexion.setRs(conexion.getStmt().executeQuery(sql));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			System.out.println("Comprobantes del Usuario:");
			ResultSet rs = conexion.getRs();
			try {
				CRUDDetalle cd = new CRUDDetalle();
				while (rs.next()) {
					Comprobante comp = new Comprobante( 1, 12f, null, null, cliente, sql);
					comp.setId(rs.getInt("id"));
					comp.setTotal(rs.getFloat("total"));
					comp.setFecha(rs.getTimestamp("fecha"));
					comp.setTipo(rs.getString ("Tipo)"));
					comp.setDetalle(cd.getLineasDetalle(comp));
					
					comprobantes.add(comp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return comprobantes;
	}
}
