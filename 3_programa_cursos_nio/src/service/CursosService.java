package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Curso;
import utilities.Utilidades;

public class CursosService {
	private final Path DIR=Paths.get("c:\\temp\\cursos.txt");
	
	public CursosService() {
		
		try {
			if(!Files.exists(DIR)) {
				Files.createFile(DIR);
			}
			
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public boolean guardarCurso(int codigoCurso, String nombre, double precio, LocalDate fechaInicio, LocalDate fechaFin) {
		 if(buscarCurso(codigoCurso)==null) {
	            boolean res=false;
	            
	            try {
	                
	               Files.writeString(DIR, codigoCurso + "," + nombre + "," + precio + "," + fechaInicio + "," + fechaFin, StandardOpenOption.APPEND);
	               res = true; 
	            }
	            catch(IOException ex) {
	                ex.printStackTrace();
	            }
	            return res;
	            
	        }else {
	            return false;
	        }
	}
	
	public Stream<String> buscarCurso(int codigo) {
       
        try {
             	return Files.lines(DIR)
             			.filter(curso->curso.getCodigo().equals(codigo));
             			
                    
            }catch(IOException ex) {
                ex.printStackTrace();
            }        
        
    }
	
	
	public void eliminarCurso(int codigo) {
		
		List<Curso> cursos = curso();
		cursos.remove(codigo);
		
		try {
			Files.write(DIR, cursos
					.stream()
					.map(c->c.getNombre()+"," + c.getPrecio()+"," + c.getFechaInicio()+"," + c.getFechaFin())
					.collect(Collectors.toList()));
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
	}
	
	
	public List<Curso> cursosPrecioMax(double max){
		
		try {
			return Files.lines(DIR)
					.filter(c->c.getPrecio() == max)
					.collect(Collectors.toList());
			
		} catch(IOException ex) {
			ex.printStackTrace();
			return List.of();
		}
		 
	}
	
	public List<Curso> cursosDuracionMax(int max){
		
	}
	public List<Curso> cursosFinalizados(){
		
	}
	public List<Curso> cursoLargo() {
		
	}
}
