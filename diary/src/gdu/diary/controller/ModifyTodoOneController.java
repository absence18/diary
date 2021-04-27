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

@WebServlet("/auth/modifyTodoOne")
public class ModifyTodoOneController extends HttpServlet {
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

		System.out.println("TodoOneController -> modifyTodoOne.jsp 포워딩");
		request.getRequestDispatcher("/WEB-INF/view/auth/modifyTodoOne.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.todoService = new TodoService();

		HttpSession session = request.getSession();
		int memberNo = ((Member)(session.getAttribute("sessionMember"))).getMemberNo();

		//받아온 데이터
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		String todoTitle = request.getParameter("todoTitle");
		String todoContent = request.getParameter("todoContent");
		String todoFontColor = request.getParameter("todoFontColor");

		//변경내용 todo객체에 담고 todoService에 수정 요청
		Todo modifyTodo = new Todo();
		modifyTodo.setTodoNo(todoNo);
		modifyTodo.setTodoTitle(todoTitle);
		modifyTodo.setTodoContent(todoContent);
		modifyTodo.setTodoFontColor(todoFontColor);

		System.out.println("ModifyTodoOneController -> todoService.modifyTodo 요청");
		boolean result = this.todoService.modifyTodoOne(modifyTodo, memberNo);

		//수정 실패시
		if(result == false) {
			System.out.println("ModifyTodoOneController // todo 수정 실패");

		} else { //수정 성공
			System.out.println("ModifyTodoOneController // todo 수정 성공");
		}

		//수정후 로그아웃컨트롤러로 리다이렉트
		System.out.println("ModifyTodoOneController -> TodoOneController 리다이렉트");
		response.sendRedirect(request.getContextPath()+"/auth/todoOne?todoNo="+todoNo);

	}

}
