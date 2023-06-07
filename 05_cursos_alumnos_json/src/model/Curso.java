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

public class Curso {
	private String curso;
	private int duracion;
	private Double precio;
	private List<Alumno> alumnos;
}
