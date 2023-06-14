package model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Pedido {
	
	private int idPedido;
	private String producto;
	private int unidades;
	private LocalDate fecha;
	private String tienda;

}
