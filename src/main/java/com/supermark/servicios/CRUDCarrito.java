package com.supermark.servicios;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import supermark.Carrito;
import supermark.Cliente;
import supermark.Detalle;
import supermark.Producto;

public class CRUDCarrito {
	private ConexionBDD conexion;
	private String sql;
	
public  CRUDCarrito () {
	super();
	this.conexion = new ConexionBDD ("supermark_ga05");
	this.conexion.connect();
	this.setSql("");
}


private void setSql(String string) {

	
}


public boolean agregar(Carrito carrito) {
	boolean resultado = false;
	if(getListado(carrito.getUsuario()).size()<30) {
		this.sql = "INSERT INTO Carrito "+
				"(id_usuario,id_producto,cantidad) "+
				"VALUE ("+
				carrito.getUsuario().getId()+","+
				carrito.getProducto().getId()+","+
				carrito.getCantidad()+")";
	}
		
		try {
			int filas = conexion.getStmt().executeUpdate(sql);
			if(filas>0){
				resultado = true;
			}
			System.out.println("Producto agregado al Carrito");
		} catch (SQLException e) {
			e.printStackTrace();
		}  {
		System.out.println("El carrito esta lleno");
	}

	return resultado;
}




public ArrayList<Detalle> getListado(Cliente cliente){
	ArrayList<Detalle> detalles = new ArrayList<Detalle>();
	
	this.sql = "SELECT Producto.nombre,Carrito.id_producto,Carrito.cantidad,Producto.precio FROM Carrito "+
				"JOIN producto ON producto.id = carrito.id_producto "+
				"WHERE id_usuario = "+cliente.getId();
	
	try {
		ResultSet rs = conexion.getStmt().executeQuery(sql);
		while(rs.next()) {
			int id_producto = rs.getInt("id_producto");
			int cantidad = rs.getInt("cantidad");
			String nombre = rs.getString("nombre");
			float precio = rs.getFloat("precio");
			detalles.add(new Detalle(
						new Producto(id_producto,nombre,precio),
						cantidad
					));
		}	
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return detalles;
}

public boolean vaciar(Cliente cliente){
	boolean resultado = false;
	this.sql = "DELETE FROM Carrito WHERE id_usuario = "+cliente.getId();
	
	try {
		int filas = conexion.getStmt().executeUpdate(sql);
		if(filas>0) {
			resultado = true;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return resultado;
}
}

