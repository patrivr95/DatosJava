package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import model.Pedido;

public class PedidosService{
	
	public class PedidosFicheroService {
	    private String dirFichero;
	    private String tienda;
	    
	    public PedidosFicheroService(String dirFichero, String tienda) {
	        super();
	        this.dirFichero = dirFichero;
	        this.tienda = tienda;
	    }
	    
	    
	    public List<Pedido> pedidosTienda() {
	        Path pt=Path.of(dirFichero);
	        try{
	            return Files.lines(pt)
	            .map(s->{
	                String[] datos=s.split("[,]");
	                return new Pedido(0,datos[0],Integer.parseInt(datos[1]),LocalDate.parse(datos[2],DateTimeFormatter.ofPattern("dd/MM/yyyy")),tienda);
	            })
	            .toList();
	        }catch(IOException ex) {
	            ex.printStackTrace();
	            return List.of(); 
	        }
	    }
	}
	
	
	}
	  




	

