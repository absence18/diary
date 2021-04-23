package gdu.diary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import gdu.diary.util.DBUtil;

public class TodoDao {
	
	private DBUtil dbUtil;
	
	// 멤버를 삭제하며 todo를 삭제하는 메서드
	public int deleteTodoByMemeber(Connection conn, int memberNo) throws SQLException {
		
		this.dbUtil = new DBUtil();
		
		int rowCnt = 0;
		PreparedStatement stmt = null;
		
		try {
			
			stmt = conn.prepareStatement(TodoQuery.DELETE_TODO_BY_MEMBER);
			stmt.setInt(1, memberNo);
			rowCnt = stmt.executeUpdate();
			
		} finally {
			this.dbUtil.close(null, stmt, null);
			
		}
		return rowCnt;
	}

}
