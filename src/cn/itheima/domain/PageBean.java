package cn.itheima.domain;

import java.util.List;

public class PageBean<T> {
    private int totalPageNo;
    private List<T> content;

    public int getTotalPageNo() {
        return totalPageNo;
    }

    public void setTotalPageNo(int totalPageNo) {
        this.totalPageNo = totalPageNo;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
