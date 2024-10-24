package edu.kh.stu.dao;

import java.sql.Connection;
import java.util.List;

import edu.kh.stu.dto.Student;

public interface stdListDAO {

	/** 학생 목록 전체 조회
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	List<Student> stdListFullView(Connection conn) throws Exception;

}
