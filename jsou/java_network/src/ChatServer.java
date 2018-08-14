import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer implements Runnable {
	ServerSocket ss;
	Service service;
	//여러명의 클라이언트르를 담아두기 위한 컬렉션?
	ArrayList<Service> list = new ArrayList<>();
	
	@Override
	public void run() {
		while(true) {
			try {
				Socket socket = ss.accept(); 
				service = new Service(socket);
				
				// 아래 내부클래스로 들어감(각 클라이언트 마다 새 socket 이 만들어짐 -< arraylist 가 그것을 닫음
				service.start();
				service.chat_name = service.in.readLine();
				
				service.messageSend("/c" + service.chat_name);
				for(Service ser : list) {
					ser.messageSend("/c" + service.chat_name);
					service.messageSend("/c" + service.chat_name);
				}
				list.add(service);
			} catch (Exception e) {
				System.out.println("Charserver run err : " + e);
			}
		}
		
	}
	
	public ChatServer() {
		try {
			ss = new ServerSocket(7777);
			new Thread(this).start();
			System.out.println("채팅 서버 서비스 시작... ");
		} catch (Exception e) {
			System.out.println("ChatServer err : " + e);
		}
	}
	
	
	
	//client별 처리용 내부 클라스
	class Service extends Thread{
		String chat_name;
		BufferedReader in;
		OutputStream out;
		Socket socket;
		
		public Service(Socket socket) {
			try {
				this.socket = socket;
				in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"euc-kr"));
				out = socket.getOutputStream();
			} catch (Exception e) {
				System.out.println("Service err : " + e);
			}
			
			
		}
		
		@Override
		public void run() {
			while(true) {
				try {
					String msg = in.readLine();
					if(msg == null || msg.equals("")) continue;
					
					//메세지가 있는 경우 처리
					if(msg.charAt(0) == '/') {
						if(msg.charAt(1)=='r') {//대화명 변경
							messageAll("/r" + chat_name + "-" + msg.substring(2));
							chat_name = msg.substring(2);
						}else if(msg.charAt(1)=='q') {//퇴장
							try {
								messageAll("/q" + chat_name);
								list.remove(this);
								in.close();
								out.close();
								socket.close();
								
							} catch (Exception e) {
								// TODO: handle exception
							}
							break;
						}else if(msg.charAt(1)=='s') {//귓속말
							// /s접속자명 메세지
							String name = msg.substring(2, msg.indexOf('-')).trim();
							for(Service ser : list) {
								if(name.equals(ser.chat_name)) {
									ser.messageSend(chat_name + ">(secret)" + msg.substring(msg.indexOf('-')+1));
								}
							}
						}
					}else {//일반메시지 처리
						messageAll(chat_name + ">" + msg);
					}
				} catch (Exception e) {
					System.out.println("ChatServer inner run err : " + e);
					break;
				}
			}
		}
		
		public void messageAll(String msg) {
			try {
				for( int i = 0; i <list.size(); i++) {
					Service ser = list.get(i);
					ser.messageSend(msg);
				}
			} catch (Exception e) {
				System.out.println("messageAll :" + e);
			}
		}
		
		public void messageSend(String msg) {
			try {
				out.write((msg + "\n").getBytes("euc-kr"));//네트워크상에선 바이트로 작업함/
			} catch (Exception e) {
				System.out.println("messageSend :" + e);
			}
		}
	}  //------------------------------------------내부클라스  end
	public static void main(String[] args) {
		new ChatServer();

	}

}
