package edu.kh.stu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Student {
	private int stdNo; 			//std 번호
	private String stdName;		//std 이름	
	private int stdAge;			//std 나이
	private String stdGender;	//std 성별
	private String  stdScore; 	//std 성적
	
}
