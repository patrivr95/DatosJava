package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Empleado {
	private String nombre;
	private String email;
	private double salario;
	private String departamento;
}

//[{"nombre":"María","email":"mar@gmail.es","salario":1400,"departamento":"ventas"},
//{"nombre":"Javier","email":"javi@gmail.com","salario":1600,"departamento":"sistemas"},
//{"nombre":"Miguel","email":"mili@gmail.es","salario":1200,"departamento":"RRHH"},
//{"nombre":"Laura","email":"lauri@gmail.es","salario":1500,"departamento":"sistemas"},
//{"nombre":"Luis","email":"luigi@gmail.com","salario":1300,"departamento":"RRHH"}]
