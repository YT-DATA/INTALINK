package com.intalink.configoperations.domain.pdm;

import lombok.Data;

import java.io.Serializable;

@Data
public class OColumn implements Serializable {
    private String businessKey;
    private Integer dataTableId;
    private String dataTableCode;
    private String tableBusinessKey;
    private String modelId;
    private String columnId;
    private String columnCode;
    private String columnName;
    private String isPk;
    private String isFk;
    private String isNotNull;
    private String dataType;
    private Integer dataLength;
    private Integer dataAccuracy;
    private String foreignKeyTable;
    private String foreignKeyValue;
    private String foreignKeyName;
    private String columnRemark;

    public OColumn() {
    }
}

