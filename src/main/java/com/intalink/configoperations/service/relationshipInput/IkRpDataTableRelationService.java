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

    List<IkBpDataModelBasic> selectModel();

    List<IkBpDataTableBasic> selectTable(Integer dataModelId);

    List<IkBpDataColumnBasic> selectColumn(Integer dataTableId);

    List<IkRpDataTableRelationVo> selectListByData(IkRpDataTableRelationVo ikRpDataTableRelationVo);

    List<IkBpDataModelBasic> selectModelBind();

    List<IkBpDataTableBasic> selectTableBind(Integer dataModelId);

    List<IkBpDataColumnBasic> selectColumnBind(Integer dataTableId);

    List<IkBpDataModelBasic> selectRModelBind();

    List<IkBpDataTableBasic> selectRTableBind(Integer dataModelId);

    List<IkBpDataColumnBasic> selectRColumnBind(Integer dataTableId);

    int deleteRelationBasicByIds(Integer[] dataTableRelationIds);

    AjaxResult insertOrUpdate(List<IkRpDataTableRelationVo> ikRpDataTableRelationVos, Boolean insertOrUpdate);

    List<IkBpDataTableRelationBasic> selectTableRelation();
}
