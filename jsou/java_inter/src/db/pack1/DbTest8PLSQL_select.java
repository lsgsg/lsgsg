package db.pack1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.internal.OracleCallableStatement;

public class DbTest8PLSQL_select {
	Connection conn;
	CallableStatement cstmt;
	OracleCallableStatement ocstmt;
	ResultSet rs;
	
	public DbTest8PLSQL_select() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			
			cstmt = conn.prepareCall("{call pro_sel(?)}");//db에서 출력용으로 procedure 만들었음;
			cstmt.registerOutParameter(1, OracleTypes.CURSOR); // OracleTypes  -> jdbc  oracel X;
			cstmt.execute();
			
			ocstmt = (OracleCallableStatement)cstmt;
			rs = ocstmt.getCursor(1);
			
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
		new DbTest8PLSQL_select();
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
