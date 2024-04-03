package com.intalink.configoperations.domain.pdm;

import java.io.Serializable;
import java.util.List;

public class OPackage<T extends OTable> implements Serializable {
    private String pdmModelId;
    private String businessKey;
    private String packageId;
    private String packageName;
    private String packageCode;
    private String parentPkgBusinessKey;
    private List<T> cTables;
    private List<OPackage> cPackages;
    private int level;
    private String remark;

    private List<String> tableIds;
    public List<String> getTableIds() {
        return tableIds;
    }
    public void setTableIds(List<String> tableIds) {
        this.tableIds = tableIds;
    }

    public OPackage() {
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getParentPkgBusinessKey() {
        return this.parentPkgBusinessKey;
    }

    public void setParentPkgBusinessKey(String parentPkgBusinessKey) {
        this.parentPkgBusinessKey = parentPkgBusinessKey;
    }

    public List<OPackage> getcPackages() {
        return this.cPackages;
    }

    public void setcPackages(List<OPackage> cPackages) {
        this.cPackages = cPackages;
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

    public String getPackageId() {
        return this.packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public List<T> getcTables() {
        return this.cTables;
    }

    public void setcTables(List<T> cTables) {
        this.cTables = cTables;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
