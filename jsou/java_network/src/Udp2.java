import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Udp2 {
	
	/*public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("보넬 자료 입력");
		String msg = in.readLine();
		
		DatagramSocket dsoc = new DatagramSocket(); // 우체통 으로 생각하렴;ㄴ
		InetAddress ia = InetAddress.getByName("192.168.0.08");
		DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.getBytes().length,ia,7777);
		dsoc.send(dp);
		dsoc.close();
		System.out.println("전송완료 :)");
	}*/

	public static void main(String[] args) { //수신담당
		try {
			DatagramSocket dsoc = new DatagramSocket(7777);
			byte[] data = new byte[65500];//한번에 받을 수 있는 바이트의 크기를 지정  ( 한번에 수신가능한 용량);
			
			DatagramPacket dp = new DatagramPacket(data, data.length);
			System.out.println("수신 대기 ..... ");
			
			while(true) {
				dsoc.receive(dp);
				System.out.println("송신하는 컴퓨터의 주소 : " + dp.getAddress().getHostAddress());
				System.out.println("자료 크기 : "  + dp.getLength());
				System.out.println("자료 내용 : " + new String(dp.getData()).trim());
			}
		} catch (Exception e) {
			System.out.println("err : " + e);
		}

	}

}
