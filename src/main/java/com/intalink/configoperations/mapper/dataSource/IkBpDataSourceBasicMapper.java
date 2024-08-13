package com.intalink.configoperations.mapper.dataSource;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.intalink.configoperations.domain.dataSource.IkBpDataSourceBasic;
import com.intalink.configoperations.domain.dataSource.vo.IkBpDataSourceBasicVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author whx
 * 数据源mapper
 */
public interface IkBpDataSourceBasicMapper extends BaseMapper<IkBpDataSourceBasic> {
    /**
     * 查询列表
     * @param ikBpDataSourceBasic
     * @return
     */
    List<IkBpDataSourceBasic> selectDataSourceList(IkBpDataSourceBasicVo ikBpDataSourceBasic);

    /**
     * 根据参数Id获取详细信息
     * @param dataSourceId
     * @return
     */
    IkBpDataSourceBasic selectDataSourceById(Integer dataSourceId);

    /**
     * 根据参数Ids删除信息
     * @param dataSourceIds
     * @return
     */
    int deleteByDataSourceId(@Param("dataSourceIds") Integer[] dataSourceIds);

    /**
     * 查询全部
     * @return
     */
    List<IkBpDataSourceBasic> selectAll();

    List<Map<String,Object>> selectOneData(@Param("dataSourceId") Integer dataSourceId);

    List<Map<String,Object>> selectTableIdAndColumnId(@Param("dataSourceId") Integer dataSourceId,@Param("tableName") String tableName,@Param("columnName") String columnName);

    List<Map<String,Object>> selectDataByColumnId(@Param("columnId") String columnId);

    List<Map<String, Object>> selectByTableId(@Param("tableId")String tableId);

    Map<String, Object> selectTableNumAndColumnNum(Integer dataSourceId);
}




