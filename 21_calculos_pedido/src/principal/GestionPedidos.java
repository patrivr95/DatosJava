package principal;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import tareas.Pedidos;

public class GestionPedidos {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Pedidos pedidosService = new Pedidos();
		ExecutorService executor=Executors.newCachedThreadPool();
		Future<String> f1 = executor.submit(()-> pedidosService.productoMasVendido());
		Future<Double> f2 = executor.submit(()-> pedidosService.mediaUnidades());
		
		while(!f1.isDone() || !f2.isDone()) {
			pedidosService.pedidos();
		}
		
		System.out.println(f1.get());
		System.out.println(f2.get());
		
		executor.shutdown();
		
		
	}

	

}
