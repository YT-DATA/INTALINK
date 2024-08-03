package com.intalink.configoperations.domain.dataRelationShip.vo;

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

    // getters and setters
    public String getDataItem() {
        return dataItem;
    }

    public void setDataItem(String dataItem) {
        this.dataItem = dataItem;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataLength() {
        return dataLength;
    }

    public void setDataLength(String dataLength) {
        this.dataLength = dataLength;
    }

    public Integer getDataNum() {
        return dataNum;
    }

    public void setDataNum(Integer dataNum) {
        this.dataNum = dataNum;
    }

    public String getDataTable() {
        return dataTable;
    }

    public void setDataTable(String dataTable) {
        this.dataTable = dataTable;
    }
}

