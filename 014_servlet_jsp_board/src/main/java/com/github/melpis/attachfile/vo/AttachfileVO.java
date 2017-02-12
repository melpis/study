package com.github.melpis.attachfile.vo;

import java.util.Date;

public class AttachfileVO {


    private int seq;
    private String fileName;
    private long fileSize;
    private String contentType;
    private String tempName;
    private Date registDate;
    private int downloadCount;


    public AttachfileVO() {

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
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }


    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    /**
     * @return the fileSize
     */
    public long getFileSize() {
        return fileSize;
    }


    /**
     * @param fileSize the fileSize to set
     */
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }


    /**
     * @return the contentType
     */
    public String getContentType() {
        return contentType;
    }


    /**
     * @param contentType the contentType to set
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }


    /**
     * @return the tempName
     */
    public String getTempName() {
        return tempName;
    }


    /**
     * @param tempName the tempName to set
     */
    public void setTempName(String tempName) {
        this.tempName = tempName;
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
     * @return the downloadCount
     */
    public int getDownloadCount() {
        return downloadCount;
    }


    /**
     * @param downloadCount the downloadCount to set
     */
    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }


}
