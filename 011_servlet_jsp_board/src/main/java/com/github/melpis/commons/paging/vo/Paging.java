package com.github.melpis.commons.paging.vo;

public abstract class Paging {
    // 데이터 정의


    private int page;
    private int countPerBlock = 5;
    private int countPerPage = 10;
    private int totalCount;
    private String searchColum;
    private String searchWord;

    // 생성자
    // 처리
    // getter, setter 정의

    public int getTotalpage() {
        int totalPage = this.totalCount / this.countPerPage;
        if (totalPage * this.countPerPage < this.totalCount) {
            totalPage++;
        }
        return totalPage;
    }

    public int getFirstPage() {
        int firstPage = 1;
        return firstPage;

    }

    public int getLastPage() {
        int lastPage = getTotalpage();
        return lastPage;
    }

    public int getNextPage() {
        int nextPage = 0;
        if (this.page < getTotalpage()) {
            nextPage = this.page + 1;
        }
        return nextPage;
    }

    public int getPrevPage() {
        int prevPage = 0;
        if (this.page != 1) {
            prevPage = this.page - 1;
        }
        return prevPage;

    }

    public int getNowBlock() {
        int nowBlock = 0;

        nowBlock = this.page / countPerBlock;

        if (nowBlock * countPerBlock < this.page) {
            nowBlock++;
        }
        return nowBlock;
    }

    public int getStartPage() {
        int startPage = 1 + (getNowBlock() - 1) * countPerBlock;
        return startPage;
    }

    public int getEndPage() {
        int endPage = getNowBlock() * countPerBlock;
        if (endPage > getTotalpage()) {
            endPage = getTotalpage();
        }
        return endPage;
    }


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

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return the countPerBlock
     */
    public int getCountPerBlock() {
        return countPerBlock;
    }

    /**
     * @param countPerBlock the countPerBlock to set
     */
    public void setCountPerBlock(int countPerBlock) {
        this.countPerBlock = countPerBlock;
    }

    /**
     * @return the searchColum
     */
    public String getSearchColum() {
        return searchColum;
    }

    /**
     * @param searchColum the searchColum to set
     */
    public void setSearchColum(String searchColum) {
        this.searchColum = searchColum;
    }

    /**
     * @return the searchWord
     */
    public String getSearchWord() {
        return searchWord;
    }

    /**
     * @param searchWord the searchWord to set
     */
    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    /**
     * @return the totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }

}
