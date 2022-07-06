package com.supermark.servicios;
import supermark.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;

import supermark.TarjetaDescuento;

public class CRUDTarjetadescuento {
	private ConexionBDD conexion;
	private String sql;
	
	public CRUDTarjetadescuento() {
		super();
		this.conexion = new ConexionBDD ("supermark");
		this.conexion.connect();
		this.sql = "";
	}
	
	public TarjetaDescuento getTarjetaDescuento(Integer id) {
		TarjetaDescuento td = null;
		this.sql = "SELECT * FROM tarjetadescuento WHERE id ="+id;
		try {
			ResultSet rs = conexion.getStmt().executeQuery(sql);
			while (rs.next()) {
				td = new TarjetaDescuento(
						rs.getInt("id_td"),
						rs.getInt("puntos"),
						rs.getCliente()get.Id_td();
						
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return td;
	}

	public void actualizarpuntos(TarjetaDescuento tarjeta,int cantidad) {
		this.sql = "UPDATE tarjeta SET tarjeta.puntos="+
				(tarjeta.getPuntos()+cantidad)+
				" WHERE tarjeta.id="+tarjeta.getId_td();
		try {
			conexion.getStmt().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			System.out.println("Puntos Actualizados");
		}
	}
	
	public int sumar_puntos(TarjetaDescuento tarjeta, Integer id_Comprobante) {
		int cantidaddepuntos = 0;
		if (id_Comprobante>0) {
			cantidaddepuntos = tarjeta.getPuntos() + 1;
		} else {
			cantidaddepuntos = tarjeta.getPuntos() ;
		}
		return cantidaddepuntos;
	}
	
	
	
	


}
