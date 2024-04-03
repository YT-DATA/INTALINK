package com.intalink.configoperations.domain.pdm;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class OTable<C extends OColumn> implements Serializable {
    private String pdmModelId;
    private String businessKey;
    private String tableId;
    private String tableCode;
    private String tableName;
    private String packageId;
    private String packageBusinessKey;
    private String remark;
    private List<String> pkList;
    private List<String> parentTableCode;
    private List<String> childrenTableCodes;
    private List<C> cColumens;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OTable<?> oTable = (OTable<?>) o;
        return tableId.equals(oTable.tableId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(tableId);
    }


    public OTable() {
    }

    public List<String> getParentTableCode() {
        return this.parentTableCode;
    }

    public void setParentTableCode(List<String> parentTableCode) {
        this.parentTableCode = parentTableCode;
    }

    public List<String> getChildrenTableCodes() {
        return this.childrenTableCodes;
    }

    public void setChildrenTableCodes(List<String> childrenTableCodes) {
        this.childrenTableCodes = childrenTableCodes;
    }

    public List<String> getPkList() {
        return this.pkList;
    }

    public void setPkList(List<String> pkList) {
        this.pkList = pkList;
    }

    public List<C> getcColumens() {
        return this.cColumens;
    }

    public void setcColumens(List<C> cColumens) {
        this.cColumens = cColumens;
    }

    public String getTableCode() {
        return this.tableCode;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode;
    }

    public String getPdmModelId() {
        return this.pdmModelId;
    }

    public void setPdmModelId(String pdmModelId) {
        this.pdmModelId = pdmModelId;
    }

    public String getBusinessKey() {
        return this.businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getTableId() {
        return this.tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPackageId() {
        return this.packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageBusinessKey() {
        return this.packageBusinessKey;
    }

    public void setPackageBusinessKey(String packageBusinessKey) {
        this.packageBusinessKey = packageBusinessKey;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

