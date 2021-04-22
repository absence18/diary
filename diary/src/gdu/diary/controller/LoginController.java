package gdu.diary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gdu.diary.service.MemberService;
import gdu.diary.vo.Member;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	
	private MemberService memberService;
	
	// 로그인 폼
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
		
	}
	
	// 로그인 액션
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.memberService = new MemberService();
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		// System.out.println(member);
		Member returnMember = this.memberService.getMemberByKey(member);
		if(returnMember == null) {
			System.out.println("로그인 실패");
			
		} else {
			
			System.out.println("로그인 성공");
			HttpSession session = request.getSession();
			session.setAttribute("sessionMember", returnMember);
			
		}
		
		response.sendRedirect(request.getContextPath() + "/login");
		
	}
}