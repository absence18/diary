package gdu.diary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.diary.service.MemberService;
import gdu.diary.vo.Member;

@WebServlet("/auth/removeMember")
public class RemoveMemberController extends HttpServlet {
	
	private MemberService memberService;
	
	// 비밀번호 입력 폼
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/auth/removeMember.jsp").forward(request, response);
		
	}
	
	// 삭제 액션
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberPw = request.getParameter("memberPw");
		
		Member member = (Member)request.getSession().getAttribute("sessionMember");
		member.setMemberPw(memberPw);
		
		this.memberService = new MemberService();
		boolean result = this.memberService.removeMemberByKey(member);
		
		if(result == false) {
			System.out.print("회탈실!");
			response.sendRedirect(request.getContextPath() + "/auth/removeMember");
			return;
		}
		response.sendRedirect(request.getContextPath() + "/auth/logout");
		
	}

}
