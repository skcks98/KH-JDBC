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

	
	/** 학생 상세 정보 조회
	 * @param conn
	 * @param stdNo
	 * @return
	 * @throws Exception
	 */
	Student stdDetailView(Connection conn, int stdNo) throws Exception;


	/** 학생 추가
	 * @param conn
	 * @param stdName
	 * @param stdAge
	 * @param stdGender
	 * @param stdScore
	 * @return
	 */
	int stdAdd(Connection conn, String stdName, int stdAge, String stdGender, String stdScore) throws Exception;


	
	
	/** 학생 상세정보 수정
	 * @param conn
	 * @param stdNo
	 * @param stdName
	 * @param stdAge
	 * @param stdGender
	 * @param stdScore
	 * @return
	 */
	int stdUpdate(Connection conn, int stdNo, String stdName, int stdAge, String stdGender, String stdScore) throws Exception;


	
	
	
	
	
	/** 학생 정보 삭제
	 * @param conn
	 * @param stdNo
	 * @return
	 * @throws Exception
	 */
	int stdDelete(Connection conn, int stdNo) throws Exception;

}
