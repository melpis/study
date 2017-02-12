package com.github.melpis.board.vo;

import com.github.melpis.attachfile.vo.AttachfileVO;
import com.github.melpis.commons.paging.vo.Paging;
import com.github.melpis.member.vo.MemberVO;

import java.util.Date;
import java.util.List;

public class BoardVO extends Paging {
    private int seq;
    private String title;
    private String content;
    private Date registDate;
    private int readCount;
    private MemberVO member;
    private List<AttachfileVO> attachfileList;


    public BoardVO() {
        this.member = new MemberVO();
    }

    /**
     * @return the seq
     */
    public int getSeq() {
        return seq;
    }


    /**
     * @param seq the seq to set
     */
    public void setSeq(int seq) {
        this.seq = seq;
    }


    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }


    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }


    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }


    /**
     * @return the registDate
     */
    public Date getRegistDate() {
        return registDate;
    }


    /**
     * @param registDate the registDate to set
     */
    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }


    /**
     * @return the readCount
     */
    public int getReadCount() {
        return readCount;
    }


    /**
     * @param readCount the readCount to set
     */
    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }


    /**
     * @return the member
     */
    public MemberVO getMember() {
        return member;
    }


    /**
     * @param member the member to set
     */
    public void setMember(MemberVO member) {
        this.member = member;
    }


    /**
     * @return the attachfileList
     */
    public List<AttachfileVO> getAttachfileList() {
        return attachfileList;
    }


    /**
     * @param attachfileList the attachfileList to set
     */
    public void setAttachfileList(List<AttachfileVO> attachfileList) {
        this.attachfileList = attachfileList;
    }


}
