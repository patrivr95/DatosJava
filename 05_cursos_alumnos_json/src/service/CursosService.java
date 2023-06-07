package service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;

import model.Alumno;
import model.Curso;

public class CursosService {
	
	String dir = "c:\\temp\\cursos.json";
	
	private Stream<Curso> streamCursos(){
		Gson gson = new Gson();
		try(FileReader fr = new FileReader(dir)){
			Curso[] cursos = gson.fromJson(fr,  Curso[].class);
			return Stream.of(cursos);
		} catch (IOException ex) {
			ex.printStackTrace();
			return Stream.empty();
		}
	}
	
	// cursos precio máximo: recibe un precio máximo
	// y devuelve la lista de cursos suyo precio sea inferior o igual
	// ordenador de menor a mayor precio
	
	public List<Curso> cursosPrecioMaximo(double precio) {
		
		return streamCursos()
				.filter(c->c.getPrecio() <= precio)
				.sorted(Comparator.comparingDouble(c->c.getPrecio()))
				.collect(Collectors.toList());	
	}
	
	
	// a partir del nombre de un curso, devuelve la lista de alumnos
	// de dicho curso
	
	public List<Alumno> alumnosPorCurso(String curso){
		
		return streamCursos()
				.filter(c->c.getCurso().equals(curso))
				.findFirst()
				.orElse(new Curso())
				.getAlumnos();	}
	
	
	// duracion media de todos los cursos
	
	public double mediaCursos() {
		
		return streamCursos()
				.collect(Collectors.averagingDouble(c->c.getDuracion()));
	}
	
	
	// media de edad de todos los alumnos
	
	public double edadMediaAlumnos() {
		
		return streamCursos()
				.flatMap(c->c.getAlumnos().stream())
				.collect(Collectors.averagingDouble(a->a.getEdad()));
	}
	
	
	//lista nombre de todos los alumnos mayores de edad
	
	public List<String> nombresAlumnos(){
		
		return streamCursos()
				.flatMap(c->c.getAlumnos().stream())
				.filter(a->a.getEdad()>= 18)
				.map(a->a.getNombre())
				.collect(Collectors.toList());
	}
}
