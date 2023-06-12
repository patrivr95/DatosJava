package service;

public class Capsula<T> {

	// Diseña una clase, que encapsule a cualquier objeto Java. Tendrá los métodos getData y setData para acceder y establecer el valor del objeto
	
	private T data;

	public Capsula(T data) {
		super();
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		data = data;
	}

	
}
