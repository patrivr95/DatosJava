package service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import model.Empleado;

public class EmpleadoService {
	
	String dir = "c:\\temp\\empleados.json";
	private Stream<Empleado> streamEmpleados(){
		Gson gson = new Gson();
		try(FileReader fr = new FileReader(dir)) {
			Empleado[] empleado = gson.fromJson(fr, Empleado[].class);
			return Stream.of(empleado);
		} catch(IOException ex) {
			ex.printStackTrace();
			return Stream.empty();
		}
	}
	
	
	public void guardarEmpleado(Empleado emp) {
		
		List<Empleado> empleados = streamEmpleados().collect(Collectors.toList());
		empleados.add(emp);
		Gson gson = new Gson();
		try(PrintStream out = new PrintStream(dir)) {
			gson.toJson(empleados,out);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
	
	public List<Empleado> empleadoDepartamento(String departamento) {
		
		return streamEmpleados()
				.filter(e->e.getDepartamento().equals(departamento))
				.collect(Collectors.toList());
	}
	
	public Empleado buscarEmpleado(String nombre) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		
		return streamEmpleados()
				.filter(e->e.getNombre().equals(nombre))
				.findFirst()
				.orElse(null);
	}
}
