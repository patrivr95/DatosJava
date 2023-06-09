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
		
		Numeros n1 = new Numeros(numero1);
		Numeros n2 = new Numeros(numero2);
		Numeros n3 = new Numeros(numero3);
		
		n1.start();
		n2.start();
		n3.start();
		for(int i=1;i<=10;i++) {
			System.out.println("haciendo otras cosas");
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
