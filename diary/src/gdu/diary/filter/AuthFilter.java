package gdu.diary.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

@WebFilter("/auth/*") 
public class AuthFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// 로그인 되어있지 않은 상태에서 "/auth/"문자로 시작하는 요청이 들어오면 redirect
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpSession session = httpRequest.getSession();
		
		if(session.getAttribute("sessionMember") == null) { 
			HttpServletResponse httpResponse = (HttpServletResponse)response;
			httpResponse.sendRedirect(httpRequest.getContextPath()+"/login");
			return; // 새로운 요청발생으로 doFilter메서드를 종료
			
		}
		chain.doFilter(request, response);	
	}
}