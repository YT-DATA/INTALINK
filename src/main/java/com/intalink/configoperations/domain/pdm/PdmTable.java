package com.intalink.configoperations.domain.pdm;

import java.io.Serializable;

public class PdmTable extends OTable<OColumn> implements Serializable {
    private String parentCodes;
    private String childCode;
    private String parentIds;
    private String childIds;
    private Integer dataTableId;

    public PdmTable() {
    }

    public String getParentCodes() {
        return this.parentCodes;
    }

    public void setParentCodes(String parentCodes) {
        this.parentCodes = parentCodes;
    }

    public String getChildCode() {
        return this.childCode;
    }

    public void setChildCode(String childCode) {
        this.childCode = childCode;
    }

    public String getParentIds() {
        return this.parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getChildIds() {
        return this.childIds;
    }

    public void setChildIds(String childIds) {
        this.childIds = childIds;
    }
}

