package model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Pais {
	private int codigoPais;
	private String nombre;
	private String continente;
	private long poblacion;
	private String bandera;
	
}
