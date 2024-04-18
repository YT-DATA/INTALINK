package com.intalink.configoperations.service.relationshipInput;

import com.intalink.configoperations.domain.dataColumn.IkBpDataColumnBasic;
import com.intalink.configoperations.domain.dataTable.IkBpDataTableBasic;
import com.intalink.configoperations.domain.dataTableRelationBasic.IkBpDataTableRelationBasic;
import com.intalink.configoperations.domain.model.IkBpDataModelBasic;
import com.intalink.configoperations.domain.relationshipInput.vo.IkRpDataTableRelationVo;
import com.intalink.configoperations.web.domain.AjaxResult;

import java.util.List;

/**
 * @author whx
 */
public interface IkRpDataTableRelationService {
    /**
     * 获取模型列表
     * @return
     */
    List<IkBpDataModelBasic> selectModel();

    /**
     * 根据模型获取表
     * @param dataModelId
     * @return
     */
    List<IkBpDataTableBasic> selectTable(Integer dataModelId);

    /**
     * 根据模型和表获取数据项
     * @param dataTableId
     * @return
     */
    List<IkBpDataColumnBasic> selectColumn(Integer dataTableId);

    /**
     * 查询列表
     * @param ikRpDataTableRelationVo
     * @return
     */
    List<IkRpDataTableRelationVo> selectListByData(IkRpDataTableRelationVo ikRpDataTableRelationVo);

    /**
     * 获取模型绑定信息
     * @return
     */
    List<IkBpDataModelBasic> selectModelBind();

    /**
     * 获取数据表绑定信息
     * @param dataModelId
     * @return
     */
    List<IkBpDataTableBasic> selectTableBind(Integer dataModelId);

    /**
     * 获取数据项绑定信息
     * @param dataTableId
     * @return
     */
    List<IkBpDataColumnBasic> selectColumnBind(Integer dataTableId);

    /**
     * 获取关联模型绑定信息
     * @return
     */
    List<IkBpDataModelBasic> selectRModelBind();

    /**
     * 获取关联数据表绑定信息
     * @param dataModelId
     * @return
     */
    List<IkBpDataTableBasic> selectRTableBind(Integer dataModelId);

    /**
     * 获取关联数据项绑定信息
     * @param dataTableId
     * @return
     */
    List<IkBpDataColumnBasic> selectRColumnBind(Integer dataTableId);

    /**
     * 根据参数Ids删除信息
     * @param dataTableRelationIds
     * @return
     */
    int deleteRelationBasicByIds(Integer[] dataTableRelationIds);

    /**
     * 新增或修改（true新增，false修改）
     * @param ikRpDataTableRelationVos
     * @param insertOrUpdate
     * @return
     */
    AjaxResult insertOrUpdate(List<IkRpDataTableRelationVo> ikRpDataTableRelationVos, Boolean insertOrUpdate);

    /**
     * 获取关联关系列表
     * @return
     */
    List<IkBpDataTableRelationBasic> selectTableRelation();
}
