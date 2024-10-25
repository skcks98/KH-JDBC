package edu.kh.stu.service;

import java.sql.Connection;
import java.util.List;

import static edu.kh.stu.common.JDBCTemplate.*;
import static edu.kh.stu.common.JDBCTemplate.close;
import static edu.kh.stu.common.JDBCTemplate.commit;
import static edu.kh.stu.common.JDBCTemplate.rollback;

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
		
		close(conn); // 커넥션 닫기
		
		
		return stdList;
	}

	
	
	@Override
	public Student stdDetailView(int stdNo) throws Exception {
		
		Connection conn = getConnection();
		
		Student std = dao.stdDetailView(conn, stdNo);
		
		close(conn);
		
		return std;
	}



	@Override
	public int stdAdd(String stdName, int stdAge, String stdGender, String stdScore) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.stdAdd(conn, stdName, stdAge, stdGender, stdScore);
		
		
		//INSERT 구문 커밋,롤백
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}



	@Override
	public int stdUpdate(int stdNo, String stdName, int stdAge, String stdGender, String stdScore) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.stdUpdate(conn, stdNo, stdName, stdAge, stdGender, stdScore);
		
		// update 이니까 commit rollback
		if(result > 0) commit(conn);
		else		   rollback(conn);
						
		close(conn);
		

		
		return result;
	}



	@Override
	public int stdDelete(int stdNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.stdDelete(conn, stdNo);
		
		
		// delete -  conmmit, rollback
		
		if(result >  0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
	
		return result;
	}

}
