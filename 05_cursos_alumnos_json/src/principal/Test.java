package principal;

import service.CursosService;

public class Test {

	public static void main(String[] args) {
		CursosService cursosService = new CursosService();
		
		// alumnos de Java
		cursosService.alumnosPorCurso("Java")
			.forEach(a->System.out.println(a.getNombre()));
		
		
		// duracion media
		System.out.println(cursosService.mediaCursos());
		
		
		// media edad alumnos
		System.out.println(cursosService.edadMediaAlumnos());

	}

}
