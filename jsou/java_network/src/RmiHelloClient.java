import java.rmi.Naming;
import java.util.Scanner;

public class RmiHelloClient {

	public static void main(String[] args) {
		try {
			
			Scanner sc = new Scanner(System.in);
			System.out.println( "구구단 몇단? : ");
			int num = sc.nextInt();
			RmiHelloInter rh = (RmiHelloInter)Naming.lookup("rmi://127.0.0.1:1099/DDurugi");
			String result = rh.sayHello(num);
			
			
			System.out.println("result : \n" + result);
		} catch (Exception e) {
			System.out.println("Client err" + e);
		}

	}

}


// cd C:\work\jsou\java_network\bin 
// dir
// rmic RmiHelloimpl
// start registry
// rmic RmiHelloServer

// 다른 창에서

// cd C:\work\jsou\java_network\bin 
// dir
// java RmiHelloClient // 실행;