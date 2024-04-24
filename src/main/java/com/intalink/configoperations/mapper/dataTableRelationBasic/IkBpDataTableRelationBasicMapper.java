package com.intalink.configoperations.mapper.dataTableRelationBasic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.intalink.configoperations.domain.dataTableRelationBasic.IkBpDataTableRelationBasic;

import java.util.List;

/**
* @author whx
 * 关联关系基础mapper
*/
public interface IkBpDataTableRelationBasicMapper extends BaseMapper<IkBpDataTableRelationBasic> {
    /**
     * 获取全部关联关系
     * @return
     */
    List<IkBpDataTableRelationBasic> selectAll();

}




