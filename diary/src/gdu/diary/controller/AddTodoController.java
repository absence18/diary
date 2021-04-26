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
import gdu.diary.vo.TodoDate;

@WebServlet("/auth/addTodo")
public class AddTodoController extends HttpServlet {
	private TodoService todoService;
	
	// todo 입력폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		int day = Integer.parseInt(request.getParameter("day"));	
		
		TodoDate todoDate = new TodoDate();
		
		todoDate.setYear(year);
		todoDate.setMonth(month);
		todoDate.setDay(day);
		
		System.out.println(todoDate); // todoDate.toString()
		
		request.setAttribute("todoDate", todoDate);
		request.getRequestDispatcher("/WEB-INF/view/auth/addTodo.jsp").forward(request, response);	
		
	}
	
	
	// todo 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		int memberNo = ((Member)(session.getAttribute("sessionMember"))).getMemberNo();
		
		String todoDate = request.getParameter("todoDate");
		String todoTitle = request.getParameter("todoTitle");
		String todoContent = request.getParameter("todoContent");
		String todoFontColor = request.getParameter("todoFontColor");
		
		Todo todo = new Todo();
		
		todo.setMemberNo(memberNo);
		todo.setTodoDate(todoDate);
		todo.setTodoTitle(todoTitle);
		todo.setTodoContent(todoContent);
		todo.setTodoFontColor(todoFontColor);
		
		System.out.println(todo); // todo.toString()
		
		
	//  서비스 호출
		this.todoService = new TodoService();
		this.todoService.addTodo(todo);
		
		String[] arr = todoDate.split("-"); // arr[0] = "2021"
		
		response.sendRedirect(request.getContextPath() + "/auth/diary?targetYear=" + arr[0] + "&targetMonth=" + (Integer.parseInt(arr[1])-1));
		
	}

}
