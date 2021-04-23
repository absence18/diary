package gdu.diary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gdu.diary.util.DBUtil;
import gdu.diary.vo.Member;

public class MemberDao {
	
	private DBUtil dbUtil;
	
	// 회원 정보 수정 메소드
	public int updateMemberByKey(Connection conn, Member member) throws SQLException{
		
		this.dbUtil = new DBUtil();

		int rowCnt = 0;
		PreparedStatement stmt = null;
		
		try{
			
			stmt = conn.prepareStatement(MemberQuery.UPDATE_MEMBER_BY_KEY);
			stmt.setString(1, member.getMemberPw());
			stmt.setString(2, member.getMemberId()); 
			System.out.println(stmt + "<------MemberDao.updateMemberByKey의 stmt");
			rowCnt = stmt.executeUpdate();

		} finally {
			//여기서 conn을 닫으면 안됨. 롤백 불가능
			this.dbUtil.close(null, stmt, null);
			
		}
		return rowCnt;
	}

	
	// 회원 가입 메소드
	public int insertMemberByKey(Connection conn, Member member)  throws SQLException{
		
		this.dbUtil = new DBUtil();

		int rowCnt = 0;
		PreparedStatement stmt = null;

		try{
			
			stmt = conn.prepareStatement(MemberQuery.INSERT_MEMBER_BY_KEY);
			stmt.setString(1, member.getMemberId()); 
			stmt.setString(2, member.getMemberPw());
			rowCnt = stmt.executeUpdate();

		} finally {
			this.dbUtil.close(null, stmt, null);
			
		}
		return rowCnt;
	}
	
	
	// 회원 삭제 메서드
	public int deleteMemberByKey(Connection conn, Member member) throws SQLException {
		
		this.dbUtil = new DBUtil();
		
		int rowCnt = 0;
		PreparedStatement stmt = null;
		System.out.println(member);
		
		try {
			
			stmt = conn.prepareStatement(MemberQuery.DELETE_MEMBER_BY_KEY);
			stmt.setInt(1, member.getMemberNo());
			stmt.setString(2, member.getMemberPw());
			rowCnt = stmt.executeUpdate();
			
		} finally {
			this.dbUtil.close(null, stmt, null);
			
		}
		return rowCnt;
	}
	
	
	// 로그인 메서드
	public Member selectMemberByKey(Connection conn, Member member) throws SQLException {
		
		this.dbUtil = new DBUtil();
		Member returnMember = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		System.out.println(member);
		
		try {
			stmt = conn.prepareStatement(MemberQuery.SELECT_MEMBER_BY_KEY);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			rs = stmt.executeQuery();
			System.out.println(stmt);
			
			if(rs.next()) {
				System.out.println("@@@@@@@@@@@rs진입여부");
				returnMember = new Member();
				returnMember.setMemberNo(rs.getInt("memberNo"));
				returnMember.setMemberId(rs.getString("memberId"));
			}
		
			
		} finally {
			this.dbUtil.close(null, stmt, rs);
			
		}
		return returnMember;
	}
}
