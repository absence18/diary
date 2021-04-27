package gdu.diary.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gdu.diary.service.TodoService;
import gdu.diary.vo.Member;
import gdu.diary.vo.Todo;

@WebServlet("/auth/todoOne")
public class TodoOneController extends HttpServlet {
	private TodoService todoService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int memberNo = ((Member)(session.getAttribute("sessionMember"))).getMemberNo();

		this.todoService = new TodoService();

		int todoNo = Integer.parseInt(request.getParameter("todoNo"));

		//todoService로 요청
		Todo todoOne = this.todoService.getTodoOne(todoNo, memberNo);

		//뷰로 포워딩
		request.setAttribute("todoOne", todoOne);

		System.out.println("TodoOneController -> todoOne.jsp 포워딩");
		request.getRequestDispatcher("/WEB-INF/view/auth/todoOne.jsp").forward(request, response);
	}

}
