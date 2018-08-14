package db.pack1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.internal.OracleCallableStatement;

public class DbTest9PLSQL_select {
	Connection conn;
	CallableStatement cstmt;
	OracleCallableStatement ocstmt;
	ResultSet rs;
	
	public DbTest9PLSQL_select() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			conn = DriverManager.getConnection(url, "scott", "tiger");
			
			cstmt = conn.prepareCall("{call pro_sel2(?,?)}");//db에서 출력용1개 입력용1개로 매개변수를 2개 선언함; ? 는 매개변수를 뜻함
			cstmt.registerOutParameter(1, OracleTypes.CURSOR); // OracleTypes  -> jdbc  oracel X;
			cstmt.setInt(2,7);//jikwon_no 가 5이하인 것만 보여줌; 2번째 매개변수의 값을 5를 줘라;
			
			cstmt.execute();//프로시져의 실행;
			
			ocstmt = (OracleCallableStatement)cstmt;
			rs = ocstmt.getCursor(1);//출력파라미터의 값을 받아옴;
			
			
			while(rs.next()) {
				System.out.println(rs.getString(1) + " " +
								   rs.getString(2) + " " +
								   rs.getString(3));
			}
		} catch (Exception e) {
			System.out.println("err : " + e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(cstmt != null) rs.close();
				if(ocstmt != null) ocstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				
			}
		}
	}
	public static void main(String[] args) {
		new DbTest9PLSQL_select();
/*
 create or replace procedure pro_sel(cur1 out SYS_REFCURSOR) is            out 출력용  in 입력용
 begin
 open cur1 for select * from myjik;
 end;
 /
*/
		
/*
 * SQL> create or replace procedure pro_sel2(cur2 out SYS_REFCURSOR,
	no in number) is                                                 -> out 출력 , in 입력 
    begin
	open cur2 for select * from myjik where jikwon_no <= no;
	end;
	/
 */

	}
}
