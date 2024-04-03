package com.intalink.configoperations.service.dataTable;

import com.intalink.configoperations.domain.dataTable.IkBpDataTableBasic;
import com.intalink.configoperations.domain.dataTable.vo.IkBpDataTableBasicVo;
import com.intalink.configoperations.web.domain.AjaxResult;

import java.util.List;
import java.util.Map;

/**
 * @author whx
 */
public interface IkBpDataTableBasicService {

    List<IkBpDataTableBasicVo> selectIkBpDataTableBasicLists(IkBpDataTableBasicVo ikBpDataTableBasicVo);


    void deleteDataTableBasicByIds(Integer[] dataTableIds);

    AjaxResult insertOrUpdate(IkBpDataTableBasicVo ikBpDataTableBasic);

    List<IkBpDataTableBasic> selectDataTableBasicByModelId(Integer dataModelId);

    Map<String, Object> tableImport(List<List<String>> result,Integer dataModelId);

    Integer count();
}
