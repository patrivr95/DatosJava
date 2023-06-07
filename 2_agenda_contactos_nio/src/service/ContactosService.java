package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

import model.Contacto;

public class ContactosService {

	private final Path FICHERO = Paths.get("c:\\temp\\contactos.txt");
	
	public ContactosService() {
		
		try {
			if(!Files.exists(FICHERO)) {
				Files.createFile(FICHERO);
			}
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public boolean guardarContacto(Integer numero, String nombre,String email, int edad) {
		
		if(buscarContacto(numero) == null) {
			boolean res= false;
		try {
				Files.writeString(FICHERO, numero+","+nombre+","+email+","+edad+"\n", StandardOpenOption.APPEND);
				res=true;
			
			} catch(IOException ex) {
				ex.printStackTrace();
				
			}
		return res;
		} else {
			return false;
		}
			
	}
	
	public Contacto buscarContacto(Integer numero) {
		
		
		try{
			return Files.lines(FICHERO)
				.map(c->{
					String[] partes=c.split(",");
                    return new Contacto(Integer.parseInt(partes[0]),partes[1],partes[2],Integer.parseInt(partes[3]));
				})
				.filter(contacto-> contacto.getTelefono().equals(numero))
				.findFirst()
				.orElse(null);
					
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	
			
		
	}
	
	public void eliminarContacto(Integer numero) {
		
		List<Contacto> contactos = contactos();
		contactos.removeIf(c->c.getTelefono().equals(numero));
 		
		try {
			Files.write(FICHERO, contactos
					.stream()
					.map(c->c.getTelefono() +","+ c.getNombre() +"," + c.getEmail() + "," + c.getEdad())
					.collect(Collectors.toList()));
				
			
		} catch(IOException ex) {
			ex.printStackTrace();
		}		
		
	}
	
	public List<Contacto> contactos() {

		try{            
            return Files.lines(FICHERO)//Stream<String>
                    .map(s->{
                        String[] partes=s.split(",");
                        return new Contacto(Integer.parseInt(partes[0]),partes[1],partes[2],Integer.parseInt(partes[3]));
                    })//Stream<Contacto>
                    .collect(Collectors.toList());
    }catch(IOException ex) {
            ex.printStackTrace();
            return List.of();
    } 
		
		
	}
}
