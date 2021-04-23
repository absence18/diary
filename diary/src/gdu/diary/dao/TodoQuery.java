package gdu.diary.dao;

public class TodoQuery {
	
	public final static String DELETE_TODO_BY_MEMBER;
	
	static {
		DELETE_TODO_BY_MEMBER = "DELETE FROM todo WHERE member_no = ?";
		
		
	}

}
