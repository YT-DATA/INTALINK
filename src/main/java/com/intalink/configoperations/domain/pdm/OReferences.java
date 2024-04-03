package com.intalink.configoperations.domain.pdm;

import java.io.Serializable;

public class OReferences implements Serializable {
    private String parentTableId;
    private String childrenTableId;
    private String leftColId;
    private String rightColId;

    public OReferences() {
    }

    public String getParentTableId() {
        return this.parentTableId;
    }

    public void setParentTableId(String parentTableId) {
        this.parentTableId = parentTableId;
    }

    public String getChildrenTableId() {
        return this.childrenTableId;
    }

    public void setChildrenTableId(String childrenTableId) {
        this.childrenTableId = childrenTableId;
    }

    public String getLeftColId() {
        return this.leftColId;
    }

    public void setLeftColId(String leftColId) {
        this.leftColId = leftColId;
    }

    public String getRightColId() {
        return this.rightColId;
    }

    public void setRightColId(String rightColId) {
        this.rightColId = rightColId;
    }
}
