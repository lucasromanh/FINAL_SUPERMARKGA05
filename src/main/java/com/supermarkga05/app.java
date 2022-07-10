package com.supermarkga05;

import static spark.Spark.*;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.supermark.servicios.CRUDCarrito;
import com.supermark.servicios.CRUDCliente;
import com.supermark.servicios.CRUDComprobante;
import com.supermark.servicios.CRUDProducto;

import supermark.Carrito;
import supermark.Cliente;
import supermark.Comprobante;
import supermark.Detalle;
import supermark.Producto;
import supermark.Registro;


public class App {

	public static void main(String[] args) {
		port(8080);
		get("/user",(request,response)->{
			response.type("application/json");
			response.header("Access-Control-Allow-Origin", "*");
			Gson mapper = new Gson();
			Registro user = mapper.fromJson(request.body(),Registro.class);
			
			CRUDCliente cu = new CRUDCliente();
			boolean resultado = cu.estaRegistrado(user);
			
			if(resultado==true) {
		    	return mapper
		    			.toJson(new StandardResponse(
		    					StatusResponse.SUCCESS,
		    					"Usuario ya se encuentra registrado")
		    					);
		    }else {
		    	return mapper
		    			.toJson(new StandardResponse(
		    					StatusResponse.SUCCESS,
		    					"El usuario no figura en el sistema")
		    					);
		    }
		});
		
		post("/registrar",(request,response)->{
			response.type("application/json");
			response.header("Access-Control-Allow-Origin", "*");
			Gson mapper = new Gson();
			Cliente user = mapper.fromJson(request.body(),Cliente.class);
			System.out.println(user);
			
			if(user.getContrasenia().length()<8) {
				return mapper
						.toJson(new StandardResponse(StatusResponse.ERROR,"Contrasenia insegura"));
			}else {
				
				CRUDCliente cu = new CRUDCliente();
				boolean resultado = cu.registrar(user);
				System.out.println(user);

			    if(resultado==true) {
			    	return mapper
			    			.toJson(new StandardResponse(
			    					StatusResponse.SUCCESS,
			    					"Usuario Registrado")
			    					);
			    }else {
			    	return mapper
			    			.toJson(new StandardResponse(
			    					StatusResponse.ERROR,
			    					"Ocurrio un error inesperado")
			    					);
			    }
			}
		});
		
		post("/logear",(request,response)->{
			response.type("application/json");
			response.header("Access-Control-Allow-Origin", "*");
			Gson mapper = new Gson();
			Registro user = mapper.fromJson(request.body(),Registro.class);
			
			
			CRUDCliente cu = new CRUDCliente();
			boolean resultado = cu.iniciarSesion(user);
			
			if(resultado==true) {
				return mapper
						.toJson(new StandardResponse(StatusResponse.SUCCESS,"Logeado con exito"));
			}else {
				return mapper
						.toJson(new StandardResponse(StatusResponse.ERROR,"Contraseña incorrecta"));
			}
		});
		
		post("/seleccionar",(request,response)->{
			response.type("application/json");
			response.header("Access-Control-Allow-Origin", "*");
			Gson mapper = new Gson();
			Carrito productoAgregado = mapper.fromJson(request.body(), Carrito.class);
			
			CRUDCarrito ccarr = new CRUDCarrito();
			
			if(ccarr.agregar(productoAgregado)) {
				return mapper
						.toJson(new StandardResponse(
									StatusResponse.SUCCESS,
									"Producto agregado al carrito"
								));
			}else {
				return mapper
						.toJson(new StandardResponse(
									StatusResponse.ERROR,
									"Hubo un inconveniente agregado el producto"
								));
			}
		});
		
		get("/micarrito/:id",(request,response)->{
			response.type("application/json");
			response.header("Access-Control-Allow-Origin", "*");
			Gson mapper = new Gson();
			
			CRUDCarrito ccarr = new CRUDCarrito();
			ArrayList<Detalle> miCarrito = ccarr.getListado(
						new Cliente(null, null, Integer.valueOf(request.params(":id")), null)
						);
			
			if(miCarrito!=null && miCarrito.size()>0) {
				return mapper
						.toJson(new StandardResponse(
									StatusResponse.SUCCESS,
									mapper.toJson(miCarrito)
								));
			}else {
				return mapper
						.toJson(new StandardResponse(
									StatusResponse.ERROR,
									"Tu carrito se encuentra vacio"
								));
			}
		});
		
		post("/comprar",(request,response)->{
			response.type("application/json");
			response.header("Access-Control-Allow-Origin", "*");
			Gson mapper = new Gson();
			
			Comprobante comp = mapper.fromJson(request.body(),Comprobante.class);
			
			comp.setFecha(new Timestamp(System.currentTimeMillis()));
			
			CRUDComprobante cc = new CRUDComprobante();
						
			if(cc.registrarCompra(comp)) {
				return mapper
						.toJson(new StandardResponse(
								StatusResponse.SUCCESS,
								"Compra Registrada"
								));
			}else {
				return mapper
						.toJson(new StandardResponse(
								StatusResponse.ERROR,
								"Hubo un inconveniente al registrar su compra. Intente nuevamente, más tarde..."
								));
			}
		});
		
		post("/producto/:id",(request,response)->{
			response.type("application/json");
			response.header("Access-Control-Allow-Origin", "*");
			Gson mapper = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
			Producto producto = mapper.fromJson(request.body(),Producto.class);
			
			CRUDCliente cu = new CRUDCliente();
			boolean resultado = cu.esAdmin(new Cliente(null, null, Integer.valueOf(request.params(":id")), null));
			if(resultado==true) {
				CRUDProducto cp= new CRUDProducto();
				
				resultado = cp.cargarProducto(producto);
				
				if(resultado==true) {
			    	return mapper
			    			.toJson(new StandardResponse(
			    					StatusResponse.SUCCESS,
			    					"Producto Registrado")
			    					);
			    }else {
			    	return mapper
			    			.toJson(new StandardResponse(
			    					StatusResponse.ERROR,
			    					"Ocurrio un error inesperado")
			    					);
			    }
			}else {
				return mapper
		    			.toJson(new StandardResponse(
		    					StatusResponse.ERROR,
		    					"No tienes los permisos necesarios")
		    					);
			}
			
			
		});
		
		post("/cargarproducto",(request,response)->{
			response.type("application/json");
			response.header("Access-Control-Allow-Origin", "*");
			Gson mapper = new Gson();
			Producto prod = mapper.fromJson(request.body(),Producto.class);
			System.out.println(prod);
						
				CRUDProducto cu = new CRUDProducto();
				boolean resultado = cu.cargarProducto(prod);
				System.out.println(prod);

			    if(resultado==true) {
			    	return mapper
			    			.toJson(new StandardResponse(
			    					StatusResponse.SUCCESS,
			    					"Producto Cargado con exito")
			    					);
			    }else {
			    	return mapper
			    			.toJson(new StandardResponse(
			    					StatusResponse.ERROR,
			    					"Ocurrio un error inesperado")
			    					);
			    }
			
		});
		post("/actualizarstock",(request,response)->{
			response.type("application/json");
			response.header("Access-Control-Allow-Origin", "*");
			Gson mapper = new Gson();
			Producto prod = mapper.fromJson(request.body(),Producto.class);
			System.out.println(prod);
						
				CRUDProducto produc = new CRUDProducto();
				int cant = produc.getStockActual(prod) ;
				boolean resultado = produc.actualizarStock(prod,cant);
				System.out.println(prod);

			    if(resultado==true) {
			    	return mapper
			    			.toJson(new StandardResponse(
			    					StatusResponse.SUCCESS,
			    					"Producto Actualizado con exito")
			    					);
			    }else {
			    	return mapper
			    			.toJson(new StandardResponse(
			    					StatusResponse.ERROR,
			    					"Ocurrio un error inesperado")
			    					);
			    }
			
		});
		
//		post("/consultaClientexCompra",(request,response)->{
//			response.type("application/json");
//			response.header("Access-Control-Allow-Origin", "*");
//			Gson mapper = new Gson();
//			Cliente clie = mapper.fromJson(request.body(),Cliente.class);
//			System.out.println(clie);
//						
//			CRUDCliente client = new CRUDCliente();
//					
//				ArrayList<Cliente> listcliente = client.consultaClientexCompra();
//				if (listcliente !=null) {
//							for(int i = 0; i<listcliente.size(); i++)  {
//							System.out.println("nombre: "+listcliente[i].getNombre());
//							System.out.println("apellido: "+listcliente[i].getApellido());
//							return mapper
//			    			.toJson(new StandardResponse(
//			    					StatusResponse.SUCCESS,
//			    					"Estos son los Clientes que realizaron alguna Compra")
//			    					);
//							}
//			    }else {
//			    	return mapper
//			    			.toJson(new StandardResponse(
//			    					StatusResponse.ERROR,
//			    					"Ocurrio un error inesperado")
//			    					);
//			    }
//			
//		});
	}
	}
	

