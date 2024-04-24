package com.intalink.configoperations.mapper.evaluationMethod;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.intalink.configoperations.domain.evaluationMethod.IkBpEvaluationMethodBasic;
import com.intalink.configoperations.domain.evaluationMethod.vo.IkBpEvaluationMethodBasicVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author whx
 * 评价方法mapper
*/
public interface IkBpEvaluationMethodBasicMapper extends BaseMapper<IkBpEvaluationMethodBasic> {
    /**
     * 获取列表
     * @param evaluationMethodName
     * @return
     */
    List<IkBpEvaluationMethodBasicVo> selectByEvaluationMethodName(String evaluationMethodName);

    /**
     * 根据方法id获取方法
     * @param evaluationMethodIds
     * @return
     */
    List<IkBpEvaluationMethodBasic> selectAllByIds(@Param("evaluationMethodIds") Integer[] evaluationMethodIds);

    /**
     * 根据参数Ids删除信息
     * @param evaluationMethodIds
     */
    void deleteByIds(@Param("evaluationMethodIds") Integer[] evaluationMethodIds);

    /**
     * 查询全部
     * @return
     */
    List<IkBpEvaluationMethodBasicVo> selectAll();
}




