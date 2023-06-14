package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Pedido;

public class PedidoService {
	
	String url="jdbc:mysql://localhost:3306/pedidos";
	String usuario="root";
	String password="Fideguap03021995";
	
	public List<Pedido> pedidosTienda(String tienda){
		
		List<Pedido> pedidos = new ArrayList<>();
		
		
		try(Connection con=DriverManager.getConnection(url,usuario,password)){
			String sql="select * from pedidos where tienda = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,  tienda);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				pedidos.add(new Pedido(rs.getInt("idPedido"),
							rs.getString("producto"),
							rs.getInt("unidades"),
							//rs.getDate("fecha").toLocalDate(),
							rs.getString("tienda")));
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return pedidos;
	}
	
	/*
	public double mediaUnidades() {
			double res = 0.0;
		
		try(Connection con=DriverManager.getConnection(url,usuario,password)){
			String sql = "select avg(unidades) from pedidos where tienda = ?";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				res=rs.getDouble(1);
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		
		return res;
	}
	*/

}
