package com.intalink.configoperations.domain.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.intalink.configoperations.annotation.Excel;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 数据模型表
 * @TableName ik_bp_data_model_basic
 */
@TableName(value ="ik_bp_data_model_basic")
@Data
public class IkBpDataModelBasicVo implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.INPUT)
    private Integer dataModelId;

    /**
     * 数据模型名称
     */
    @NotBlank(message = "数据模型名称不能为空")
    @Excel(name = "数据模型名称",sort = 1)
    private String dataModelName;

    /**
     * 数据模型Code
     */
    @NotBlank(message = "数据模型编码不能为空")
    @Excel(name = "数据模型编码",sort = 2)
    private String dataModelCode;

    /**
     * 数据模型描述
     */
    @Excel(name = "数据模型描述",sort = 3)
    private String dataModelRemark;

    /**
     * 数据表总量
     */
    private String dataTableCount;

    /**
     * 数据表总量
     */
    private String dataTableCode;

    /**
     * 是否删除
     */
    private Integer isDel;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}