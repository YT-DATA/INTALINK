package com.intalink.configoperations.domain.systemModelRelation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName ik_bp_data_source_data_model_relation_basic
 */
@TableName(value ="ik_bp_data_source_data_model_relation_basic")
@Data
public class IkBpDataSourceDataModelRelationBasic implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.INPUT)
    private Integer dataSourceDataModelRelationId;

    /**
     * 系统Id
     */
    private Integer systemId;

    /**
     * 数据模型Id
     */
    private Integer dataModelId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}