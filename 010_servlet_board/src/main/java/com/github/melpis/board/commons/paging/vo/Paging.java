package com.github.melpis.board.commons.paging.vo;

public abstract class Paging {
	// 데이터 정의
	private int page;
	private int countPerPage;
	// 생성자
	// 처리
	// getter, setter 정의
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getStartRowNum() {
		int startRowNum = 1 + (page - 1) * countPerPage;
		return startRowNum;
	}
	public int getEndRowNum() {
		int endRowNum = page * countPerPage;
		return endRowNum;
	}
	public int getCountPerPage() {
		return countPerPage;
	}
	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}
	
}
