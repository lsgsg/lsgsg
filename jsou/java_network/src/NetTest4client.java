import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class NetTest4client {
	Socket socket;
	PrintWriter out;
	BufferedReader reader;
	
	public NetTest4client() {
		try {
			socket = new Socket("192.168.0.69" ,8830);
			out = new PrintWriter(socket.getOutputStream(),true);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "euc-kr"));
		} catch (Exception e) {
			System.out.println("NetTest4Client err : " + e);
		}
	}
	private void sendMsg() {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("전송 메세지 입력 : ");
			String data = scanner.nextLine();
			out.println(data);//서버로 자료 전송
			
			String re_data = reader.readLine(); // 서버에서 자료 수신;
			System.out.println("수신 자료 : " + re_data);
		} catch (Exception e) {
			System.out.println("sendMsg err : " + e);
		}finally {
			try {
				reader.close();
				out.close();
				socket.close();
			} catch (Exception e2) {
				
			}
		}

	}
	public static void main(String[] args) {
		NetTest4client client = new NetTest4client();
		client.sendMsg();
	}
	
}
