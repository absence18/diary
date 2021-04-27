package gdu.diary.service;

import java.sql.Connection;
import java.sql.SQLException;

import gdu.diary.dao.TodoDao;
import gdu.diary.util.DBUtil;
import gdu.diary.vo.Todo;

public class TodoService {
	private TodoDao todoDao;
	private DBUtil dbUtil;
	
	// todoOne 서비스
	public Todo getTodoOne(int todoNo, int memberNo) {
		this.todoDao = new TodoDao();
		this.dbUtil = new DBUtil();

		Connection conn = null;
		Todo returnTodo = null;

		try {
			
			conn = this.dbUtil.getConnection();
			returnTodo = this.todoDao.selectTodoOne(conn, todoNo, memberNo);

			//문제없으면 커밋 진행
			conn.commit();
			
		} catch(SQLException e) {
			//코드진행중 예외발생시 롤백
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return returnTodo;
	}
	
	// todoOne 수정 서비스
	public boolean modifyTodoOne(Todo modifyTodo, int memberNo) {
		this.todoDao = new TodoDao();
		this.dbUtil = new DBUtil();

		Connection conn = null;

		int RowCnt = 0;

		try {
			conn = dbUtil.getConnection();
			RowCnt = this.todoDao.updateTodoOne(conn, modifyTodo, memberNo);

			//문제없으면 커밋 진행
			conn.commit();

		} catch (SQLException e) {
			//코드진행중 예외발생시 롤백
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return false;
			}
			e.printStackTrace();
			return false;
		} finally {
			try {
				//커넥션 반환.
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return RowCnt > 0;
	}
	
	// todoOne 삭제 서비스
	public boolean removeTodoOne(int todoNo,int memberNo) {
		this.dbUtil = new DBUtil();
		this.todoDao = new TodoDao(); 

		Connection conn = null;
		int rowCnt = 0;

		try {
			//db연결
			conn = dbUtil.getConnection();

			//
			System.out.println("TodoService.removeTodoOne -> todoDao.deleteTodoOne 요청");
			rowCnt = this.todoDao.deleteTodoOne(conn, todoNo, memberNo);

			//커밋
			conn.commit();
		} catch (SQLException e) {
			try {
				//도중 예외 발생시 롤백.
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();

			System.out.println("TodoService.removeTodoOne 응답");
			return false;
		} finally {
			//커넥션 사용 종료후 닫아주기
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//로그인 된 상태에서 회원삭제가 진행되기때문에 투두리스트와 멤버가 존재하지않아서 둘의 합이 0일 가능성 없음.
		System.out.println("TodoService.removeTodoOne 응답");
		return (rowCnt > 0);
	}
	
	
	// todo 추가 서비스
	public int addTodo(Todo todo) {
		this.dbUtil = new DBUtil();
		this.todoDao = new TodoDao();
		
		Connection conn = null;
		int rowCnt = 0;
		
		try {
			
			conn = dbUtil.getConnection();
			rowCnt = this.todoDao.insertTodo(conn, todo);
			conn.commit();
			
		} catch(SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowCnt;
	}

}
