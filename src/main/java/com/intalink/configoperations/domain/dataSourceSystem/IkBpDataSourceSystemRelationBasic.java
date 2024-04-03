package com.intalink.configoperations.domain.dataSourceSystem;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * 系统数据源关联表
 * @TableName ik_bp_data_source_system_relation_basic
 */
@TableName(value ="ik_bp_data_source_system_relation_basic")
@Data
public class IkBpDataSourceSystemRelationBasic implements Serializable {
    /**
     * 主键Id
     */
    @TableId(type = IdType.INPUT)
    private Integer dataSourceSystemRelationId;

    /**
     * 数据源Id
     */
    private Integer datasourceId;

    /**
     * 系统Id
     */
    private Integer systemId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}