package principal;

import java.time.LocalDate;
import java.util.Scanner;

public class Lanzador {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduce un nombre: ");
		String producto = sc.nextLine();
		System.out.println("Introduce las unidades: ");
		int unidades = Integer.parseInt(sc.nextLine());
		System.out.println("Introduce una fecha: ");
		String f = sc.nextLine();
		LocalDate fecha = LocalDate.parse(f);
		System.out.println("Introduce la tienda: ");
		String tienda = sc.nextLine();
		
		
		

	}

}
