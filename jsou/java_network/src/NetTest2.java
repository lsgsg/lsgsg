import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class NetTest2 {
	//soket? 
	public static void main(String[] args) {
		try {
			InetAddress ia = InetAddress.getByName("www.daum.net");
			Socket socket = new Socket(ia, 80);//클라이언트 소켓
			
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
			
			out.println("GET http://www.daum.net");
			out.flush();//요청후 내 컴퓨터내 버퍼를 비운다.
			
			//웹서버에서 넘어온 자료 출력
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(true) {// 넘어올 값이 몇개일지 모르니깐 무한루프에 빠지게 한다.
				String str = reader.readLine();
				if(str == null) {break;}
				System.out.println(str);
			}
			reader.close();
			out.close();
			socket.close(); 
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
