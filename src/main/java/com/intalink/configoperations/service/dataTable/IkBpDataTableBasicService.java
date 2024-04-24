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
    /**
     * 查询列表
     * @param ikBpDataTableBasicVo
     * @return
     */
    List<IkBpDataTableBasicVo> selectIkBpDataTableBasicLists(IkBpDataTableBasicVo ikBpDataTableBasicVo);

    /**
     * 根据参数Ids删除信息
     * @param dataTableIds
     */
    void deleteDataTableBasicByIds(Integer[] dataTableIds);

    /**
     * 插入/修改数据
     * @param ikBpDataTableBasic
     * @return
     */
    AjaxResult insertOrUpdate(IkBpDataTableBasicVo ikBpDataTableBasic);

    /**
     * 根据模型Id获取对应的数据表
     * @param dataModelId
     * @return
     */
    List<IkBpDataTableBasic> selectDataTableBasicByModelId(Integer dataModelId);

    /**
     * 导入数据表信息数据
     * @param result
     * @param dataModelId
     * @return
     */
    Map<String, Object> tableImport(List<List<String>> result,Integer dataModelId);

    /**
     * 根据模型Id获取对应的数据表
     * @return
     */
    Integer count();
}
