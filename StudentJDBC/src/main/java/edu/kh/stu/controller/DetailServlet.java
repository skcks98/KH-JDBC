package edu.kh.stu.controller;

import java.io.IOException;

import edu.kh.stu.dto.Student;
import edu.kh.stu.service.stdListService;
import edu.kh.stu.service.stdListServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/std/detail")
public class DetailServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
		
		int stdNo = Integer.parseInt(req.getParameter("stdNo"));
		
		stdListService service = new stdListServiceImpl();
		
		Student std = service.stdDetailView(stdNo);
		
		if(std == null) {
			
			// session 객체 생성 후 message 세팅하기
			HttpSession session = req.getSession();
			session.setAttribute("message", "학생이 존재하지 않습니다.");
			
			resp.sendRedirect("/");
			return;
			
		}
		
		// todo가 존재하는 경우
		// detail.jsp로 forward해서 응답
		req.setAttribute("std", std);
		
		// JSP 파일 경로(webapp 폴더 기준으로 작성)
		String path = "/WEB-INF/views/detail.jsp";
		
		// 요청 발송자를 이용해서 요청 위임
		req.getRequestDispatcher(path).forward(req, resp);
		
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
