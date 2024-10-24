package edu.kh.todoList.dao;

import java.sql.Connection;
import java.util.List;

import edu.kh.todoList.dto.Todo;

public interface TodoListDAO {

	/** 할 일 목록 전체 조회
	 * @param conn
	 * @return todoList
	 */
	List<Todo> todoListFullView(Connection conn) throws Exception;

	
	
	/** 완료된 할 일 개수 조회
	 * @param conn
	 * @return 완료된 개수
	 */
	int getCompleteCount(Connection conn) throws Exception;



	/** 할 일 추가
	 * @param conn
	 * @param title
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	int todoAdd(Connection conn, String title, String detail) throws Exception;



	/** 할 일 상세 조회
	 * @param conn
	 * @param todo
	 * @return
	 */
	Todo todoDetailView(Connection conn, int todoNo) throws Exception;



	/** 완료 여부 변경
	 * @param conn
	 * @param todoNo
	 * @return
	 */
	int todoComplete(Connection conn, int todoNo) throws Exception;



	int todoUpdate(Connection conn, int todoNo, String title, String detail) throws Exception;



	int todoDelete(Connection conn, int todoNo) throws Exception;



	

	
	
	
	
}
