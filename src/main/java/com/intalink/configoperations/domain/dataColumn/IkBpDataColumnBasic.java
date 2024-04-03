package com.intalink.configoperations.domain.dataColumn;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 数据列基础信息
 * @TableName ik_bp_data_column_basic
 */
@TableName(value ="ik_bp_data_column_basic")
@Data
public class IkBpDataColumnBasic implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer dataColumnId;

    /**
     * 数据项编码
     */
    @NotBlank(message = "数据项编码不能为空")
    private String dataColumnCode;

    /**
     * 数据项名称
     */
    @NotBlank(message = "数据项名称不能为空")
    private String dataColumnName;

    /**
     * 数据项描述
     */
    private String dataColumnRemark;

    /**
     * 数据表Id
     */
    @NotNull(message = "数据表不能为空")
    private Integer dataTableId;

    /**
     * 是否删除
     */
    private Integer isDel;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}