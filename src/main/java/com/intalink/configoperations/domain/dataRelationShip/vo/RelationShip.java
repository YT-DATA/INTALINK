package com.intalink.configoperations.domain.dataRelationShip.vo;

import lombok.Data;

@Data
public class RelationShip {


    /**
     * 主数据项
     */
    public String mainColumn;

    /**
     * 关联数据项
     */
    public String relatedColumn;


    /**
     * 关联表达式
     */
    public String relationShipTypeStr;


}
