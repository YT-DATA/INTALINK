package com.intalink.configoperations.domain.dataTable.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.intalink.configoperations.annotation.Excel;
import com.intalink.configoperations.web.domain.BaseEntity;
import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * 数据表信息
 * @TableName ik_bp_data_table_basic
 */
@TableName(value ="ik_bp_data_table_basic")
@Data
public class IkBpDataTableBasicVo extends BaseEntity {
    /**
     * 主键
     */
    @TableId
    private Integer dataTableId;

    /**
     * 数据模型Id
     */
    private Integer dataModelId;

    @NotBlank(message = "数据模型名称不能为空")
    private String dataModelName;

    /**
     * 数据表编码
     */
    @NotBlank(message = "数据表编码不能为空")
    @Excel(name = "数据表编码",sort = 1)
    private String dataTableCode;

    /**
     * 数据表名称
     */
    @Excel(name = "数据表名称",sort = 2)
    private String dataTableName;

    /**
     * 数据表描述
     */
    private String dataTableRemark;

    /**
     * 数据表描述
     */
    private String columnCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}