package com.github.melpis.board.vo;

import java.util.Date;

import com.github.melpis.commons.paging.vo.Paging;





public class BoardVO  extends Paging{
	//데이터 정의
	/**
	 * 게시물 번호
	 */
	private int seq;
	/**
	 * 게시물 제목
	 */
	private String title;
	/**
	 * 게시물 내용
	 */
	private String content;
	/**
	 * 게시물 등록날짜
	 */
	private Date registDate;
	/**
	 *
	 */
	private int readCount;
	//생성자
	public BoardVO(){}
	//처리

	//getter setter
	public int getSeq() {
		return seq;
	}
	/**
	 * seq 데이터 주입
	 * @param seq
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegistDate() {
		return registDate;
	}
	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}


}
