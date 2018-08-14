package db.pack1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbTest7PLSQL {
	Connection conn;
	CallableStatement cstmt; // procedure 처리용

	public DbTest7PLSQL() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			process();
		} catch (Exception e) {
			System.out.println("DbTest7PLSQL err :"+ e );
		}
	}
	private void process() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			cstmt = conn.prepareCall("{call pro_del(?)}");
			cstmt.setInt(1, 10);//1번째 물음표에 10을 넣자;
			int re = cstmt.executeUpdate();
			if(re >=1) {
			System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
			}
		} catch (Exception e) {
			System.out.println("process err :"+ e );
		}finally {
			try {
			if(cstmt != null) cstmt.close();
			if(conn != null) conn.close();
			}catch(Exception e2) {
				
			}
			
		}
	}
	
	public static void main(String[] args) {
		new DbTest7PLSQL();

	}

}
