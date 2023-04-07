package com.yedam.board.domain;

import lombok.Data;

@Data
public class Criteria {
	
	private int pageNum;
	private int amount;
	private String type; // T, TW, TWC
	private String keyword;
	private String[] typeArr; // {T} or {TW} or {TWC}
	
	public Criteria() {
		this.pageNum = 1;
		this.amount = 10;
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
	}
	
}
