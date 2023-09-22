package com.cfz.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PageVo {

    private Long total;
    private List rows;

    public PageVo(Long total, List rows) {
        super();    
        this.total = total;
        this.rows = rows;
    }

}
