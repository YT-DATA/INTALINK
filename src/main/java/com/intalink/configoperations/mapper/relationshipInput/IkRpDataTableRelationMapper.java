package com.intalink.configoperations.mapper.relationshipInput;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.intalink.configoperations.domain.relationshipInput.IkRpDataTableRelation;
import com.intalink.configoperations.domain.relationshipInput.vo.IkRpDataTableRelationVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author whx
 */
public interface IkRpDataTableRelationMapper extends BaseMapper<IkRpDataTableRelation> {

    List<IkRpDataTableRelationVo> selectListByData(IkRpDataTableRelationVo ikRpDataTableRelationVo);

    int deleteRelationBasicByIds(@Param("dataTableRelationIds") Integer[] dataTableRelationIds);

    void insertAll(@Param("list") List<IkRpDataTableRelation> newList);
}




