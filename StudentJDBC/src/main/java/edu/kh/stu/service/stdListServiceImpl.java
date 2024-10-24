package edu.kh.stu.service;

import java.sql.Connection;
import java.util.List;

import static edu.kh.stu.common.JDBCTemplate.*;
import edu.kh.stu.dao.stdListDAO;
import edu.kh.stu.dao.stdListDAOImpl;
import edu.kh.stu.dto.Student;

public class stdListServiceImpl implements stdListService{

	private stdListDAO dao = new stdListDAOImpl();
	
	@Override
	public List<Student> stdListFullView() throws Exception {
		
		// 커넥션 생성
		Connection conn = getConnection();
		
		// 학생 목록 얻어오기(dao 호출 및 반환 받기)
		List<Student> stdList = dao.stdListFullView(conn);
		
		
		
		return stdList;
	}

}
