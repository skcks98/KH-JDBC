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

@WebServlet("/std/update")
public class UpdateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			int stdNo = Integer.parseInt(req.getParameter("stdNo"));
			
			stdListService service = new stdListServiceImpl();
			Student std = service.stdDetailView(stdNo);
			
			if(std == null) {
				// 메인페이지로 redirect 
				resp.sendRedirect("/");
				return;
			}
			
			// request scope에 todo 객체 세팅
			req.setAttribute("std", std);
						
			// 요청발송자를 통해 forward
			String path = "/WEB-INF/views/update.jsp";
			req.getRequestDispatcher(path).forward(req, resp); // 요청 위임
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			// 전달받은 파라미터 얻어오기 (제목, 상세내용, todo번호)
			String stdName = req.getParameter("stdName");
			int stdAge = Integer.parseInt(req.getParameter("stdAge"));
			String stdGender = req.getParameter("stdGender");
			String stdScore = req.getParameter("stdScore");
			int stdNo = Integer.parseInt(req.getParameter("stdNo"));
			
			stdListService service = new stdListServiceImpl();
			int result = service.stdUpdate(stdNo, stdName, stdAge, stdGender, stdScore);
			
			String url = null;
			String message = null;
			
			if(result > 0) { // 성공
				url = "/";
				message = "수정 되었습니다";
				
			} else { // 실패
				url = "/std/detail?stdNo=" + stdNo;
				
				message = "수정 실패";
			}
			
			// session 객체에 속성 추가 
			req.getSession().setAttribute("message", message);
						
			// redirect는 GET 방식 요청
			resp.sendRedirect(url);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

}
