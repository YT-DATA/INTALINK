package com.intalink.configoperations.domain.intaScore.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 方法得分Vo
 */
@Data
public class EvaluationMethodScoreVo {

    /**
     * 链路Id
     */
    public String intaLinkId;

    /**
     * 评分方案Id
     */
    public Integer scoringSchemeId;

    /**
     * 方法得分详情
     */
    List<MethodScore> methodScoreList;


}
