package db.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbTest1 {
	private Connection conn; // db 연결 객체
	private Statement stmt; // java.sql
	private ResultSet rs; // select문의 결과 접근

	public DbTest1() {
		try {
			// 1. Driver 클래스 로딩;
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("Driver 로딩 실패");
			return;
		}

		try {
			// 2.DB와 연결;
			// @127.0.0.1 나혼자 연습할 로컬주소 혹은 localhost : 오라클의 포트번호 : 1521 (포트번호 :장치번호 인터넷의 기본
			// 포트번호는 80번 생략가능 )
			// sid명 : 오라클 설치시 만들었던 orcl;(url,사용할db명,db명의비번)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
		} catch (Exception e) {
			System.out.println("DB연결 실패 : " + e);
			return;
		}

		try {
			// 3.sql문을 사용하여 자료읽기;
			/*
			 * stmt = conn.createStatement(); rs =
			 * stmt.executeQuery("select * from sangdata"); rs.next();//Record pointer이동 Y?
			 * 포인터가 있는 레코드만 가져오기때문에 포인터를이동해서 레코드를 읽어와야함;
			 * 
			 * String code = rs.getString("code");//칼람명이 곧 인덱스 인덱스번호 대신 테이블의 칼럼명을 쓴다. String
			 * sangpum = rs.getString("sang"); int su = rs.getInt("su"); int dan =
			 * rs.getInt("dan"); System.out.println(code + " "+ sangpum + " " + su + " " +
			 * dan);
			 */
			
			
			// 모든 자료를 다 읽기;
			stmt = conn.createStatement();
			String sql = "select code,sang,su as 수량 ,dan from sangdata";
			rs = stmt.executeQuery(sql); // *보다는 칼럼명을 다 쓰는게 더빠르게 읽힘
			// 원본DB의 순서와 상관없이 내가 가져오고 싶은 순서대로 불러오면 됨;
			int count = 0;
			while (rs.next()) {

				String code = rs.getString(1);// 칼람명이 곧 인덱스 인덱스번호 대신 테이블의 칼럼명을 쓴다.
				String sangpum = rs.getString(2);
				int su = rs.getInt("수량");
				int dan = rs.getInt("dan");
				System.out.println(code + " " + sangpum + " " + su + " " + dan);
				count += 1;
			} // .next(); 포인터를 이동했을때 해당레코드가 있으면 true를 없으면 false를반환
			
			//건수 구하기
			sql = "select count(*) from sangdata";
			rs = stmt.executeQuery(sql);
			rs.next();
			System.out.println("건수 : " + rs.getInt(1));
			System.out.println("건수 : " + count);//db서버를  가지 않고 건수받는 법;
		} catch (Exception e) {
			System.out.println("처리실패 : " + e);
		} finally {
			try {

				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}

	public static void main(String[] args) {

		new DbTest1();
	}

}
