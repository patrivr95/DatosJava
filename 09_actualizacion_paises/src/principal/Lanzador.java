package principal;

import java.util.List;

import model.Pais;
import service.PaisesService;

public class Lanzador {

	public static void main(String[] args) {
		PaisesService paisesServices = new PaisesService();
		List<Pais> paises = paisesServices.paisesFromJson();
		paises.forEach(p->{
			if(paisesServices.existePais(p.getCodigoPais())) {
				paisesServices.actualizarPais(p);
			} else {
				paisesServices.guardarPais(p);
			}
		});
		
		System.out.println("Tarea completada");

	}

}
