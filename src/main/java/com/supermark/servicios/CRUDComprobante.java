package com.supermark.servicios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import java.sql.Statement;

import supermark.Comprobante;
import supermark.Detalle;

import supermark.Par;
import supermark.Transferencia;
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
	
	public boolean registrarCompra(Comprobante comprobante) {
		HashMap<Integer, Detalle> detalles = comprobante.getDetalle();
		this.sql = "INSERT INTO comprobante "+
				"(fecha,id_cliente,) "+
				"VALUE ('"+
				
				comprobante.getFecha()+"',"+
				comprobante.getDestinatario().getId()+","+
				")";
		try {
			int row = conexion.getStmt().executeUpdate(sql);
			agregarDetallesAComprobante(detalles,row);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			System.out.println("Comprobante registrado");
		}
		return false;
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
	
	
	
	public ArrayList<Comprobante> getComprobantes(Cliente cliente) {
		ArrayList<Comprobante> comprobantes = new ArrayList<Comprobante>();
		this.sql = "SELECT * FROM comprobante WHERE id_usuario ="+
					cliente.getId();
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

	public Comprobante getComprobante (Integer id) {
		this.sql = "SELECT id,total,tipo,fecha FROM comprobante "+
				"WHERE domicilio.id="+id;
		Comprobante com = null;
		try {
			conexion.setRs(conexion.getStmt().executeQuery(sql));
			com = new Comprobante(
						conexion.getRs().getInt("id"),
						conexion.getRs().getFloat("total"),
						conexion.getRs().getTimestamp("fecha"),
						conexion.getRs().getString("tipo")
						
						);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return com;
	
	}



public boolean registrarCompraconDeescuento(Comprobante comprobante) {
	boolean resultado = false;
	CRUDCarrito ccarr = new CRUDCarrito();
	ArrayList<Detalle> detalles = ccarr.getListado(comprobante.getDestinatario());
	comprobante.setDetalle(detalles);
	double total = 0;
	for(Detalle detalle:detalles) {
		total += detalle.getProducto().getPrecio()*detalle.getCantidad();
	}
	
	CRUDTarjetadescuento desc = new CRUDTarjetadescuento ();
	Par par = desc.porcentaje(comprobante.getDestinatario().getTarjetadescuento()); 
	total = total*(1-par.getSegundo()); //total por 90% 
	this.sql = "INSERT INTO Comprobante "+
			"(tipo,fecha,id_usuario,id_tc,total) "+
			"VALUE ('"+
			comprobante.getTipo()+"','"+
			comprobante.getFecha()+"',"+
			comprobante.getDestinatario().getId()+","+
			total+")";
	try {
		PreparedStatement stmt = conexion.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		int filas = stmt.executeUpdate();
		if(filas>0) {
			resultado = true;
			ResultSet rs = stmt.getGeneratedKeys();
			while(rs.next()) {
				comprobante.setId(rs.getInt(1));
			}
		}
		agregarDetallesAComprobante(comprobante.getDetalle(), comprobante.getId());
		ccarr.vaciar(comprobante.getDestinatario());
		desc.actualizarpuntos(comprobante.getDestinatario().getTarjetadescuento());
		System.out.println("Comprobante registrado");
		if (comprobante.getMetododepago() instanceof Transferencia) {
			CRUDTransferencia trans = new CRUDTransferencia();		
			trans.registrar (comprobante);
		} else {
			CRUDPagotarjeta pgt = new CRUDPagotarjeta();	
			pgt.registrar (comprobante);
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return resultado;
}
}

//INSERT INTO pagotarjeta (cvu,cbu,alias,id_comprobante,id_cliente) 
//VALUE (comprobante.getMeotodoPago().getCVU(),comprobante.getMeotodoPago().getCBU(),
//		comprobante.getMeotodoPago().getAlias(),comprobante.getId(),
//		comprobante.getDestinatario().getId())
//
//
//pgt.registrar (comprobante.getMetododepago(),comprobante.getId(),comprobante.getDestinatario().getId()); 
//
//trans.registrar (comprobante.getMetododepago(),comprobante.getId(),comprobante.getDestinatario().getId());

