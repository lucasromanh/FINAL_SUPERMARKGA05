package supermark;

import java.sql.Timestamp;

import java.util.HashMap;


import com.supermark.servicios.CRUDComprobante;
import com.supermark.servicios.CRUDProducto;

public class Testsupermark {

	public static void main(String[] args) {
//		Cliente user = new Cliente(null, null, 0, null);
//		user.setId(1);
//		CRUDComprobante cc = new CRUDComprobante();
//		ArrayList<Comprobante> comprobantes = cc.getComprobantes(user);
//		for(Comprobante comprobante:comprobantes) {
//			System.out.println(comprobante);
//		}
		CRUDComprobante cc = new CRUDComprobante();
		HashMap<Integer, Detalle> detalles = new HashMap<Integer, Detalle>();
		
		Producto p = new Producto(1, "arroz", "gallo", null, 29.2, 30);
		CRUDProducto cp = new CRUDProducto();
		p.setStock(cp.getStockActual(p));
		Detalle detalle = new Detalle(p,10, null);
		detalles.put(1,detalle);
		Cliente user = new Cliente(null, null, 0, null);
		user.getDNI();
		PagoTarjeta pt = new PagoTarjeta("macro", 203931823, "visa", null, 234, 1238f, null);
		Comprobante comp = new Comprobante (
					1,
					10f,
					new Timestamp(System.currentTimeMillis()),
					detalles,
					user,
					null
					);
		cc.registrarCompra(comp);
		System.out.print(cp.getStockActual(new Producto(1, "arroz", "gallo", null, 29.2, 30)));
	}

}