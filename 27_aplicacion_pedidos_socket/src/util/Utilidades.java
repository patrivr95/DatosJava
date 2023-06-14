package util;

import java.util.List;

import com.google.gson.Gson;

import model.Pedido;

public class Utilidades {

	public static String convertirAJson(List<Pedido> pedidos) {
		
		Gson gson = new Gson();
		return gson.toJson(pedidos);
	}
}
