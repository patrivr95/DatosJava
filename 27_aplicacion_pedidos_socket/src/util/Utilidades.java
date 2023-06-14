package util;

import java.time.LocalDate;
import java.util.List;

import com.google.gson.Gson;

import model.Pedido;

public class Utilidades {

	public static String convertirAJson(List<Pedido> pedidos) {
		
		Gson gson = new Gson()
		 .newBuilder()
         .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
         .create();
		return gson.toJson(pedidos);
	}
}
