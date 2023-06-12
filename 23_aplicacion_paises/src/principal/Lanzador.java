package principal;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import model.Pais;
import service.PaisService;

public class Lanzador {

	public static void main(String[] args) {
		PaisService paisService = new PaisService();
		ExecutorService executor = Executors.newCachedThreadPool();
		CompletableFuture<Pais> cf=CompletableFuture.supplyAsync(()-> paisService.paisPoblado(), executor);
		cf.whenComplete((r, ex) -> System.out.println(r.getNombre()));
		paisService.continentes().forEach(System.out::println);

	}

}
