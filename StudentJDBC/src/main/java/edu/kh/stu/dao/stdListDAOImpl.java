package edu.kh.stu.dao;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static edu.kh.stu.common.JDBCTemplate.*;

import edu.kh.stu.dto.Student;


public class stdListDAOImpl implements stdListDAO{
	
	// JDBC 객체 참조변수 + Properties 참조 변수 선언
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
			
	private Properties prop;
			
	public stdListDAOImpl() {
		
		try {
			
			String filePath = stdListDAOImpl.class
					.getResource("/xml/sql.xml").getPath();
			
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
			
			
			
		} catch (Exception e) {
			System.out.println("sql.xml 로드 중 예외발생");
			e.printStackTrace();
		
		}
		

	}
	
	
	
	@Override
	public List<Student> stdListFullView(Connection conn) throws Exception {
		
		List<Student> stdList = new ArrayList<Student>();
		
		try {
			String sql = prop.getProperty("stdListFullView");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				Student std = Student.builder().
						stdNo(rs.getInt("STD_NO"))
						.stdName(rs.getString("STD_NAME"))
						.stdAge(rs.getInt("STD_AGE"))
						.stdGender(rs.getString("STD_GENDER"))
						.stdScore(rs.getString("STD_SCORE"))
						.build();
				
				stdList.add(std);
				
				
			}
		} finally {
			
			close(rs);
			close(stmt);
		}
		
	
		return stdList;
	}

}
