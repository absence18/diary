package gdu.diary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gdu.diary.vo.Todo;

public class TodoDao {
	
	// 달력안에 todo 출력 메서드
	public List<Todo> selectTodoListByDate(Connection conn, int memberNo, int targetYear, int targetMonth) throws SQLException {
		
		List<Todo> list = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			/*
			 * SELECT todo_no todoNo, LEFT(todo_title, 10) todoTitle FROM todo 
			 * WHERE member_no=? AND YEAR(todo_date)=? AND MONTH(todo_date)=?
			 */
			stmt = conn.prepareStatement(TodoQuery.SELECT_TODO_LIST_BY_DATE);
			stmt.setInt(1, memberNo);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			
			rs = stmt.executeQuery();
			
			// if로 하면 todo 2개이상 출력안됨... //
			while(rs.next()) {
				
				Todo todo = new Todo();
				todo.setTodoNo(rs.getInt("todoNo"));
				todo.setTodoDate(rs.getString("todoDate"));
				todo.setTodoTitle(rs.getString("todoTitle"));
                todo.setTodoFontColor(rs.getString("todoFontColor"));
				list.add(todo);
				
			}
			
		} finally {
			rs.close();
			stmt.close();
			
		}
		return list;
	}
	
	
	
	// todo를 추가하는 메서드
	public int insertTodo(Connection conn, Todo todo) throws SQLException {
		
		int rowCnt = 0;
		PreparedStatement stmt = null;
		
		try {
			
			stmt = conn.prepareStatement(TodoQuery.INSERT_TODO);
			stmt.setInt(1, todo.getMemberNo());
			stmt.setString(2, todo.getTodoDate());
			stmt.setString(3, todo.getTodoTitle());
			stmt.setString(4, todo.getTodoContent());
			stmt.setString(5, todo.getTodoFontColor());
			
			rowCnt = stmt.executeUpdate();
			
		} finally {
			stmt.close();
			
		}
		return rowCnt;
	}
	
	
	// 멤버를 삭제하며 todo를 삭제하는 메서드
	public int deleteTodoByMemeber(Connection conn, int memberNo) throws SQLException {
		
		int rowCnt = 0;
		PreparedStatement stmt = null;
		
		try {
			
			stmt = conn.prepareStatement(TodoQuery.DELETE_TODO_BY_MEMBER);
			stmt.setInt(1, memberNo);
			rowCnt = stmt.executeUpdate();
			
		} finally {
			stmt.close();
			
		}
		return rowCnt;
	}

}
