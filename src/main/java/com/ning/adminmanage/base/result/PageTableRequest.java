package com.ning.adminmanage.base.result;

import lombok.Data;

import java.io.Serializable;
@Data
public class PageTableRequest implements Serializable {
    private  Integer page;
    private  Integer limit;
    private  Integer offset;//起始点
    //起始点的计算
    public void countOffset(){
        if(null== this.page||null== limit){
            this.offset=0;
            return;
        }
        this.offset = (this.page - 1) * this.limit;
    }
}
