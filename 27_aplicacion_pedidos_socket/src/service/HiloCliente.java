package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

import model.Pedido;
import util.Utilidades;

public class HiloCliente implements Runnable{
	
	private Socket sc;
	public HiloCliente(Socket sc) {
		this.sc=sc;
	}
	
	
	@Override
	public void run() {
		try(PrintStream out  = new PrintStream(sc.getOutputStream());
				BufferedReader bf = new BufferedReader(new InputStreamReader(sc.getInputStream()))){

			PedidoService pedidosService = new PedidoService();
			List<Pedido> pedidos = pedidosService.pedidosTienda(bf.readLine());
			out.println(Utilidades.convertirAJson(pedidos));
			
			
			
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
