package principal;

import java.util.Scanner;

import service.Numeros;

public class Lanzador {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduce un número: ");
		int numero1 = sc.nextInt();
		System.out.println("Introduce un número: ");
		int numero2 = sc.nextInt();
		System.out.println("Introduce un número: ");
		int numero3 = sc.nextInt();
		
		Numeros n1 = new Numeros();
		Numeros n2 = new Numeros();
		Numeros n3 = new Numeros();

	}

}
