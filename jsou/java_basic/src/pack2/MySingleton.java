package pack2;

public class MySingleton {
	
	int kor=90;
	
	private static MySingleton singleton = new MySingleton();
	public static MySingleton getInstance() {
	return singleton;
	}

}
