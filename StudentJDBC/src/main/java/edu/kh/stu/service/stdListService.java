package edu.kh.stu.service;

import java.util.List;

import edu.kh.stu.dto.Student;

public interface stdListService {

	
	/** 학생 정보 출력
	 * @return
	 * @throws Exception
	 */
	List<Student> stdListFullView() throws Exception;

	
	
	/** 학생 상세정보 출력
	 * @param stdNo
	 * @return
	 * @throws Exception
	 */
	Student stdDetailView(int stdNo) throws Exception;



	/** 학생 추가 
	 * @param stdName
	 * @param stdAge
	 * @param stdGender
	 * @param stdScore
	 * @return
	 * @throws Exception
	 */
	int stdAdd(String stdName, int stdAge, String stdGender, String stdScore) throws Exception;



	/** 학생 상세정보 수정
	 * @param stdNo
	 * @param stdName
	 * @param stdAge
	 * @param stdGender
	 * @param stdScore
	 * @return
	 * @throws Exception
	 */
	int stdUpdate(int stdNo, String stdName, int stdAge, String stdGender, String stdScore) throws Exception;



	/** 학생 정보 삭제
	 * @param stdNo
	 * @return
	 */
	int stdDelete(int stdNo) throws Exception;

}
