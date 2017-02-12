package com.github.melpis.comment.vo;

import com.github.melpis.commons.paging.vo.Paging;
import com.github.melpis.member.vo.MemberVO;

import java.util.Date;

public class CommentVO extends Paging {

    private int seq;
    private String content;
    private Date registDate;
    private MemberVO member;

    public CommentVO() {
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


}
