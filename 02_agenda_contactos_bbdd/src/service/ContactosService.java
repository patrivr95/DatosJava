package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Contacto;

public class ContactosService {
	
	String url="jdbc:mysql://localhost:3306/agenda";
	String usuario="root";
	String password="Fideguap03021995";
	
	
	public boolean guardarContacto(Integer telefono, String nombre,String email, int edad) {
		
		boolean res=false;
		
		try (Connection con=DriverManager.getConnection(url, usuario, password)){
			/*String sql="insert into contactos (telefono, nombre, email, edad)";
			sql+="values(" +telefono +",'" +nombre +"','" +email +"',"+ edad +")";
			Statement st=con.createStatement();
			st.execute(sql);*/
			
			String sql="insert into contactos(telefono, nombre, email, edad) values(?,?,?,?)";
			PreparedStatement ps =con.prepareStatement(sql);
			
			// sustituimos los parámetros ? por valores
			ps.setInt(1,  telefono);
			ps.setString(2, nombre);
			ps.setString(3, email);
			ps.setInt(4,  edad);
			ps.execute();
			
			res=true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			res=false;
		}
		return res;
	}
	
	
	public Contacto buscarContacto(Integer numero) {
		
		Contacto contacto = null;
		
			try(Connection con=DriverManager.getConnection(url, usuario, password)){
			String sql="select * from contactos where telefono = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, numero);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				contacto = new Contacto(rs.getInt("telefono"),
						rs.getString("nombre"),
						rs.getString("email"),
						rs.getInt("edad"));
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			
		}
			
		return contacto;
			
	}
	
	
	public void eliminarContacto(Integer numero) {

		try (Connection con=DriverManager.getConnection(url, usuario, password)){
			/*String sql="delete from contactos ";
			sql+="WHERE telefono = " + numero;
			Statement st=con.createStatement();
			st.execute(sql);*/
			
			String sql="DELETE FROM contactos WHERE telefono=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, numero);
			ps.execute();
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			
		}
		
	}
	
	
	public List<Contacto> contactos() {	
		
		List<Contacto> contactos = new ArrayList<Contacto>();
		
		try(Connection con=DriverManager.getConnection(url, usuario, password)){
			String sql="select * from contactos";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				 contactos.add(new Contacto(rs.getInt("telefono"), rs.getString("nombre"), rs.getString("email"), rs.getInt("edad")));
				
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return contactos;
		
		
	
	}
}
