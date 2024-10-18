package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCExample6 {

	public static void main(String[] args) {

		// 아이디, 비밀번호, 이름을 입력받아
		// 아이디, 비밀번호가 일치하는 사용자(TB_USER)의
		// 이름을 수정(UPDATE)

		Connection conn = null;

		PreparedStatement pstmt = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2-2) DB 연결 정보 작성
			String url = "jdbc:oracle:thin:@localhost:1521:XE";

			String userName = "kh_ncw"; // 사용자 계정명
			String password = "kh1234"; // 계정 비밀번호

			conn = DriverManager.getConnection(url, userName, password);
			
			// 오토커밋 끄기
			conn.setAutoCommit(false);
			
			

			Scanner sc = new Scanner(System.in);
			System.out.println("아이디 입력 : ");
			String id = sc.nextLine();

			System.out.println("비밀번호 입력 : ");
			String pw = sc.nextLine();

			System.out.println("수정할 이름 입력 : ");
			String name = sc.nextLine();

			String sql = """
					UPDATE TB_USER
					SET USER_NAME = ? 
					WHERE USER_ID = ? AND USER_PW = ? 
					""";
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);

			// ?에 알맞는 값 세팅
			pstmt.setString(1, name); // 1번째 ?에 name 값 매핑
	        pstmt.setString(2, id);   // 2번째 ?에 id 값 매핑
	        pstmt.setString(3, pw);   // 3번째 ?에 pw 값 매핑
			// -> SQL 세팅 끝 -> 실행 결과 반환 받기

			// PreparedStatement 이용하여 sql 실행
			int result = pstmt.executeUpdate();

			
			// 성공 시 "수정 성공" + COMMIT
			if (result > 0) { // insert 성공 시
				System.out.println("수정 성공!"); // commit 수행 -DB에 update 영구 반영
				conn.commit();
				
			} else {
				// 실패 시 "아이디 또는 비밀번호 불일치" + ROLLBACK
				
				System.out.println("아이디 또는 비밀번호 불일치");
				conn.rollback();
			}

			
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
