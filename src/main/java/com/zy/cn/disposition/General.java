package com.zy.cn.disposition;

/*****
 * Controller返回的通用对象
 */
public class General {

    private Integer limit;
    private Integer page;
    private Integer total;
    private Object object;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public General(Integer limit, Integer page, Integer total, Object object) {
        this.limit = limit;
        this.page = page;
        this.total = total;
        this.object = object;
    }
}
