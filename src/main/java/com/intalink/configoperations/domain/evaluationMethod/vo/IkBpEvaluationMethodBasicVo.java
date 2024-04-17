package com.intalink.configoperations.domain.evaluationMethod.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.intalink.configoperations.web.domain.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 评价方法表
 * @TableName ik_bp_evaluation_method_basic
 */
@TableName(value ="ik_bp_evaluation_method_basic")
@Data
public class IkBpEvaluationMethodBasicVo extends BaseEntity {
    /**
     * 主键
     */
    @TableId(type = IdType.INPUT)
    private Integer evaluationMethodId;

    /**
     * 评分方法
     */
    @NotBlank(message = "评分方法名称不能为空")
    private String evaluationMethodName;

    /**
     * 评分方法描述
     */
    private String evaluationMethodRemark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}