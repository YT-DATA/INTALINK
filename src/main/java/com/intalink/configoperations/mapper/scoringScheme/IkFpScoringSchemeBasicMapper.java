package com.intalink.configoperations.mapper.scoringScheme;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.intalink.configoperations.domain.scoringScheme.IkFpScoringSchemeBasic;
import com.intalink.configoperations.domain.scoringScheme.vo.IkFpScoringSchemeBasicVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author whx
 * 评分方案mapper
*/
public interface IkFpScoringSchemeBasicMapper extends BaseMapper<IkFpScoringSchemeBasic> {
    /**
     * 根据方案名称获取列表
     * @param scoringSchemeName
     * @return
     */
    List<IkFpScoringSchemeBasicVo> selectByScoringSchemeName(String scoringSchemeName);

    /**
     * 根据id获取全部数据
     * @param scoringSchemeIds
     * @return
     */
    List<IkFpScoringSchemeBasic> selectAllByIds(@Param("scoringSchemeIds") Integer[] scoringSchemeIds);

    /**
     * 根据方案id删除数据
     * @param scoringSchemeIds
     */
    void deleteBySSIds(@Param("scoringSchemeIds") Integer[] scoringSchemeIds);
}




