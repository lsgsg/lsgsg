import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuth extends Authenticator{ //메일 인증용 : id /pwd //mail이라는 자르 파일을 연동함
	private String fromEmail,passwd;
	
	public MyAuth(String fromEmail, String passwd) {
		this.fromEmail = fromEmail;
		this.passwd = passwd;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		
		return new PasswordAuthentication(fromEmail, passwd);
	}
}
