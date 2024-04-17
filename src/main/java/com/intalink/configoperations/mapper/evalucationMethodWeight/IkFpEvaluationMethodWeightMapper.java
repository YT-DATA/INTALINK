package com.intalink.configoperations.mapper.evalucationMethodWeight;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.intalink.configoperations.domain.evalucationMethodWeight.IkFpEvaluationMethodWeight;
import com.intalink.configoperations.domain.evalucationMethodWeight.vo.IkFpEvaluationMethodWeightVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author whx
*/
public interface IkFpEvaluationMethodWeightMapper extends BaseMapper<IkFpEvaluationMethodWeight> {

    List<IkFpEvaluationMethodWeightVo> selectByEMIds(@Param("evaluationMethodIds") Integer[] evaluationMethodIds);

    List<IkFpEvaluationMethodWeightVo> selectBySSIds(@Param("scoringSchemeId") Integer[] scoringSchemeIds);

    void deleteByIds(@Param("ids") Integer[] scoringSchemeIds);

    List<IkFpEvaluationMethodWeightVo> selectScoringSchemeById(Integer scoringSchemeId);
}




