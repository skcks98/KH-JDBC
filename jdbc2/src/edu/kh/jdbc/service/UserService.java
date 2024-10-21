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


	/** USER 등록 서비스
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
				commit(conn);
		
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


	/** User 전체 조회 서비스
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


	/** User 이름 검색 조회 서비스
	 * @return
	 */
	public List<User> selectByName(String input) throws Exception {
		
		// 1. 커넥션 생성
		Connection conn = JDBCTemplate.getConnection();
				
		// 2. 데이터 가공(없으면 넘어감)
				
		// 3. DAO 메서드 호출 후 결과 반환받기
		
		 List<User> userList = dao.selectByName(conn, input);
		
		// 4. 만약 DML인 경우 트랜잭션 처리
		// 		SELECT 는 안해도 된다.
				
		// 5. Connection 반환
		
		JDBCTemplate.close(conn);
				
		// 6. 결과 반환
		return userList;
		
	}


	/** USER_NO로 User 조회 서비스
	 * @param userNo (USER_NO)
	 * @return 일치하는 User 객체, 없으면 null
	 */
	public int selectByUserNo(int input) throws Exception {
	    Connection conn = JDBCTemplate.getConnection();
	    
	    // DAO 호출
	    User user = dao.selectByUserNo(conn, input);
	    
	    JDBCTemplate.close(conn);
	    
	    return user;
	}


	public int deleteByUserNo(int input) {
		// TODO Auto-generated method stub
		return null;
	}

}
