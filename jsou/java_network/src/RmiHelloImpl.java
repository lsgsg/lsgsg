import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class RmiHelloImpl extends UnicastRemoteObject implements RmiHelloInter {

	public RmiHelloImpl() throws RemoteException {
		
	}
	
	@Override
	public String sayHello(int gugudan) throws RemoteException {
		int gop = 0;
		String gugu = "";
		for(int i = 1; i <= 9 ; i ++) {
			gop = gugudan * i;
			gugu += gugudan + " X " + i + " = " + gop + "\n";
		}
		return gugu;
	}

}
