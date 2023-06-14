package util;

import java.time.LocalDate;
import java.util.List;

import com.google.gson.Gson;

import model.Pedido;

public class Utilidades {
	
	public static List<Pedido> convertirJsonAPedidos(String json){
		
		Gson gson = new Gson()
				.newBuilder()
	            .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
	            .create();
		
		//return gson.fromJson(json,  new TypeToken<List<Pedido>>() {}.getType());
		return List.of(gson.fromJson(json,  Pedido[].class));
	}
}
