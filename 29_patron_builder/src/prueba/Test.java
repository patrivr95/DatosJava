package prueba;

public class Test {

	public static void main(String[] args) {
		
		// Creamos un coche con ruedas y peso
		Coche c1 = new CocheBuilder()
					.withRuedas(5)
					.withPeso(1200)
					.build();
		
		// creamos un coche con todas las propiedades
		Coche c2 = new CocheBuilder()
					.withColor("Rojo")
					.withPeso(1300)
					.withPotencia(125)
					.withRuedas(4)
					.build();

	}

}
