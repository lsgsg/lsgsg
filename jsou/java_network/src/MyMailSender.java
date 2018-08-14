import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MyMailSender {
	// 메일 처리 기본 지식

	/*Session : 메일 서버와의 세션 관리
	Message : 메일 메시지 정보 및 내용
	Address : 이메일 주소
	Authenticator : 아이디/비밀번호 정보
	Transport : 메일 전송
	Store / Folder : 메일 박스 및 메일 폴더*/
	public static void main(String[] args) {
		String password = "acorn1234test";
		String toMail = "acorngoodjob@gmail.com";
		String fromName = "뚜루기";
		String subject = "점심에는 뭘 먹을지 맨날 고민하는 것도 짜증남";
		String content = "뭐 먹지 움직이기도 시르다";
		
		MyMailSender mailSender = new MyMailSender();
		mailSender.sendMail(toMail, password, fromName, subject, content);
	}
	public void sendMail(String toMail, String password,String fromName,String subject,String content) {
		try {
			//smtp 서버 환경설정 Properties
			Properties props = new Properties();
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.auth", "true");
			
			//인증 
			MyAuth auth = new MyAuth(toMail, password);
			Session sess = Session.getDefaultInstance(props,auth);
			
			//메세지 
			MimeMessage msg = new MimeMessage(sess);
			msg.setHeader("content-type", "text/plain;charset=utf-8");
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("tvtiiiiiij@gmail.com",fromName,"utf-8"));
			
			//전송
			msg.setSubject(subject);
			msg.setContent(content,"text/plain;charset=utf-8");//plain타입(보통)으로 불러들어오기 html파일로 불러올려면 html
			msg.setSentDate(new Date());
			
			//첨부파일 생략;
			
			Transport.send(msg);
			System.out.println("메일 전송 완료");
		} catch (Exception e) {
			System.out.println("sendMail err : "  + e );
		}
	}
}