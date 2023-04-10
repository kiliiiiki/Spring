package com.yedam.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //기본생성자 (get/set/toString ..)
@AllArgsConstructor //매개값이 있는 생성자
@NoArgsConstructor //매개값이 없는 생성자
public class SampleVO {

	private int mno;
	private String firstName;
	private String lastName;
	
	
}
