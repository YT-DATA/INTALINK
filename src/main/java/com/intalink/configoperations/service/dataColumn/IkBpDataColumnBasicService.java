package com.intalink.configoperations.service.dataColumn;

import com.intalink.configoperations.domain.dataColumn.IkBpDataColumnBasic;
import com.intalink.configoperations.domain.dataColumn.vo.IkBpDataColumnBasicVo;

import java.util.List;
import java.util.Map;

/**
 * @author whx
 * @createDate 2024-03-19 09:01:26
 */
public interface IkBpDataColumnBasicService {

    List<IkBpDataColumnBasicVo> selectIkBpDataColumnBasicLists(IkBpDataColumnBasicVo ikBpDataColumnBasicVo);

    IkBpDataColumnBasic selectDataColumnBasicById(Integer dataColumnId);

    void deleteDataColumnBasicByIds(Integer[] dataColumnIds);

    int insertOrUpdate(IkBpDataColumnBasicVo ikBpDataColumnBasic);

    List<IkBpDataColumnBasic> selectDataColumnBasicByTableId(Integer dataTableId);

    Map<String, Object> columnImport(List<List<String>> result, Integer dataModelId, Integer dataTableId);
}
