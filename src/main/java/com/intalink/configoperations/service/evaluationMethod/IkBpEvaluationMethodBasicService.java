package com.intalink.configoperations.service.evaluationMethod;

import com.intalink.configoperations.domain.evaluationMethod.IkBpEvaluationMethodBasic;
import com.intalink.configoperations.domain.evaluationMethod.vo.IkBpEvaluationMethodBasicVo;
import com.intalink.configoperations.web.domain.AjaxResult;

import java.util.List;

/**
* @author whx
*/
public interface IkBpEvaluationMethodBasicService {

    List<IkBpEvaluationMethodBasicVo> selectEvaluationMethodBasicLists(IkBpEvaluationMethodBasicVo ikBpEvaluationMethodBasicVo);

    AjaxResult insertOrUpdate(IkBpEvaluationMethodBasic ikBpEvaluationMethodBasic, Boolean insertOrUpdate);

    AjaxResult deleteEvaluationMethodBasicByIds(Integer[] evaluationMethodIds);

    List<IkBpEvaluationMethodBasicVo> selectAll();

}
