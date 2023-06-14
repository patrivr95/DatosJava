package service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PedidoServidor {
	
	
	
	
	public static void main(String[] args) {
		
		int port=8000;
		ExecutorService executor=Executors.newCachedThreadPool();
		try(ServerSocket servidor = new ServerSocket(port)){
			while(true) {
				Socket sc = servidor.accept();
				executor.submit(new HiloCliente(sc));
				
				
			}
			
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		

	}
	

}