//CRUDProducto produc = new CRUDProducto();
//int cant = produc.getStockActual(clie) ;
//boolean resultado = produc.actualizarStock(clie,cant);
//System.out.println(clie);}



//public class app {
//public static void main (String[] args) {
////	Gson mapper = new Gson ();
////	Cliente cliente = new Cliente ("Lucas", "Roman", 3152637, new Domicilio ("parqueB", "sirewi", 12, 12, 2 ), 34, 1);
////	get ("/saludar", (request,response)  ->  cliente,mapper::toJson); //"los dos puntos entre mapper y toJason es para convertir un ovbjeto"
////////	
//	post("/registrar",(request,response) -> {
//	response.type("application/json");
//		Gson mapper = new Gson ();
//		Cliente user = mapper.fromJson(request.body(),Cliente.class);   //ejecutar un servicio, por ejemplo el CRUDCliente
//	
//	CRUDCliente cu = new CRUDCliente();
////	boolean resutlado = cu.registrar(user);
//	cu.insertar(user); 
//////		
////	if (resultado==true) {
////		return mapper 
////				.toJson(new StandardResponse(StatusResponse.SUCCESS, "CLiente regisrado"));
////		
////	} else {
////		return mapper 
////				.toJson(new StandardResponse(StatusResponse.ERROR, "No se ha podido registrar el Cliente"));
////	}
//////		
//	return mapper.toJson(new StandardResponse(StatusResponse.SUCCESS));
//////		
//////	
//});
//}
//
//}
