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

@WebServlet("/std/add")
public class AddStudentServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			// TodoListServiceImpl 객체 생성
			stdListService service = new stdListServiceImpl();

			
			// 요청 시 전달받은 파라미터 얻어오기
			String stdName = req.getParameter("stdName");
			int stdAge = Integer.parseInt(req.getParameter("stdAge"));
			String stdGender = req.getParameter("stdGender");
			String stdScore = req.getParameter("stdScore");
			
			// 서비스 호출 후 결과 반환받기
			int result = service.stdAdd(stdName, stdAge, stdGender, stdScore);
			
			// 성공/ 실패 메시지 세팅하기
			String message = null;
			if(result > 0) message = "추가 성공!";
			else		   message = "추가 실패...";
			
			// 기존 req를 사용할 수 없기 때문에
			// session을 이용해서 message를 세팅
			HttpSession session = req.getSession();
			session.setAttribute("message", message);
			
			
			// 메인 페이지로 redirect(재요청)
			resp.sendRedirect("/");
			// -> @WebServlet("/") 가 작성된 Servlet을 재요청
						
			// redirect는 무조건 get 방식!
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String path = 
				"/WEB-INF/views/addStudent.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
		
	}
	
	
	

}
