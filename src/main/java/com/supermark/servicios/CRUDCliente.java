package com.supermark.servicios;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import supermark.Cliente;
import supermark.Comprobante;
import supermark.Domicilio;
import supermark.Registro;



public class CRUDCliente<CLiente> {
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
	
	public boolean iniciarSesion(Registro registro) {
		this.sql = "SELECT * FROM registro WHERE registro.mail='"+registro.getMail()+
				"' AND registro.contraseña = '"+registro.getContraseña()+"'";
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

	
	public boolean registrar(Cliente cliente) {
		CRUDDomicilio cdom = new CRUDDomicilio();
		Domicilio dom = cdom.insertar(cliente.getDomicilio());
		cliente.setDomicilio(dom);
		this.sql = "INSERT INTO Cliente "+
				"(id,nombre,apellido,dni,id_domicilio,edad,rol,id_tarjetadescuento) "+
				"VALUE ('"+
				cliente.getId()+"','"+
				cliente.getNombre()+"','"+
				cliente.getApellido()+"','"+
				cliente.getDni()+",'"+
				cliente.getDomicilio().getId()+")"+
				cliente.getEdad()+",'"+
				cliente.getRol()+",'"+
				cliente.getTarjetadescuento().getId_td()+" )";
		boolean resultado = false;
		try {
			conexion.getStmt().executeUpdate(sql);
			resultado = true;
			System.out.println("Cliente registrado");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public ArrayList<Cliente> consultarCliente(){
		ArrayList<Cliente> cliente = new ArrayList<Cliente>();
		this.sql = "SELECT * FROM cliente";
		try {
			conexion.setRs(conexion.getStmt().executeQuery(sql));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			System.out.println("Clientes Registrados:");
			ResultSet rs = conexion.getRs();
			try {
				while (rs.next()) {
					CRUDDomicilio cdom = new CRUDDomicilio();
					Domicilio dom = cdom.getDomicilio(rs.getInt("id_domicilio"));
					if(dom!=null) {
						Cliente user = new Cliente(
								rs.getString("nombre"),
								rs.getString("apellido"),
								rs.getLong("dni"),
								dom,
								rs.getInt("edad"),
								rs.getInt("id")
								);
						cliente.add (user);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cliente;
	}
	
	
	public void eliminar (Cliente cliente)  {
		this.sql =  "DROP INTO cliente" + " (nombre,apellido,dni,id_domicilio,edad,id) " +
				 "VALUE ( ' " + cliente.getNombre () + "', '" + cliente.getApellido() + 
				"',  " + cliente.getDni () + ", " + cliente.getDomicilio().getId() + ",  " + cliente.getEdad() + " ,"
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
				"',  " + cliente.getDni () + ", " + cliente.getDomicilio().getId() + ",  " + cliente.getEdad() + " ,"
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

//	public boolean estaRegistrado(Registro registro) {
//		this.sql = "SELECT * FROM registro WHERE registro.mail='"+registro.getMail()+"'";
//		boolean resultado = false;
//		try {
//			conexion.setRs(conexion.getStmt().executeQuery(sql));
//			while(conexion.getRs().next()) {
//				resultado = true;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return resultado;
//	}

	public boolean estaRegistrado(Cliente cliente) {
		this.sql = "SELECT * FROM cliente WHERE id_domicilio='"+cliente.getDomicilio().getId();
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
	

 
public boolean esAdmin(Cliente cliente) {
	this.sql = "SELECT rol FROM cliente WHERE id="+cliente.getId();
	boolean resultado = false;
	try {
		ResultSet rs = conexion.getStmt().executeQuery(sql);
		if(rs.getInt("rol")==1) {
			resultado = true;
		}
		System.out.println("Usuario registrado");
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return resultado;
}

public ArrayList<Cliente> consultaClientexCompra(){
	ArrayList<Cliente> cliente = new ArrayList<Cliente>();
	this.sql = "SELECT id_cliente FROM comprobante";
	try {
		conexion.setRs(conexion.getStmt().executeQuery(sql));
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		System.out.println("Clientes que realizaron una Compra:");
		ResultSet rs = conexion.getRs();
		try {
			while (rs.next()) {
				CRUDComprobante rcomp = new CRUDComprobante();
				Comprobante compr = rcomp.getComprobante(rs.getInt("id_comprobante"));
				
				if(compr!=null) {
					Cliente user = new Cliente(
							rs.getString("nombre"),
							rs.getString("apellido"),
							rs.getLong("dni"),
							rs.getInt("id")
							);
					cliente.add (user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return cliente;
}












}
