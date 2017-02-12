package com.github.melpis.member.vo;

import java.util.Date;

public class MemberVO {

    private String userId;
    private String userPw;
    private String userPwConfirm;
    private String userName;
    private Date registDate;
    private Date lastVisitedDate;


    public MemberVO() {

    }


    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }


    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }


    /**
     * @return the userPw
     */
    public String getUserPw() {
        return userPw;
    }


    /**
     * @param userPw the userPw to set
     */
    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }


    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }


    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * @return the lastVisitedDate
     */
    public Date getLastVisitedDate() {
        return lastVisitedDate;
    }


    /**
     * @param lastVisitedDate the lastVisitedDate to set
     */
    public void setLastVisitedDate(Date lastVisitedDate) {
        this.lastVisitedDate = lastVisitedDate;
    }


    /**
     * @return the userPwConfirm
     */
    public String getUserPwConfirm() {
        return userPwConfirm;
    }


    /**
     * @param userPwConfirm the userPwConfirm to set
     */
    public void setUserPwConfirm(String userPwConfirm) {
        this.userPwConfirm = userPwConfirm;
    }


}
