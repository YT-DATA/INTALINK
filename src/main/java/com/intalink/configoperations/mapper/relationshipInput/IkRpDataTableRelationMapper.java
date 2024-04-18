package com.intalink.configoperations.mapper.relationshipInput;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.intalink.configoperations.domain.relationshipInput.IkRpDataTableRelation;
import com.intalink.configoperations.domain.relationshipInput.vo.IkRpDataTableRelationVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author whx
 * 数据项关联mapper
 */
public interface IkRpDataTableRelationMapper extends BaseMapper<IkRpDataTableRelation> {
    /**
     * 根据查询条件获取数据列表
     * @param ikRpDataTableRelationVo
     * @return
     */
    List<IkRpDataTableRelationVo> selectListByData(IkRpDataTableRelationVo ikRpDataTableRelationVo);

    /**
     * 根据参数Ids删除信息
     * @param dataTableRelationIds
     * @return
     */
    int deleteRelationBasicByIds(@Param("dataTableRelationIds") Integer[] dataTableRelationIds);

    /**
     * 新增全部
     * @param newList
     */
    void insertAll(@Param("list") List<IkRpDataTableRelation> newList);
}




