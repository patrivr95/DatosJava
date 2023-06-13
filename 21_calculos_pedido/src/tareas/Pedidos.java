package tareas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Pedido;

public class Pedidos {
	
	String url="jdbc:mysql://localhost:3306/pedidos";
	String usuario="root";
	String password="*****";
	
	public String productoMasVendido() {
		
		List<Pedido> pedidos = new ArrayList<>();
		
		try(Connection con=DriverManager.getConnection(url,usuario,password)){
			 String sql = "select * from pedidos";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				pedidos.add(new Pedido(rs.getInt("idPedido"),
						rs.getString("producto"),
						rs.getInt("unidades"),
						rs.getDate("fecha").toLocalDate(),
						rs.getString("tienda")));
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return pedidos.stream()
				.max((p1,p2)->Integer.compare(p1.getUnidades(),p2.getUnidades()))
        		.orElse(new Pedido())
        		.getProducto();
	}

	public List<Pedido> pedidos(){
		
		List<Pedido> pedidos = new ArrayList<>();
		
		try(Connection con=DriverManager.getConnection(url,usuario,password)){
			String sql="select * from pedidos";
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				pedidos.add(new Pedido(rs.getInt("idPedido"),
							rs.getString("producto"),
							rs.getInt("unidades"),
							rs.getDate("fecha").toLocalDate(),
							rs.getString("tienda")));
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return pedidos;
	}
	
	public double mediaUnidades() {
		
		double res = 0.0;
		
		try(Connection con=DriverManager.getConnection(url,usuario,password)){
			String sql = "select avg(unidades) from pedidos";
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

}
