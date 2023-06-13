package servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class HiloCliente implements Runnable {
	private Socket sc;
	public HiloCliente(Socket sc) {
		this.sc=sc;
	}
	
	@Override
	public void run() {
		try(PrintStream out  = new PrintStream(sc.getOutputStream())){
			out.println("hola");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
