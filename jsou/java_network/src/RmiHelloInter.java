import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Scanner;


public interface RmiHelloInter extends Remote {
	
	
	
	String sayHello(int gogodan) throws RemoteException; //추상메소드를 하나 가짐;
}
