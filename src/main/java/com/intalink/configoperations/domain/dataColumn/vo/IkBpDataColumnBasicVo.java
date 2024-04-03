package com.intalink.configoperations.domain.dataColumn.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.intalink.configoperations.annotation.Excel;
import com.intalink.configoperations.web.domain.BaseEntity;
import lombok.Data;

/**
 * 数据列基础信息
 * @TableName ik_bp_data_column_basic
 */
@TableName(value ="ik_bp_data_column_basic")
@Data
public class IkBpDataColumnBasicVo extends BaseEntity {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer dataColumnId;

    /**
     * 数据项编码
     */
    @Excel(name = "数据项编码",sort = 2)
    private String dataColumnCode;

    /**
     * 数据项名称
     */
    @Excel(name = "数据项名称",sort = 1)
    private String dataColumnName;

    /**
     * 数据项描述
     */
    private String dataColumnRemark;

    /**
     * 数据表Id
     */
    private Integer dataTableId;

    /**
     * 数据表名称
     */
    private String dataTableName;

    /**
     * 数据表代码
     */
    private String dataTableCode;

    /**
     * 数据模型Id
     */
    private Integer dataModelId;

    /**
     * 数据模型Id
     */
    private String dataModelName;

    /**
     * 是否删除
     */
    private Integer isDel;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}