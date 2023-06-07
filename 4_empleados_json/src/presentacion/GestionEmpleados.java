package presentacion;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import model.Empleado;
import service.EmpleadoService;

public class GestionEmpleados {
	
	static Scanner sc = new Scanner(System.in);
	static EmpleadoService empleadoService = new EmpleadoService();
	
	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		
		String dir = "c:\\temp\\empleados.json";
		int opcion;
		
		do {
			mostrarMenu();
			opcion = Integer.parseInt(sc.nextLine());
			switch(opcion) {
				case 1:
					agregarEmpleado();
					break;
				case 2:
					buscarDepartamento();
					break;
				case 3:
					buscarNombre();
					break;
				case 4:
					System.out.println("Adios");
			}
			
		} while(opcion != 4);

	}
	
	
	public static void mostrarMenu() {
		System.out.println("Elige una opción");
		System.out.println("1. Nuevo empleado");
		System.out.println("2. Empleado por departamento");
		System.out.println("3. Empleado por nombre");
		System.out.println("4. Salir");
		
	}
	
	
	public static void agregarEmpleado() {
		
		System.out.println("Introduce el nombre: ");
		String nombre = sc.nextLine();
		System.out.println("Introduce el email: ");
		String email = sc.nextLine();
		System.out.println("Introduce el salario: ");
		Double salario = Double.parseDouble(sc.nextLine());
		System.out.println("Introduce el departamento: ");
		String departamento = sc.nextLine();
		
		Empleado empleado = new Empleado(nombre, email, salario, departamento);
		empleadoService.guardarEmpleado(empleado);
			
	}
	
	public static void buscarDepartamento() {
		System.out.println("Introduce el departamento");
		String departamento = sc.nextLine();
		empleadoService.empleadoDepartamento(departamento);	
	}
	
	public static void buscarNombre() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		System.out.println("Introduce el nombre del empleado: ");
		String nombre = sc.nextLine();
		empleadoService.buscarEmpleado(nombre);
		
		}
	}


