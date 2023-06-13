package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import model.Item;
import utilities.FechaALocalDate;

public class CovidService {
	
	String http="https://opendata.ecdc.europa.eu/covid19/nationalcasedeath/json/";
	
	private List<Item> itemsFromJson(){
		Gson gson = new Gson();
		try {
			HttpRequest request = HttpRequest.newBuilder()
									.uri(URI.create(http))
									.GET()
									.build();
			
			HttpClient cliente=HttpClient.newBuilder()
								.build();
			
			HttpResponse<String> response = cliente.send(request, BodyHandlers.ofString());
			return List.of(gson.fromJson(response.body(), Item[].class));
		} catch (Exception ex) {
			ex.printStackTrace();
			return List.of();
		}
	}
	
	
	public long casosTotales() {
		
		return itemsFromJson()
				.stream()
				.filter(it->it.getIndicador().equals("cases")&&FechaALocalDate.convertirTextoFecha(it.getFechaMuestra().equals(fechaMasReciente())))
				.collect(Collectors.summingLong(it->it.getCasosAcumulados()));
				
				
		}
	
	
	public double incidenciaMedia(String pais) {
		
		return itemsFromJson()
				.stream()
				.filter(it->it.getPais().equals(pais)&&it.getIndicador().equals("cases"))
				.collect(Collectors.averagingDouble(it->it.getIncidencia()));
	}
	
	public List<String> paises(){
		
		return itemsFromJson()
				.stream()
				.map(c->c.getPais())
				.collect(Collectors.toList());
	}
	
	private LocalDate fechaMasReciente() {
		
		return FechaALocalDate.convertirTextoFecha(itemsFromJson())
				.max(Comparator.comparing(it->FechaALocalDate.convertirTextoFecha(it.getFechaMuestra())))
				.orElse(new Item())
				.getFechaMuestra());
	}
	
	
	public Map<String, Double> incidenciaPaises(){
		
		return itemsFromJson()
				.stream()
				.filter(c->c.getIndicador().equals("cases"))
				.collect(Collectors.groupingBy(it->it.getPais(), Collectors.averagingDouble(it->it.getIncidencia())));
	}

}
