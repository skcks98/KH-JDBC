package edu.kh.jdbc.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.dto.User;
import edu.kh.jdbc.service.UserService;

public class UserView {
	
	// 필드
	private Scanner sc = new Scanner(System.in);
	private UserService service = new UserService();
	
	
	
	/**
	 * JDBCTemplate 사용 테스트
	 */
	public void test() {
		
		// 입력된 ID와 일치하는 USER 정보 조회
		System.out.println("ID 입력 : ");
		String input = sc.nextLine();
		
		// 서비스 호출 후 결과 반환 받기
		User user = service.selectId(input);
		
		// 결과 출력
		System.out.println(user);
		
	}
	
	/** User 관리 프로그램 메인 메뉴
	 */
	public void mainMenu() {
		
		int input = 0;
		
		do {
			try {
				
				System.out.println("\n===== User 관리 프로그램 =====\n");
				System.out.println("1. User 등록(INSERT)");
				System.out.println("2. User 전체 조회(SELECT)");
				System.out.println("3. User 중 이름에 검색어가 포함된 회원 조회 (SELECT)");
				System.out.println("4. USER_NO를 입력 받아 일치하는 User 조회(SELECT)");
				System.out.println("5. USER_NO를 입력 받아 일치하는 User 삭제(DELETE)");
				System.out.println("6. ID, PW가 일치하는 회원이 있을 경우 이름 수정(UPDATE)");
				System.out.println("7. User 등록(아이디 중복 검사)");
				System.out.println("8. 여러 User 등록하기");
				System.out.println("0. 프로그램 종료");
				
				System.out.print("메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine(); // 버퍼에 남은 개행문자 제거
				
				switch(input) {
				case 1:
					 insertUser(); break;
				case 2:
					 selectAll();  break;
				case 3:
					 selectName();  break;
				case 4:
					 selectUser(); break;
				case 5:
					 deleteUser();  break;
				case 6:
					/* updateName(); */  break;
				case 7:
					/* insertUser2(); */ break;
				
				case 8:
					/* multiInsertUser(); */ break;
				
				case 0 : System.out.println("\n[프로그램 종료]\n"); break;
				default: System.out.println("\n[메뉴 번호만 입력하세요]\n");
				}
				
				System.out.println("\n-------------------------------------\n");
				
			} catch (InputMismatchException e) {
				// Scanner를 이용한 입력 시 자료형이 잘못된 경우
				System.out.println("\n***잘못 입력 하셨습니다***\n");
				
				input = -1; // 잘못 입력해서 while문 멈추는걸 방지
				sc.nextLine(); // 입력 버퍼에 남아있는 잘못된 문자 제거
				
			} catch (Exception e) {
				// 발생되는 예외를 모두 해당 catch 구문으로 모아서 처리
				e.printStackTrace();
			}
			
		}while(input != 0);
		
	} // mainMenu() 종료

	
	
	

	

	

	

	/**
	 * 1. User 등록
	 */
	public void insertUser() throws Exception{
		
		System.out.println("\n=== 1. User 등록 ===\n");
		
		System.out.println("ID : ");
		String userId = sc.next();
		
		System.out.println("PW : ");
		String userPw = sc.next();
		
		System.out.println("Name : ");
		String userName = sc.next();
		
		// 입력받은 값 3개를 한번에 묶어서 전달할 수 있도록
		// User DTO 객체를 생성한 후 필드에 값을 세팅
		
		User user = new User();
		
		// setter 이용 3가지 값 세팅
		user.setUserId(userId);
		user.setUserPw(userPw);
		user.setUserName(userName);
		
		// 서비스 호출 후 결과 반환 받기(int, 삽입된 행의 개수) 반환 받기
		int result = service.insertUser(user);
		
		// 반환된 결과에 따라 출력할 대뇽 선택
		if(result > 0 ) {
			System.out.println("\n" + userId + " 사용자가 등록되었습니다.");
			
		} else {
			System.out.println("***** *****");
		}
	}

	
	
	
	/**
	 * 2. User 전체 조회(SELECT)
	 */
	public void selectAll() throws Exception {
		System.out.println("\n=== 2. User 전체 조회(SELECT) ===\n");
		
		// 서비스 호출(SELECT) 후 결과(List<User>) 반환
		List<User> userList = service.selectAll();
		
		// 조회 결과가 없을 경우 
		if(userList.isEmpty()) {
			System.out.println("\n*** 조회 결과가 없습니다 ***\n");
			return;
		}
		
		// 있을 경우 향상된 for문 이용해서 userList에 있는 User 객체 출력
		for(User user : userList) {
			System.out.println(user);
		}
		
		
	
	}
	

	/**
	 *  3. User 중 이름에 검색어가 포함된 회원 조회 (SELECT)
	 */
	public void selectName() throws Exception {
		System.out.println("\n=== 3. User 중 이름에 검색어가 포함된 회원 조회(SELECT) ===\n");
		
		System.out.println("이름을 입력하세요 : ");
		String input = sc.nextLine();
		
		
		// 서비스 호출(SELECT) 후 결과(List<User>) 반환
		List<User> userList = service.selectByName(input);
				
		// 조회 결과가 없을 경우 
		if(userList.isEmpty()) {
			System.out.println("\n*** 검색 결과가 없습니다 ***\n");
			return;
		}
				
		// 있을 경우 user 객체 출력(toString)
		for(User user : userList) {
			System.out.println(user);
		}
		
		
		
	}
	
	
	
	/**
	 * 4. USER_NO를 입력 받아 일치하는 User 조회(SELECT)
	 */
	public void selectUser() throws Exception{
		System.out.println("\n=== 4.USER_NO를 입력 받아 일치하는 User 조회(SELECT) ===\n");
		
		System.out.println("유저번호을 입력하세요 : ");
		int input = sc.nextInt();
		
		// 서비스 호출(SELECT) 후 결과(List<User>) 반환
		User user = service.selectByUserNo(input);
						
		// 조회 결과가 없을 경우 
		if(user == null) {
			System.out.println("\n*** USER_NO가 일치하는 회원이 없습니다 ***\n");
			return;
		}
						
		// 있을 경우 user 객체 출력(toString)
		
			System.out.println(user);
		
		
	}
	
	
	/**
	 * 5. USER_NO를 입력 받아 일치하는 User 삭제(DELETE)
	 */
	public void deleteUser() throws Exception {
		System.out.println("\n=== 5. USER_NO를 입력 받아 일치하는 User 삭제(DELETE) ===\n");
		
		System.out.println("유저번호을 입력하세요 : ");
		int input = sc.nextInt();
		
		// 서비스 호출(DELETE) 후 삭제된 행 개수(int) 반환
	    int result = service.deleteByUserNo(input);
								
		// 조회 결과가 없을 경우 
	    
	    if(result == 0) {
	    	System.out.println("\n*** 사용자 번호가 일치하는 User가 존재하지 않습니다 ***\n");
	 
	    } else {
	    	System.out.println("USER_NO" + input + "회원 삭제 성공");
	    }
		
								
		
		
	}
	
	
	
}
