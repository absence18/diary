package gdu.diary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.diary.service.GuGuService;

@WebServlet("/GuGuController")
public class GuGuController extends HttpServlet {
	
	private GuGuService guguService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.guguService = new GuGuService();
		String gugu = guguService.getGuGu();
		
		request.setAttribute("gugu", gugu);
	}

}
