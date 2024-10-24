package edu.kh.todoList.service;

import java.util.Map;

import edu.kh.todoList.dto.Todo;

public interface TodoListService {

	/** 할 일 목록 반환 서비스
	 * @return todoList + 완료 개수
	 */
	Map<String, Object> todoListFullView() throws Exception;

	
	
	
	
	/** 할 일 추가 서비스
	 * @param title
	 * @param detail
	 * @return int 성공시 추가된 행의 개수 / 실패 시 0 반환
	 */
	int todoAdd(String title, String detail) throws Exception;





	/** 할 일 상세조회 서비스
	 * @param todoNo
	 * @return null 
	 */
	Todo todoDeatilView(int todoNo) throws Exception;





	/** 완료 여부 변경 서비스
	 * @param todoNo
	 * @return
	 */
	int todoComplete(int todoNo) throws Exception;





	/** 상세정보 수정 서비스
	 * @param todoNo
	 * @param title
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	int todoUpdate(int todoNo, String title, String detail) throws Exception;





	/** 상세정보 삭제 서비스
	 * @param todoNo
	 * @return
	 * @throws Exception
	 */
	int todoDelete(int todoNo) throws Exception;
	
	
}
