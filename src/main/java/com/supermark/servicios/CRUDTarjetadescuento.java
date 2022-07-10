package com.supermark.servicios;

import java.sql.ResultSet;
import java.sql.SQLException;


import supermark.Par;
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
	
	public TarjetaDescuento Crear (TarjetaDescuento tarjetadescuento)  {
		this.sql =  "INSERT INTO tarjetadescuento" + " (id,puntos) " +
				 "VALUE (  " + tarjetadescuento.getId_td () + ", " + tarjetadescuento.getPuntos() + 
				" )"; 
		try {
			conexion.getStmt().executeUpdate(this.sql);
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println ("Tarjetadescuento Creada");
			
		}
		return tarjetadescuento;
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
						rs.getInt("cliente")
						
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return td;
	}

	public void actualizarpuntos(TarjetaDescuento tarjeta) {
		int cantidad = sumar_puntos (tarjeta);
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
	
	private int sumar_puntos(TarjetaDescuento tarjeta) {
		Par par = this.porcentaje(tarjeta);
		if (par.getPrimer() <5) {
			par.setPrimer(par.getPrimer()+1);
		} else {
			par.setPrimer(1);
		}
		return par.getPrimer();
	}
	
	
	
	
public Par porcentaje (TarjetaDescuento tarjetadescuento) {
	Par par = null;
	double descuento = -1;
	int puntaje = 0;
	this.sql = "SELECT puntos FROM tarjetadescuento WHERE id_cliente ="+tarjetadescuento.getCliente().getId();
	try {
		ResultSet rs = conexion.getStmt().executeQuery(sql);	
			while (rs.next())
			{ 
				puntaje = rs.getInt("puntos");
			}
		if ( puntaje == 5) {
		descuento = 0.1;
			} else {
				descuento = 0;
			}
		par = new Par (puntaje, descuento);
		}
			catch (SQLException e) {
				e.printStackTrace();
			}
			
	return par;
	} 
}



			



//SELECT puntos FROM tarjedescuento WHERE id_cliente = "cliente.getId()"; 
