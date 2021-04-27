package gdu.diary.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gdu.diary.service.TodoService;
import gdu.diary.vo.Member;
import gdu.diary.vo.Todo;

@WebServlet("/auth/removeTodoOne")
public class RemoveTodoOneController extends HttpServlet {
	private TodoService todoService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int memberNo = ((Member)(session.getAttribute("sessionMember"))).getMemberNo();

		this.todoService = new TodoService();

		int todoNo = Integer.parseInt(request.getParameter("todoNo"));

		//todoService로 요청
		boolean result = this.todoService.removeTodoOne(todoNo, memberNo);

		//삭제 실패시
		if(result == false) {
			System.out.println("todo삭제실패 RemoveTodoOneController -> DiaryController 리다이렉트");
			response.sendRedirect(request.getContextPath()+"/auth/todoOne?todoNo="+todoNo);
			return;
		}
		//삭제성공시 로그아웃컨트롤러로 리다이렉트
		System.out.println("회원탈퇴성공 RemoveTodoOneController -> DiaryController 리다이렉트");
		response.sendRedirect(request.getContextPath()+"/auth/diary");
	}

}
