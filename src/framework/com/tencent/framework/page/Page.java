package com.tencent.framework.page;

import java.util.List;

/**
 * 表示分页中的一页。
 *
 * @author Zhang Kaitao
 */
public class Page<E> {
    
    private List<E> items;//当前页包含的记录列表
    private int startIndex;//当前页开始记录(起始为0)
    private long total;
    private int pageSize;
    private int page;

    public static int getStartOfPage(int pageNo, int pageSize) {
        return (pageNo-1) * pageSize;
    }

    

    public Page(int startIndex, long totalcount, int pagesize, List<E> list) {
        this.items = list;
        this.startIndex = startIndex;
        this.total = totalcount;
        this.pageSize = pagesize;
        
    }

    public Page() {
    }
    
    
 
}
