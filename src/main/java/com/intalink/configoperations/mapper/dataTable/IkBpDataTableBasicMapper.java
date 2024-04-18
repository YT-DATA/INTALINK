package com.intalink.configoperations.mapper.dataTable;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.intalink.configoperations.domain.dataTable.IkBpDataTableBasic;
import com.intalink.configoperations.domain.dataTable.vo.IkBpDataTableBasicVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author whx
* @createDate 2024-03-18 18:09:15
 * 数据表mapper
*/
public interface IkBpDataTableBasicMapper extends BaseMapper<IkBpDataTableBasic> {

    /**
     * 查询列表
     * @param ikBpDataTableBasicVo
     * @return
     */
    List<IkBpDataTableBasicVo> selectDataSourceList(IkBpDataTableBasicVo ikBpDataTableBasicVo);

    /**
     * 根据ids删除表
     * @param dataTableIds
     */
    void deleteByDataTableId(@Param("dataTableIds") Integer[] dataTableIds);

//    int saveTables(@Param("list") List<IkBpDataTableBasic> list);

    /**
     * 新增表数据
     * @param list
     * @return
     */
    int saveTables(IkBpDataTableBasic list);

    /**
     * 根据模型id删除不存在的表数据
     * @param modelId
     */
    void deleteNullTableByModelId(String modelId);

    /**
     * 根据模型id获取表相关数据
     * @param dataModelId
     * @return
     */
    List<IkBpDataTableBasic> selectDataTableBasicByModelId(Integer dataModelId);

    /**
     * 根据模型id和表编码获取表数据
     * @param dataModelId
     * @param dataTableCode
     * @return
     */
    IkBpDataTableBasic selectByModelIdAndTableCode(@Param("dataModelId") Integer dataModelId,@Param("dataTableCode")  String dataTableCode);

    /**
     * 根据表名和模型id获取表的数据
     * @param dataTableName
     * @param dataModelId
     * @return
     */
    IkBpDataTableBasic selectByTableName(@Param("dataTableName")String dataTableName,@Param("dataModelId") Integer dataModelId);

    /**
     * 根据模型id删除模型数据
     * @param dataModelIds
     */
    void deleteByDataModelId(@Param("dataModelIds") Integer[] dataModelIds);

    /**
     * 根据id获取模型数据
     * @param dataModelIds
     * @return
     */
    List<IkBpDataTableBasicVo> selectByModelIds(@Param("dataModelIds") Integer[] dataModelIds);

    /**
     * 根据模型id获取数据表
     * @param dataModelId
     * @return
     */
    List<IkBpDataTableBasic> selectTableBind(Integer dataModelId);

    /**
     * 根据模型id获取数据表
     * @param dataModelId
     * @return
     */
    List<IkBpDataTableBasic> selectRTableBind(Integer dataModelId);
}




