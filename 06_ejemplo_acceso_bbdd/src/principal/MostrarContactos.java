package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MostrarContactos {

	public static void main(String[] args) {
		// datos para obtener la conexion con la BBDD
				String url ="jdbc:mysql://localhost:3306/agenda";
				String usuario="root";
				String password="****";
				
				// conexion con la BBDD, que la tendremos que meter en un trycatch
				try (Connection con=DriverManager.getConnection(url, usuario, password)) {
					String sql="select nombre from contactos";
					Statement st=con.createStatement();
					ResultSet rs = st.executeQuery(sql);
					while(rs.next()){
						System.out.println(rs.getString("nombre"));
					}
					
				} catch(SQLException ex) {
					ex.printStackTrace();
				}

	}

}
