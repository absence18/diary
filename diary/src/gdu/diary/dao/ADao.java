package gdu.diary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
// 트랜잭션
public class ADao {
	
	public void insert(Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;
		
		try {
			
			stmt = conn.prepareStatement("A insert 쿼리");
			stmt.execute();
			
		} finally {
			stmt.close();
			
		}
		
	}

}
