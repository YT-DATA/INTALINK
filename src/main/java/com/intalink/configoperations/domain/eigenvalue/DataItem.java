package com.intalink.configoperations.domain.eigenvalue;

import lombok.Data;

@Data
public class DataItem {
    // 数据源-数据表-数据项
    private String dataItem;

    // 数据项类型
    private String dataType;

    // 数据项类型长度
    private String dataTypeLength;

    // 数据长度
    private String dataLength;

    // 数据表
    private String dataTable;
}