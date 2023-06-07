package utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utilidades {
	static final String formato="dd/MM/yyyy";
	public static LocalDate convertirTextoAFecha(String fecha) {
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern(formato);
		return LocalDate.parse(fecha,formatter);
	}
	
	public static String convertirFechaATexto(LocalDate fecha) {
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern(formato);
		return fecha.format(formatter);
	}
}
