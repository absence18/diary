package gdu.diary.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.diary.service.MemberService;
import gdu.diary.vo.Member;


@WebServlet("/auth/modifyMember")
public class ModifyMemberController extends HttpServlet {
	private MemberService memberService;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/auth/modifyMember.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.memberService = new MemberService();
		String memberId = request.getParameter("memberId");
		//새로운 비밀번호
		String newMemberPw = request.getParameter("newMemberPw");


		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(newMemberPw);


		boolean result = this.memberService.modifyMemberByKey(member);

		if(result == false) {
			System.out.println("회원정보 수정 실패");

		}
		System.out.println("회원정보 수정 성공");


		response.sendRedirect(request.getContextPath()+"/auth/logout");
	}

}