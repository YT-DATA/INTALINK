package com.intalink.configoperations.mapper.model;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.intalink.configoperations.domain.model.IkBpDataModelBasic;
import com.intalink.configoperations.domain.model.vo.IkBpDataModelBasicVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author whx
* @createDate 2024-03-18 18:09:15
*/
public interface IkBpDataModelBasicMapper extends BaseMapper<IkBpDataModelBasic> {

    List<IkBpDataModelBasicVo> selectDataSourceList(IkBpDataModelBasicVo ikBpDataModelBasicVo);

    List<IkBpDataModelBasicVo> selectDataModelById(@Param("dataModelIds") Integer[] dataModelIds);

    void deleteByDataTableId(@Param("dataModelIds") Integer[] dataModelIds);

    void saveAll(@Param("list") List<IkBpDataModelBasic> list);

    IkBpDataModelBasic selectOneByModelName(String dataModelName);

    List<IkBpDataModelBasic> selectAll();

    void saveModel(IkBpDataModelBasic ikBpDataModelBasic);

    List<IkBpDataModelBasic> selectModelBind();

    List<IkBpDataModelBasic> selectRModelBind();
}




