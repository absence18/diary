package gdu.diary.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/diary", "root", "java1004");
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		}
		// finally 절을 구현하여 conn.close()를 하면 안되는 이유?
		// -> conn을 요청한 쪽에서 사용할 수가 없기 때문에..
		return conn;
	}
	
	// 2) 1)의 이유로 메서드 호출 쪽에서 자원을 해제(close)해야하는데 편하게 해제 가능하도록 메서드 제공
	public void close(Connection conn, PreparedStatement stmt, ResultSet rs) {
		
		// 생성된 역순으로 해제
		if(rs != null) {
			try {
				rs.close();
				
			} catch(Exception e) {
				e.printStackTrace();
				
			}
		}
		if(stmt != null) {
			try {
				rs.close();
				
			} catch(Exception e) {
				e.printStackTrace();
				
			}
		}
		if(conn != null) {
			try {
				conn.close();
				
			} catch(Exception e) {
				e.printStackTrace();
				
			}
		}
	}
}
