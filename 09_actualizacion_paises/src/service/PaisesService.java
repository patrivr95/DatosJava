package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaisesService {
	
	String url="jdbc:mysql://localhost:3306/mundo";
	String usuario = "root";
	String password = "Fideguap03021995";
	
	public boolean guardarPais(String codigoPais, String nombre, String continente, long poblacion, String bandera) {
		
		boolean res=false;
		
		try(Connection con=DriverManager.getConnection(url, usuario, password)){
			
			String sql="insert into paises values (?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, codigoPais);
			ps.setString(2, nombre);
			ps.setString(3, continente);
			ps.setLong(4, poblacion);
			ps.setString(5, bandera);
			
			res=true;
		} catch(SQLException ex) {
			ex.printStackTrace();
			res=false;
		}
		return res;
	}
}
