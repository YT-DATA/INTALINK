package com.intalink.configoperations.domain.dataRelationShip.vo;

import java.util.List;

public class DataTable {
    /**
     * 数据源Key
     */
    private String dataSource;
    /**
     * 数据项列表
     */
    private List<DataItem> dataItem;

    // getters and setters
    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public List<DataItem> getDataItem() {
        return dataItem;
    }

    public void setDataItem(List<DataItem> dataItem) {
        this.dataItem = dataItem;
    }
}

