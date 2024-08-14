package com.intalink.configoperations.domain.dataTableRelationBasic;


import lombok.Data;

@Data
public class EigenvalueSuccessLinkEntity {
    /**
     * 成功池的key
     */
    private String successKey;
    /**
     * 成功池对应的特征值的key
     */
    private String eigenvalueKey;


}
