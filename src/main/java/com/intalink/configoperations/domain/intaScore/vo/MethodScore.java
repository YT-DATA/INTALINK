package com.intalink.configoperations.domain.intaScore.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MethodScore {

    /**
     * 评分方法Id
     */
    public Integer evaluationMethodId;

    /**
     * 评分方法权重系数
     */
    public Double weightCoefficient;

    /**
     * 方法得分
     */
    public BigDecimal score;
}
