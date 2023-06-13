package principal;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import model.Item;
import service.CovidService;

public class Programa {

	public static void main(String[] args) {
		CovidService covidService = new CovidService();
		ExecutorService executor=Executors.newCachedThreadPool();
		CompletableFuture<Long> cf = CompletableFuture.supplyAsync(()->covidService.casosTotales(), executor);
		cf.whenCompleteAsync((r, ex) -> System.out.println("Casos totales: " + r));
		covidService.incidenciaMediaPaises().forEach((i, m) -> System.out.println(i +" , " + m));
		executor.shutdown();

	}

}
