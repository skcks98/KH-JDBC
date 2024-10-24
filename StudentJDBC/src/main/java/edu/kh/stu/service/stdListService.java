package edu.kh.stu.service;

import java.util.List;

import edu.kh.stu.dto.Student;

public interface stdListService {

	
	/** 학생 정보 출력
	 * @return
	 * @throws Exception
	 */
	List<Student> stdListFullView() throws Exception;

}
