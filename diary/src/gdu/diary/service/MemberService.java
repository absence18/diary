package gdu.diary.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import gdu.diary.dao.MemberDao;
import gdu.diary.dao.TodoDao;
import gdu.diary.util.DBUtil;
import gdu.diary.vo.Member;

public class MemberService {
	
	private DBUtil dbUtil;
	private MemberDao memberDao;
	private TodoDao todoDao;
	
	// 회원 정보 수정 DB
	public boolean modifyMemberByKey(Member member) {
		
		this.dbUtil = new DBUtil();
		this.memberDao = new MemberDao(); 
		Connection conn = null;
		int rowCnt = 0;

		try {
			
			conn = dbUtil.getConnection();
			rowCnt = this.memberDao.updateMemberByKey(conn, member);
			conn.commit();
			
		} catch (SQLException e) {
			
			try {
				conn.rollback();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
				
			}
			e.printStackTrace();
			return false;
			
		} finally {
			dbUtil.close(conn, null, null);
			
		}
		return rowCnt > 0;
	}
	
	
	// 회원가입 서비스 DB연동
	public int addMemberByKey(Member member) {
		
		this.dbUtil = new DBUtil();
		this.memberDao = new MemberDao();
		Connection conn = null;
		int returnCnt = 0;
		
		try {
			
			conn = dbUtil.getConnection();
			returnCnt = this.memberDao.insertMemberByKey(conn, member);
			conn.commit();
			
		} catch (SQLException e) {
			
			try {
				
				conn.rollback();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
				
			}
			e.printStackTrace();
			
		} finally {
			dbUtil.close(conn, null, null);
			
		}
		return returnCnt;
	}
	
	
	// 회원삭제 서비스 DB연동
	// 삭제 성공하면 true 실패하면(rollback) false
	public boolean removeMemberByKey(Member member) {
		
		this.dbUtil = new DBUtil();
		this.memberDao = new MemberDao();
		this.todoDao = new TodoDao();
		
		Connection conn = null;
		int todoRowCnt = 0;
		int memberRowCnt = 0;
		
		try {
			
			conn = dbUtil.getConnection();
			todoRowCnt = this.todoDao.deleteTodoByMemeber(conn, member.getMemberNo());
			memberRowCnt = this.memberDao.deleteMemberByKey(conn, member);
			conn.commit();
			
		} catch (SQLException e) {
			
			try {
				conn.rollback();
				
			} catch(SQLException e1) {
				e1.printStackTrace();
				
			}
			e.printStackTrace();
			return false;
			
		} finally {
			this.dbUtil.close(conn, null, null);
			
		}
		return (todoRowCnt + memberRowCnt) > 0;
	}
	
	
	// 로그인 서비스 DB연동
	public Member getMemberByKey(Member member) {
		
		this.dbUtil = new DBUtil();
		Member returnMember = null;
		this.memberDao = new MemberDao(); 
		Connection conn = null;
		
		try {
			
			conn = dbUtil.getConnection();
			returnMember = this.memberDao.selectMemberByKey(conn, member);
			System.out.println(returnMember);
			conn.commit();
			
		} catch(SQLException e) {
			
			try {
				conn.rollback();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
				
			}
			e.printStackTrace();
			
		} finally {
			this.dbUtil.close(conn, null, null);
			
		}
		return returnMember;
	}
}
