package com.intalink.configoperations.mapper.scoringScheme;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.intalink.configoperations.domain.scoringScheme.IkFpScoringSchemeBasic;
import com.intalink.configoperations.domain.scoringScheme.vo.IkFpScoringSchemeBasicVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author whx
*/
public interface IkFpScoringSchemeBasicMapper extends BaseMapper<IkFpScoringSchemeBasic> {

    List<IkFpScoringSchemeBasicVo> selectByScoringSchemeName(String scoringSchemeName);

    List<IkFpScoringSchemeBasic> selectAllByIds(@Param("scoringSchemeIds") Integer[] scoringSchemeIds);

    void deleteBySSIds(@Param("scoringSchemeIds") Integer[] scoringSchemeIds);
}




