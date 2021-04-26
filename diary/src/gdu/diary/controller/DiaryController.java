package gdu.diary.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gdu.diary.service.DiaryService;
import gdu.diary.vo.Member;

@WebServlet("/auth/diary")
public class DiaryController extends HttpServlet {
	
	private DiaryService diaryService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.diaryService = new DiaryService();
		HttpSession session = request.getSession(); // 세션 코드 추가
		
		int memberNo = ((Member)session.getAttribute("sessionMember")).getMemberNo();
		
		String targetYear = request.getParameter("targetYear"); // "2021"
		String targetMonth = request.getParameter("targetMonth"); // 4월이면...3, 5월이면 4,...
		
		Map<String, Object> diaryMap = this.diaryService.getDiary(memberNo, targetYear, targetMonth);
		
		request.setAttribute("diaryMap", diaryMap);
		request.getRequestDispatcher("/WEB-INF/view/auth/diary.jsp").forward(request, response);
		
	}
	
}
