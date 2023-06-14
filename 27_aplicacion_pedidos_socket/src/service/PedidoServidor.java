package service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PedidoServidor {
	

	public static void main(String[] args) {
		int port=8000;
		ExecutorService executor=Executors.newCachedThreadPool();
		
		try(ServerSocket servidor = new ServerSocket(port)){
		
			
			while(true) {
				System.out.println("A la espera de llamadas");
				Socket sc = servidor.accept();
				System.out.println("Llamada recibida");
				executor.submit(new HiloCliente(sc));
			}
			
			
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		

	}
	

}
