package edu.kh.jdbc.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 
 */
public class JDBCTemplate {

	
	/*
	 * Template : 양식, 틀, 주형
	 * -> "미리 만들어뒀다 " 의미
	 * 
	 * 
	 * JDBCTemplate :
	 *  JDBC 관련 작업을 위한 코드를 
	 *  미리 작성해거 제공하는 클래스
	 *  
	 *  
	 *  - Connection 생성
	 *  - AutoCommit false
	 *  - commit / rollback
	 *  - 각종 close() 
	 *  
	 *  ****** 중요 *******
	 *  
	 *  어디서든 JDBCTemplate
	 *  
	 * */
	
	private static Connection conn = null;
	// -> static 메서드에서 사용 가능한 필드로 static 필드 선언
	
	// 메서드
	
	/** 호출 시 Connection 객체를 생성해서 반환하는 메서드
	 * @return conn
	 */
	public static Connection getConnection() {
		return null;
	}
	
	
	// ---------------------------------------------------------
	
	/** 전달 받은 커넥션에서 수행한 SQL을 COMMIT 하는 메서드
	 * @param conn
	 */
	public static void commit(Connection conn) {
		
	}
	
	/** 전달 받은 커넥션에서 수행한 SQL을 ROLLBACK 하는 메서드
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		
	}
	
	/** 전달 받은 커넥션을 close(자원 반환)하는 메서드
	 * @param conn
	 */
	public static void close(Connection conn) {
		
	}
	
	/** 전달 받은 Statement + PreparedStatement 둘 다 close()  
	 *  + 다형성 업캐스팅 적용
	 *  -> PreparedStatement는 Statement 자식
	 * @param stmt
	 */
	public static void close(Statement stmt) {
		
	}
	
	
	/** 전달 받은 ResultSet을 close()하는 메서드
	 * @param rs
	 */
	public static void close(ResultSet rs) {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
