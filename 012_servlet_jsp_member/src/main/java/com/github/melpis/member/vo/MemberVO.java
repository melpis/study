package com.github.melpis.member.vo;

import java.util.Date;

public class MemberVO {
    //데이터 정의
    private String userId;
    private String userPw;
    private String userName;
    private Date registDate;
    private Date lastVisitedDate;

    //생성자
    public MemberVO() {
    }

    //처리
    //getter and setter
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public Date getLastVisitedDate() {
        return lastVisitedDate;
    }

    public void setLastVisitedDate(Date lastVisitedDate) {
        this.lastVisitedDate = lastVisitedDate;
    }

}
