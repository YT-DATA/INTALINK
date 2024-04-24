package com.intalink.configoperations.service.evaluationMethod;

import com.intalink.configoperations.domain.evaluationMethod.IkBpEvaluationMethodBasic;
import com.intalink.configoperations.domain.evaluationMethod.vo.IkBpEvaluationMethodBasicVo;
import com.intalink.configoperations.web.domain.AjaxResult;

import java.util.List;

/**
* @author whx
*/
public interface IkBpEvaluationMethodBasicService {
    /**
     * 获取列表
     * @param ikBpEvaluationMethodBasicVo
     * @return
     */
    List<IkBpEvaluationMethodBasicVo> selectEvaluationMethodBasicLists(IkBpEvaluationMethodBasicVo ikBpEvaluationMethodBasicVo);

    /**
     * 新增或修改（true新增，false修改）
     * @param ikBpEvaluationMethodBasic
     * @param insertOrUpdate
     * @return
     */
    AjaxResult insertOrUpdate(IkBpEvaluationMethodBasic ikBpEvaluationMethodBasic, Boolean insertOrUpdate);

    /**
     * 根据参数Ids删除信息
     * @param evaluationMethodIds
     * @return
     */
    AjaxResult deleteEvaluationMethodBasicByIds(Integer[] evaluationMethodIds);

    /**
     * 查询全部
     * @return
     */
    List<IkBpEvaluationMethodBasicVo> selectAll();

}
