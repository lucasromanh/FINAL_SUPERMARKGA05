package com.supermarkga05;

import static spark.Spark.*;


import com.google.gson.Gson;
import com.supermark.servicios.CRUDCliente;

import supermark.Cliente;
import supermark.Domicilio;

public class app {
	public static void main (String[] args) {
//		Gson mapper = new Gson ();
//		Cliente cliente = new Cliente ("Lucas", "Roman", 3152637, new Domicilio ("parqueB", "sirewi", 12, 12, 2 ), 34, 1);
//		get ("/saludar", (request,response)  ->  cliente,mapper::toJson); //"los dos puntos entre mapper y toJason es para convertir un ovbjeto"
//////		
		post("/registrar",(request,response) -> {
		response.type("application/json");
			Gson mapper = new Gson ();
			Cliente user = mapper.fromJson(request.body(),Cliente.class);   //ejecutar un servicio, por ejemplo el CRUDCliente
		
		CRUDCliente cu = new CRUDCliente();
//		boolean resutlado = cu.registrar(user);
		cu.insertar(user); 
////			
//		if (resultado==true) {
//			return mapper 
//					.toJson(new StandardResponse(StatusResponse.SUCCESS, "CLiente regisrado"));
//			
//		} else {
//			return mapper 
//					.toJson(new StandardResponse(StatusResponse.ERROR, "No se ha podido registrar el Cliente"));
//		}
////			
		return mapper.toJson(new StandardResponse(StatusResponse.SUCCESS));
////			
////		
	});
	}

}
