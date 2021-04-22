package gdu.diary.dao;

public class MemberQuery {
	
	public final static String SELECT_MEMBER_BY_KEY;
	
	static {
		SELECT_MEMBER_BY_KEY = "SELECT member_no memberNo, member_id memberId FROM member WHERE member_id=? AND member_pw=PASSWORD(?)";
	}

}
