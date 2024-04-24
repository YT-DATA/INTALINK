package com.intalink.configoperations.domain.evalucationMethodWeight;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 评分权重
 * @TableName ik_fp_evaluation_method_weight
 */
@TableName(value ="ik_fp_evaluation_method_weight")
@Data
public class IkFpEvaluationMethodWeight implements Serializable {
    /**
     * 方案id
     */
    @TableId
    private Integer scoringSchemeId;

    /**
     * 评分方法ID
     */
    private Integer evaluationMethodId;

    /**
     * 权重系数
     */
    private Double weightCoefficient;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}