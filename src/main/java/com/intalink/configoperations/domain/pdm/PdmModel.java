package com.intalink.configoperations.domain.pdm;

import java.io.Serializable;
import java.util.List;

public class PdmModel<P extends OPackage, T extends OTable> implements Serializable {
    private String modelId;
    private String modelName;
    private String modelBusinessKey;
    private List<P> cPackages;
    private List<T> cTables;

    public PdmModel() {
    }

    public String getModelBusinessKey() {
        return this.modelBusinessKey;
    }

    public void setModelBusinessKey(String modelBusinessKey) {
        this.modelBusinessKey = modelBusinessKey;
    }

    public String getModelId() {
        return this.modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return this.modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public List<P> getcPackages() {
        return this.cPackages;
    }

    public void setcPackages(List<P> cPackages) {
        this.cPackages = cPackages;
    }

    public List<T> getcTables() {
        return this.cTables;
    }

    public void setcTables(List<T> cTables) {
        this.cTables = cTables;
    }
}

