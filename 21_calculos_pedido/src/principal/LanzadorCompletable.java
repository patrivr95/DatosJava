package principal;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tareas.Pedidos;

public class LanzadorCompletable {

	public static void main(String[] args) {
		Pedidos pedidosService = new Pedidos();
		ExecutorService executor= Executors.newCachedThreadPool();
		CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(()-> pedidosService.productoMasVendido(), executor);
		CompletableFuture<Double> cf2 = CompletableFuture.supplyAsync(()-> pedidosService.mediaUnidades(), executor);
		cf1.whenCompleteAsync((r, ex) -> System.out.println("El producto más vendido es: "+ r));
		cf2.whenCompleteAsync((r, ex) -> System.out.println("Media de unidades es: " + r));
	}

}
