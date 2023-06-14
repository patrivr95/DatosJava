package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientePedidos {

	public static void main(String[] args) {
		
		Scanner scn = new Scanner(System.in);
		
		try(Socket sc= new Socket("localhost", 8000);
				PrintStream out = new PrintStream(sc.getOutputStream());
				BufferedReader bf = new BufferedReader(new InputStreamReader(sc.getInputStream())))
		{
			System.out.println("Introduce el nombre de la tienda: ");
			String tienda = scn.nextLine();
			out.println(tienda);
			System.out.println(bf.readLine());
			
		}catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
