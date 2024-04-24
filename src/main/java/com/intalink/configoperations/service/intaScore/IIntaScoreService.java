package com.intalink.configoperations.service.intaScore;

import com.intalink.configoperations.domain.intaScore.vo.IntaLinkScoreVo;
import com.intalink.configoperations.domain.intaScore.vo.EvaluationMethodScoreVo;
import com.intalink.configoperations.enums.ScoreStrategy;

import java.util.List;

/**
 * intaLink 链路评分接口
 */
public interface IIntaScoreService {

    /**
     * 链路评分方法
     * (方案得分)
     */
    public List<IntaLinkScoreVo> getIntaLinkScore(List<EvaluationMethodScoreVo> evaluationMethodScoreVoList, Integer scoringSchemeId, ScoreStrategy scoreStrategy);


    public List<IntaLinkScoreVo> getIntaLinkSort(List<IntaLinkScoreVo> intaLinkScoreVoList, int sortNum);


}
