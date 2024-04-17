package com.intalink.configoperations.domain.dataTableRelationBasic;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 关联关系基础表
 * @TableName ik_bp_data_table_relation_basic
 */
@TableName(value ="ik_bp_data_table_relation_basic")
@Data
public class IkBpDataTableRelationBasic implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.INPUT)
    private Integer relationId;
    /**
     * 关联关系描述
     */
    @NotBlank(message = "关联关系描述不能为空")
    private String relationDescribe;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}