package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AltaContacto {

	public static void main(String[] args) {
		
		// datos para obtener la conexion con la BBDD
		String url ="jdbc:mysql://localhost:3306/agenda";
		String usuario="root";
		String password="Fideguap03021995";
		
		// conexion con la BBDD, que la tendremos que meter en un trycatch
		try (Connection con=DriverManager.getConnection(url, usuario, password)) {
			String sql="insert into contactos (nombre, email, edad)";
			sql+= "values('cnuevo', 'cemail@gmail.com', 44)";
			Statement st = con.createStatement();
			st.execute(sql);
			System.out.println("contacto añadido");
		} catch(SQLException ex) {
			ex.printStackTrace();
		}

	}

}
