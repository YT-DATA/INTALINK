package com.intalink.configoperations.service.dataColumn;

import com.intalink.configoperations.domain.dataColumn.IkBpDataColumnBasic;
import com.intalink.configoperations.domain.dataColumn.vo.IkBpDataColumnBasicVo;

import java.util.List;
import java.util.Map;

/**
 * @author whx
 * @createDate 2024-03-19 09:01:26
 * 数据项mapper
 */
public interface IkBpDataColumnBasicService {

    /**
     * 查询列表
     * @param ikBpDataColumnBasicVo
     * @return
     */
    List<IkBpDataColumnBasicVo> selectIkBpDataColumnBasicLists(IkBpDataColumnBasicVo ikBpDataColumnBasicVo);

    /**
     * 根据参数Id获取详细信息
     * @param dataColumnId
     * @return
     */
    IkBpDataColumnBasic selectDataColumnBasicById(Integer dataColumnId);

    /**
     * 根据参数Ids删除信息
     * @param dataColumnIds
     */
    void deleteDataColumnBasicByIds(Integer[] dataColumnIds);

    /**
     * 插入/修改数据
     * @param ikBpDataColumnBasic
     * @return
     */
    int insertOrUpdate(IkBpDataColumnBasicVo ikBpDataColumnBasic);

    /**
     * 根据表id获取数据项数据
     * @param dataTableId
     * @return
     */
    List<IkBpDataColumnBasic> selectDataColumnBasicByTableId(Integer dataTableId);

    /**
     * 导入数据项信息数据
     * @param result
     * @param dataModelId
     * @param dataTableId
     * @return
     */
    Map<String, Object> columnImport(List<List<String>> result, Integer dataModelId, Integer dataTableId);
}
