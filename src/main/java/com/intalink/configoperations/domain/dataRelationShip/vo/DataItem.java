package com.intalink.configoperations.domain.dataRelationShip.vo;

import lombok.Data;

@Data
public class DataItem {

    /**
     * 数据项
     * 结构：数据源Id-数据表Id-数据项Id
     */
    private String dataItem;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 数据类型
     */
    private String dataTypeLength;
    /**
     * 数据长度
     */
    private String dataLength;

    /**
     * 特征值的长度
     */
    private Integer dataNum;

    /**
     * 数据表Key
     */
    private String dataTable;

}

