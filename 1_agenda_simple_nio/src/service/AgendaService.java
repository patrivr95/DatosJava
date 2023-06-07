package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class AgendaService {
	Path ruta=Paths.get("c:\\temp\\contactos.txt");
	//constructor: comprobar si existe el fichero y si no existe crearlo
	
	public AgendaService() throws NoExisteAlmacenamientoException {
		
		try {
			if(!Files.exists(ruta)) {
				Files.createFile(ruta);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			
			throw new NoExisteAlmacenamientoException();
		}
		
	}
	
	
	public void guardarContacto(String nombre) {
		try {
            Files.writeString(ruta, nombre, StandardOpenOption.APPEND);        
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
	}
	
	public boolean buscarContacto(String nombre) {
		
        try {
                 return Files.lines(ruta)
                		 .anyMatch(linea -> linea.equals(nombre));
                    
            }catch(IOException ex) {
                ex.printStackTrace();
                return false;
                
            }
        
	}
	
	public List<String> contactos() {
		
		try{
            return Files.readAllLines(ruta);  
   
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
        
	}
}
