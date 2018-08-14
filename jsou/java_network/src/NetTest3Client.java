

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class NetTest3Client {

	public static void main(String[] args) {
		try {
			InetAddress ia = InetAddress.getByName("192.168.0.18");
			Socket socket = new Socket(ia, 9999);
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			out.println("hi! my name is surukiiiiiii" + "\n");
			out.close();
			socket.close();
		} catch (Exception e) {
			System.out.println("client err : " + e);
		}

	}

}
