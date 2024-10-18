package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample4 {

	public static void main(String[] args) {
		// 부서명을 입력받아
		// 해당 부서에 근무하는 사원의
		// 사번, 이름, 부서명, 직급명을
		// 직급코드 오름차순으로 조회

		// 부서명 입력 : 총무부
		// 200 / 선동일 / 총무부 / 대표
		// 202 / 노옹철 / 총무부 / 부사장

		// hint : SQL에서 문자열은 양쪽에 ''(홑따옴표) 필요
		// ex) 총무부 입력 => '총무부'

		// 1. JDBC 객체 참조 변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2-2) DB 연결 정보 작성
			String type = "jdbc:oracle:thin:@"; // 드라이버 종류

			String host = "localhost"; // DB 서버 컴퓨터의 IP 또는 도메인 주소

			String port = ":1521"; // 프로그램 연결을 위한 port 번호

			String dbName = ":XE"; // DBMS 이름(XE == eXpress Edition)

			String userName = "kh_ncw"; // 사용자 계정명
			String password = "kh1234"; // 계정 비밀번호

			conn = DriverManager.getConnection(type + host + port + dbName, userName, password);

			// 스캐너
			Scanner sc = new Scanner(System.in);
			System.out.println("부서명 입력 : ");
			String input = sc.nextLine();
			
			String sql = """
					SELECT 
					EMP_ID, 
					EMP_NAME, 
					NVL(DEPT_TITLE, '없음') DEPT_TITLE,
					 JOB_NAME
				FROM EMPLOYEE
				JOIN JOB USING(JOB_CODE)
				LEFT JOIN DEPARTMENT ON(DEPT_ID = DEPT_CODE)
				WHERE DEPT_TITLE = '""" + input + "' ORDER BY JOB_CODE";
				
			
			// 4. Statement 객체 생성
			stmt = conn.createStatement();

			// 5. SQL 수행 후 결과 반환 받기
			rs = stmt.executeQuery(sql);
			
			boolean flag = true;

			// 6. 한 행씩 접근해서 컬럼 값 받아오기
			
			while (rs.next()) {
				flag = false;
				
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String deptTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");

				System.out.printf("%s / %s / %s / %s \n", empId, empName, deptTitle, jobName );
			}
			
			
			if(flag) {
				System.out.println("일치하는 부서가 없습니다.");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
