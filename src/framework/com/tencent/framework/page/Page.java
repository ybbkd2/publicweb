package com.tencent.framework.page;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 表示分页中的一页。
 *
 * @author Zhang Kaitao
 */
public class Page<E> {
    
    private Collection<E> items;//当前页包含的记录列表
    private int startIndex;//当前页开始记录(起始为0)
    private long total;   // 总记录数
    private int pageSize;  // 每页记录数
    private int curPage; // 当前页号
    

    /**
     * 取得页开始index
     * @param pageNo
     * @param pageSize
     * @return 
     */
    public static int getStartIdxOfPage(int pageNo, int pageSize) {
        return (pageNo-1) * pageSize;
    }

    

    public Page(int startIndex, long totalcount, int pagesize, Collection<E> list) {
        this.items = list;
        this.startIndex = startIndex;
        this.total = totalcount;
        this.pageSize = pagesize;
        this.curPage = (int) ( (startIndex%pagesize==0 && startIndex >0) ? startIndex/pagesize : startIndex/pagesize +1 );
    }
    
    public Page() {
        this.items = new ArrayList<E>();
    }

    /**
     * @return the items
     */
    public Collection<E> getItems() {
        return items;
    }



    /**
     * @return the startIndex
     */
    public int getStartIndex() {
        return startIndex;
    }



    /**
     * @return the total
     */
    public long getTotal() {
        return total;
    }



    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }


    /**
     * @return the curPage
     */
    public int getCurPage() {
        return curPage;
    }
    
    
    public int getTotalPage() {
        return (int) ( (this.total%this.pageSize==0 && this.pageSize >0) ? this.total/this.pageSize : this.total/this.pageSize+1 );
    }

 
}
