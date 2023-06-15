package prueba;

public class CocheBuilder {
	private Coche coche;
	
	public CocheBuilder() {
		coche=new Coche();
		
	}
	
	public CocheBuilder withRuedas(int ruedas) {
		coche.setRuedas(ruedas);
		return this;
	}
	
	public CocheBuilder withPotencia(double potencia) {
		coche.setPotencia(potencia);
		return this;
	}
	
	public CocheBuilder withColor(String color) {
		coche.setColor(color);
		return this;
	}
	
	public CocheBuilder withPeso(double peso){
		coche.setPotencia(peso);
		return this;
	}
	
	public Coche build() {
		return coche;
	}
}
