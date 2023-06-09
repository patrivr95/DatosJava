package service;

public class Numeros extends Thread {

	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			for(int j=1; j<=10; i++) {
				System.out.println(i*j);
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
