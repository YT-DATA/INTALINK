package com.intalink.configoperations.mapper.dataTableRelationBasic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.intalink.configoperations.domain.dataTableRelationBasic.IkBpDataTableRelationBasic;

import java.util.List;

/**
* @author whx
*/
public interface IkBpDataTableRelationBasicMapper extends BaseMapper<IkBpDataTableRelationBasic> {

    List<IkBpDataTableRelationBasic> selectAll();

}




