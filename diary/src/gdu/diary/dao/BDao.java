package gdu.diary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BDao {
	
	public void insert(Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;
		
		try {
			
			stmt = conn.prepareStatement("B insert 쿼리");
			stmt.execute();
		
		} finally {
			stmt.close();
			
		}
		
	}

}
