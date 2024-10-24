package edu.kh.stu.controller;

import java.io.IOException;
import java.util.List;

import edu.kh.stu.dto.Student;
import edu.kh.stu.service.stdListService;
import edu.kh.stu.service.stdListServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//"/main" 요청을 매핑하여 처리하는 서블릿
@WebServlet("/main")
public class MainServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			
			stdListService service = new stdListServiceImpl();
			
			List<Student> stdList = service.stdListFullView();
			
			// request scope 에 객체 값 추가하기
			req.setAttribute("stdList", stdList);
		
						
			// 메인 페이지 응답을 담당하는 jsp 에 요청 위임
			String path = 
					"/WEB-INF/views/main.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
