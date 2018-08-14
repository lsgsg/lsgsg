package db.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbTest6Execute {
	Connection conn;
	Statement stmt;
	ResultSet rs;

	public DbTest6Execute() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			process();
		} catch (Exception e) {
			System.out.println("DbTest6Execute err : " + e);
		}
	}

	private void process() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			stmt = conn.createStatement();
			boolean b = false;
			// execute안의 메게변수가 select 면 true 나머지는 false를 리턴한다.
			b = stmt.execute("update sangdata set sang='소화기' where code = 1 ");
			System.out.println("update 후 : " + b);
			
			int re = stmt.getUpdateCount(); // update 성공유무
			System.out.println("update 후 : " + re);
			if( re >= 1) {
				System.out.println("작업성공"); // update 성공
			} else {
				System.out.println("작업 실패");// update 실패;
			}
			b = stmt.execute("select * from sangdata");
			System.out.println("select 후 : " + b);
			rs = stmt.getResultSet();
			System.out.println(rs);
			while(rs.next()) {
				System.out.println(rs.getString(1)+" "+rs.getString(2));
			}
		} catch (Exception e) {
			System.out.println("DB연결 실패 : " + e);
		}finally {
			try {

				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
	}
	public static void main(String[] args) {
		new DbTest6Execute();

	}

}
