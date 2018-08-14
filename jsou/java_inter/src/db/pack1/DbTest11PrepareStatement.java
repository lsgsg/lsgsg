package db.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbTest11PrepareStatement {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public DbTest11PrepareStatement() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			
			String sql = "select * from sangdata order by code ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//전체자료 읽기
			while(rs.next()) {
					System.out.println(rs.getString(1)+" "+
									   rs.getString(2)+" "+
									   rs.getString(3)+" "+
									   rs.getString(4));
				}
			
			System.out.println();
			//부분자료읽기
			String co = "1";
			sql = "select * from sangdata where code = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, co);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getString("code")+" "+
								   rs.getString("sang")+" "+
								   rs.getString("su")+" "+
								   rs.getString("dan"));
				
			/*
			//자료 추가
			String isql = "insert into sangdata values(?,?,?,?)";
			pstmt = conn.prepareStatement(isql);
			pstmt.setString(1,"10");
			pstmt.setString(2,"신상품");
			pstmt.setInt(3,100);
			pstmt.setString(4,"5000");
			
			int re = pstmt.executeUpdate();
			if(re == 1 ) {
				System.out.println("추가 성공");
			}else {
				System.out.println("추가 실패");
			}
		
				
			//자료 수정;
			String usql = "update sangdata set sang = ? ,su = ?, dan = ? where code = ?";
			pstmt = conn.prepareStatement(usql);
			pstmt.setString(1, "초코송이");
			pstmt.setInt(2,10);
			pstmt.setInt(3,3000);
			pstmt.setString(4,"1");
			pstmt.executeUpdate();
			 */	
			
			//자료 삭제;
			String dsql= "delete from sangdata where code = ? ";
			pstmt = conn.prepareStatement(dsql);
			pstmt.setString(1, "");
			//pstmt.executeUpdate();
			
			if(pstmt.executeUpdate() > 0) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}
				
				
				
				
				
				
				
				
			}else {
				System.out.println("그런자료는 없네요");
			}
			
		} catch (Exception e) {
			System.out.println("err : " + e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null ) pstmt.close();
				if(conn != null ) conn.close();
			} catch (Exception e2) {
				
			}
			
		}
	}

	public static void main(String[] args) {
		new DbTest11PrepareStatement();

	}

}
