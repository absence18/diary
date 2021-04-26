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
	
}
