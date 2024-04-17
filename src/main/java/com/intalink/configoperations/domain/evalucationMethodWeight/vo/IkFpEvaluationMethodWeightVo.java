package com.intalink.configoperations.domain.evalucationMethodWeight.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 评分权重
 * @TableName ik_fp_evaluation_method_weight
 */
@TableName(value ="ik_fp_evaluation_method_weight")
@Data
public class IkFpEvaluationMethodWeightVo implements Serializable {
    /**
     * 主键
     */
    private Integer scoringSchemeId;

    /**
     * 方案名称
     */
    private String scoringSchemeName;

    /**
     * 评分方法ID
     */
    private Integer evaluationMethodId;

    /**
     * 评分方法名称
     */
    private String evaluationMethodName;

    /**
     * 权重系数
     */
    private Double weightCoefficient;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}