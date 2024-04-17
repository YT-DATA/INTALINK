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

    AjaxResult insertOrUpdate(IkFpScoringSchemeBasic ikFpScoringSchemeBasic, Boolean insertOrUpdate);

    AjaxResult deleteScoringSchemeBasicByIds(Integer[] scoringSchemeIds,Boolean sureOrNot);

    List<IkFpEvaluationMethodWeightVo> selectScoringSchemeById(Integer scoringSchemeId);

    AjaxResult insert(List<IkFpEvaluationMethodWeight> ikFpEvaluationMethodWeight, Integer scoringSchemeId);
}
