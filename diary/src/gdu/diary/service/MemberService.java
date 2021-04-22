package gdu.diary.service;

import java.sql.Connection;
import java.sql.SQLException;

import gdu.diary.dao.MemberDao;
import gdu.diary.util.DBUtil;
import gdu.diary.vo.Member;

public class MemberService {
	
	private DBUtil dbUtil;
	private MemberDao memberDao;
	
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
