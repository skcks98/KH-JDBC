package edu.kh.jdbc.service;

// import static : 지정된 경로에 존재하는 static 구문을 모두 얻어와
// 클래스명.메서드명이 아닌 메서드명() 만 작성해도 호출 가능하게함
import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.common.JDBCTemplate;


import edu.kh.jdbc.dao.UserDAO;
import edu.kh.jdbc.dto.User;

public class UserService {

	// 필드
	private UserDAO dao = new UserDAO();
	
	
	/** 전달 받은 아이디와 일치하는 User 정보 반환 서비스
	 * @param input (입력된 아이디)
	 * @return 아이디가 일치하는 회원 정보, 없으면 null
	 */
	public User selectId(String input) {
		
		// 커넥션 생성 
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO 메서드 호출 후 결과 반환 받기
		User user = dao.selectId(conn, input);
		
		// 다쓴 커넥션 닫기
		JDBCTemplate.close(conn);
		
		
		
		return user; // DB 조회 결과 반환
	}


	/** 1.  USER 등록 서비스
	 * @param user : 입력 받은 id, pw, name이 세팅된 객체
	 * @return 삽입 성공한 결과 행의 개수
	 */
	public int insertUser(User user) throws Exception {
		
		// 1. 커넥션 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2. 데이터 가공(할게 없으면 넘어감)
		
		// 3. DAO 메서드 호출(INSERT) 후
		// 결과(삽입 성공한 행 개수, int) 반환 받기
		int result = dao.insertUser(conn, user);
				
		
		// 4. INSERT 수행 결과에 따라 트랜잭션 제어 처리
		if(result > 0) { //INSERT성공
				commit(conn);
			
		} else { // 테스트 실패
			rollback(conn);
		}
		
		
		// 5., Connection 반환하기
		close(conn);
		
	
		// 6. 결과 반환
		return result;
	}


	/** 2. User 전체 조회 서비스
	 * @return 조회된 User가 담긴 List
	 */
	public List<User> selectAll() throws Exception {
		
		// 1. 커넥션 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2. 데이터 가공(없으면 넘어감)
		
		// 3. DAO 메서드 호출 후 결과 반환받기
		List<User> userList = dao.selectAll(conn);
		
		// 4. 만약 DML인 경우 트랜잭션 처리
		// 		SELECT 는 안해도 된다.
		
		// 5. Connection 반환
		close(conn);
		
		// 6. 결과 반환
		return userList;
	}


	/** 3. User 이름 검색 조회 서비스
	 * @return
	 */
	public List<User> selectName(String input) throws Exception {
		Connection conn = getConnection();
		
		List<User> searchList = dao.selectName(conn, input);
		JDBCTemplate.close(conn);
		return searchList;
	}


	/** 4. USER_NO로 User 조회 서비스
	 * @param input 입력한 사용자 번호
	 * @return 일치하는 User 객체, 없으면 null
	 */
	public User selectUser(int input) throws Exception {
	    // Connection 생성
		Connection conn = getConnection();
	    
	    // DAO 호출 결과 반환
	    User user = dao.selectUser(conn, input);
	    
	    // Connection 반환
	    close(conn);
	    
	    // 결과 반환
	    return user;
	}


	/** 5. USER_NO를 입력 받아 일치하는 User 삭제(DELETE)
	 * @param input
	 * @return result
	 */
	public int deleteUser(int input) throws Exception {

		Connection conn = getConnection();
		
		int result = dao.deleteUser(conn, input);
		
		// 결과에 따라 트랜잭션 제어 처리
		
		if (result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}


	
	/** 6. ID,PW 일치하는 회원의 USER_NO 조회 DAO
	 * @param conn
	 * @param userId
	 * @param userPw
	 * @return
	 */
	public int selectUserNo(String userId, String userPw) throws Exception {
		
		Connection conn = getConnection();
		
		// DAO 호출 후 결과 반환 받기
		int userNo = dao.selectUser(conn, userId, userPw);
		
		close(conn);
		
		return userNo;
	}


	
	
	
	/**  userNo가 일치하는 User의 이름 수정 서비스
	 * @param userName
	 * @param userNo
	 * @return result
	 * @throws Exception
	 */
	public int updateName(String userName, int userNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.updateName(conn, userName, userNo);
		
		// 트랜잭션 제어
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		
		
		return result;
	}


	/** 7. 아이디 중복 확인 서비스
	 * @param userId
	 * @return count
	 * @throws Exception
	 */
	public int idCheck(String userId) throws Exception{
		
		Connection conn = getConnection();
		
		int count = dao.idCheck(conn, userId);
		
		close(conn);
		
		return count;
	}


	/** 8.userList에 있는 모든 user INSERT 서비스
	 * @param userList
	 * @return result : 삽입된 행의 개수
	 */
	public int multiInsertUser(List<User> userList) throws Exception{
		
		Connection conn = getConnection();
		
		// 다중 INSERT 방법
		// 1) SQL 을 이용한 다중 INSERT
		// 2) JAVA 반복문을 이용한 다중 INSERT (이거 사용!)
		
		int count = 0; // 삽입 성공한 행의 개수 count
		
		// 1행씩 삽입
		for(User user : userList) {
			int result = dao.insertUser(conn, user);
			count += result; // 삽입 성공한 행의 개수를 count에 누적			
		}
		
		count--; // 강제 실패 처리
		
		
		// 트랜잭션 제어 처리
		// 전체 삽입 성공시 commit / 아니면 rollback(일부 삽입, 전체 실패)
		
		if(count == userList.size()) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		
		return count;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
