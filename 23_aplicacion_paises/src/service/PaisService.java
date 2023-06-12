package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import model.Pais;

public class PaisService {
	
	String http="https://restcountries.com/v2/all";

	
	private List<Pais> paisesFromJson(){
		
		Gson gson=new Gson();
		try {
		HttpRequest request = HttpRequest.newBuilder()
								.uri(URI.create(http))
								.GET()
								.build();
		
		
		HttpClient cliente=HttpClient.newBuilder()
							.build();
		
		HttpResponse<String> response = cliente.send(request, BodyHandlers.ofString());
		return List.of(gson.fromJson(response.body(), Pais[].class));
		
		} catch (Exception ex) {
			ex.printStackTrace();
			return List.of();
		}
	}
	
	public Pais paisPoblado() {
		
		return paisesFromJson()
			.stream()
			.max(Comparator.comparingLong(p->p.getPoblacion()))
			.orElse(new Pais());
		
	}
	
	public List<String> continentes(){
		
		return paisesFromJson()
				.stream()
				.map(p->p.getContinente())
				.distinct()
				.collect(Collectors.toList());
		
	}

}
