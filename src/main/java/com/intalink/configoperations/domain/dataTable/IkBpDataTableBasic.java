package com.intalink.configoperations.domain.dataTable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 数据表信息
 * @TableName ik_bp_data_table_basic
 */
@TableName(value ="ik_bp_data_table_basic")
@Data
public class IkBpDataTableBasic implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.INPUT)
    private Integer dataTableId;

    /**
     * 数据模型Id
     */
    @NotNull(message = "数据模型不能为空")
    private Integer dataModelId;

    /**
     * 数据表编码
     */
    @NotBlank(message = "数据表编码不能为空")
    private String dataTableCode;

    /**
     * 数据表名称
     */
    private String dataTableName;

    /**
     * 数据表描述
     */
    private String dataTableRemark;
    /**
     * 是否删除
     */
    private Integer isDel;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}