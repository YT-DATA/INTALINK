package com.intalink.configoperations.mapper.dataTable;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.intalink.configoperations.domain.dataTable.IkBpDataTableBasic;
import com.intalink.configoperations.domain.dataTable.vo.IkBpDataTableBasicVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author whx
* @createDate 2024-03-18 18:09:15
*/
public interface IkBpDataTableBasicMapper extends BaseMapper<IkBpDataTableBasic> {

    List<IkBpDataTableBasicVo> selectDataSourceList(IkBpDataTableBasicVo ikBpDataTableBasicVo);


    void deleteByDataTableId(@Param("dataTableIds") Integer[] dataTableIds);

//    int saveTables(@Param("list") List<IkBpDataTableBasic> list);
    int saveTables(IkBpDataTableBasic list);

    void deleteNullTableByModelId(String modelId);

    List<IkBpDataTableBasic> selectDataTableBasicByModelId(Integer dataModelId);

    IkBpDataTableBasic selectByModelIdAndTableCode(@Param("dataModelId") Integer dataModelId,@Param("dataTableCode")  String dataTableCode);

    IkBpDataTableBasic selectByTableName(@Param("dataTableName")String dataTableName,@Param("dataModelId") Integer dataModelId);

    void deleteByDataModelId(@Param("dataModelIds") Integer[] dataModelIds);

    List<IkBpDataTableBasicVo> selectByModelIds(@Param("dataModelIds") Integer[] dataModelIds);
}




