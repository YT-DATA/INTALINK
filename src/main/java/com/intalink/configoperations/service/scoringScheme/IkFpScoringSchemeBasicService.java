package com.intalink.configoperations.service.scoringScheme;

import com.intalink.configoperations.domain.evalucationMethodWeight.IkFpEvaluationMethodWeight;
import com.intalink.configoperations.domain.evalucationMethodWeight.vo.IkFpEvaluationMethodWeightVo;
import com.intalink.configoperations.domain.scoringScheme.IkFpScoringSchemeBasic;
import com.intalink.configoperations.domain.scoringScheme.vo.IkFpScoringSchemeBasicVo;
import com.intalink.configoperations.web.domain.AjaxResult;

import java.util.List;

/**
 * @author whx
 */
public interface IkFpScoringSchemeBasicService {

    List<IkFpScoringSchemeBasicVo> selectScoringSchemeLists(IkFpScoringSchemeBasicVo ikFpScoringSchemeBasicVo);

    /**
     * 新增或修改（true新增，false修改）
     * @param ikFpScoringSchemeBasic
     * @param insertOrUpdate
     * @return
     */
    AjaxResult insertOrUpdate(IkFpScoringSchemeBasic ikFpScoringSchemeBasic, Boolean insertOrUpdate);

    /**
     * 根据参数Ids删除信息
     * @param scoringSchemeIds
     * @param sureOrNot
     * @return
     */
    AjaxResult deleteScoringSchemeBasicByIds(Integer[] scoringSchemeIds,Boolean sureOrNot);

    /**
     * 根据方案id获取评分权重
     * @param scoringSchemeId
     * @return
     */
    List<IkFpEvaluationMethodWeightVo> selectScoringSchemeById(Integer scoringSchemeId);

    /**
     * 新增方案所绑定的方法及权重
     * @param ikFpEvaluationMethodWeight
     * @param scoringSchemeId
     * @return
     */
    AjaxResult insert(List<IkFpEvaluationMethodWeight> ikFpEvaluationMethodWeight, Integer scoringSchemeId);
}
