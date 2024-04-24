package com.intalink.configoperations.mapper.evalucationMethodWeight;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.intalink.configoperations.domain.evalucationMethodWeight.IkFpEvaluationMethodWeight;
import com.intalink.configoperations.domain.evalucationMethodWeight.vo.IkFpEvaluationMethodWeightVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author whx
 * 评分权重mapper
*/
public interface IkFpEvaluationMethodWeightMapper extends BaseMapper<IkFpEvaluationMethodWeight> {
    /**
     * 根据评价方法id获取评分权重数据
     * @param evaluationMethodIds
     * @return
     */
    List<IkFpEvaluationMethodWeightVo> selectByEMIds(@Param("evaluationMethodIds") Integer[] evaluationMethodIds);

    /**
     * 根据评分方案id获取权重数据
     * @param scoringSchemeIds
     * @return
     */
    List<IkFpEvaluationMethodWeightVo> selectBySSIds(@Param("scoringSchemeId") Integer[] scoringSchemeIds);

    /**
     * 根据方案id删除评分权重
     * @param scoringSchemeIds
     */
    void deleteByIds(@Param("ids") Integer[] scoringSchemeIds);

    /**
     * 根据方案id获取评分权重
     * @param scoringSchemeId
     * @return
     */
    List<IkFpEvaluationMethodWeightVo> selectScoringSchemeById(Integer scoringSchemeId);
}




