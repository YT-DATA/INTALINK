package com.intalink.configoperations.mapper.evaluationMethod;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.intalink.configoperations.domain.evaluationMethod.IkBpEvaluationMethodBasic;
import com.intalink.configoperations.domain.evaluationMethod.vo.IkBpEvaluationMethodBasicVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author whx
*/
public interface IkBpEvaluationMethodBasicMapper extends BaseMapper<IkBpEvaluationMethodBasic> {

    List<IkBpEvaluationMethodBasicVo> selectByEvaluationMethodName(String evaluationMethodName);

    List<IkBpEvaluationMethodBasic> selectAllByIds(@Param("evaluationMethodIds") Integer[] evaluationMethodIds);

    void deleteByIds(@Param("evaluationMethodIds") Integer[] evaluationMethodIds);

    List<IkBpEvaluationMethodBasicVo> selectAll();
}




