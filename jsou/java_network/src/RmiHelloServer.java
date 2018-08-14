import java.rmi.Naming;

public class RmiHelloServer {
	public static void main(String[] args) {
		try {
			/// 원격 객체를 작성후 rmi registry에 등록
			RmiHelloImpl impl = new RmiHelloImpl(); // rmi 의 기본 포트번호 : 1099
			Naming.rebind("rmi://127.0.0.1:1099/DDurugi", impl); // DDurugi 라는 이름으로 impl객체를 바인딩;
			System.out.println("rmi service start...");
		} catch (Exception e) {
			System.out.println("서버 에러 : "  + e);
		}
	}
}
