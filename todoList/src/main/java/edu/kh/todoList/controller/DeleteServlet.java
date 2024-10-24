package edu.kh.todoList.controller;

import java.io.IOException;

import edu.kh.todoList.dto.Todo;
import edu.kh.todoList.service.TodoListService;
import edu.kh.todoList.service.TodoListServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/todo/delete")
public class DeleteServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			// 전달받은 파라미터 얻어오기
			int todoNo = Integer.parseInt(req.getParameter("todoNo"));
			
			
			TodoListService service = new TodoListServiceImpl();
			int result = service.todoDelete(todoNo);
			
			String url = null;
			String message = null;
			
			if(result > 0) { // 삭제 성공
				url = "/";
				message = "할 일이 삭제되었습니다";
			} else { // 삭제 실패
				url = "/todo/detail?todoNo=" + todoNo;
				message = "Todo가 존재하지 않습니다";
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
