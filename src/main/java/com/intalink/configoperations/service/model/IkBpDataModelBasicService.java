package com.intalink.configoperations.service.model;

import com.intalink.configoperations.domain.model.IkBpDataModelBasic;
import com.intalink.configoperations.domain.model.vo.IkBpDataModelBasicVo;

import java.util.List;
import java.util.Map;

/**
* @author whx
*/
public interface IkBpDataModelBasicService {

    List<IkBpDataModelBasicVo> selectIkBpDataTableBasicLists(IkBpDataModelBasicVo ikBpDataModelBasicVo);

    List<IkBpDataModelBasicVo> selectDataModelById(Integer[] dataModelIds);

    void deleteDataTableBasicByIds(Integer[] dataModelIds);

    int insertOrUpdate(IkBpDataModelBasic ikBpDataModelBasic);

    Map<String, Object> modelImport(List<List<String>> result);

    List<IkBpDataModelBasic> selectAll();

    Integer count();
}
