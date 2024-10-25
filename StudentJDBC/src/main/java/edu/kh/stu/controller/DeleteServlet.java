package edu.kh.stu.controller;

import java.io.IOException;

import edu.kh.stu.service.stdListService;
import edu.kh.stu.service.stdListServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/std/delete")
public class DeleteServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			int stdNo = Integer.parseInt(req.getParameter("stdNo"));
			
			stdListService service = new stdListServiceImpl();
			int result = service.stdDelete(stdNo);
			
			String url = null;
			String message = null;
			
			if(result > 0) { // 삭제 성공
				url = "/";
				message = "학생이 삭제되었습니다";
			} else { // 삭제 실패
				url = "/std/detail?stdNo=" + stdNo;
				message = "학생이 존재하지 않습니다";
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
