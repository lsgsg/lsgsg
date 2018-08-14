package db.pack1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbTest5Pooling {
	Connection conn;
	Statement stmt;
	ResultSet rs;
	DBConnectionMgr pool;
	
	public DbTest5Pooling() {
		try {
			pool = DBConnectionMgr.getInstance();
			accDb();
		} catch (Exception e) {
			System.out.println("연결실패 : " + e);
		}
	}
	private void accDb() {
		try {
			conn = pool.getConnection();//다운받은 DBConnectinoMgr에 연결도었ㅇ;ㅁ
			String sql = "select * from sangdata";
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println(
				rs.getString(1)+" "+
				rs.getString(2)+" "+
				rs.getString(3)+" "+
				rs.getString(4));
				
			}
		} catch (Exception e) {
			System.out.println("accDb err : " + e);
		}finally {
			pool.freeConnection(conn,stmt,rs);
			//풀링에 대해 이해하기
		}

	}
	public static void main(String[] args) {
		new DbTest5Pooling();

	}

}
