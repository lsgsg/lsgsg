import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NetTest4server {
	ServerSocket ss;
	Socket socket;
	PrintWriter out;
	BufferedReader reader;
	
	public NetTest4server() {
		try {
			ss = new ServerSocket(8888);
		} catch (Exception e) {
			System.out.println("NetTest4server err : " + e);
			return;
		}
		
		System.out.println("서버 서비스 중 ....");
		
		try {
			socket = ss.accept();
			out = new PrintWriter(socket.getOutputStream(),true);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "euc-kr"));
		} catch (Exception e) {
			System.out.println("err : "+ e);
		}
	}
	
	public void receiveMsg() {
		try {
			String msg = reader.readLine();
			System.out.println("receive Msg : " + msg);
			
			out.println("from server : " + msg + "\n");
			reader.close();
			out.close();
			socket.close();
			ss.close();
		} catch (Exception e) {
			System.out.println("receiveMsg err : " + e);
		}
	}
	public static void main(String[] args) {
		while(true) {
			
			NetTest4server server =  new NetTest4server();
			server.receiveMsg();
		}

	}

}
