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


//jsp와 이름이 같게 하기로함.
@WebServlet("/signUp")
public class SignUpController extends HttpServlet {

	private MemberService memberService;

	//회원가입요청
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 된 상태에서 회원가입 요청이 들어오면 login 컨트롤러로 redirect
		HttpSession session = request.getSession();

		if(session.getAttribute("sessionMember") != null) { 
			response.sendRedirect(request.getContextPath()+"/login");
			return; //

		}
		//로그인상태가아니면 회원가입 뷰로 이동
		request.getRequestDispatcher("/WEB-INF/view/signUp.jsp").forward(request, response);
	}
	//회원가입액션
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.memberService = new MemberService();
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");

		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);

		int Cnt = this.memberService.addMemberByKey(member);
		if(Cnt == 0) {
			System.out.println("회원가입 실패");

		}
		System.out.println("회원가입 성공");

		response.sendRedirect(request.getContextPath() + "/login");
	}

}