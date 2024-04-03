package com.intalink.configoperations.mapper.dataSource;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.intalink.configoperations.domain.dataSource.IkBpDataSourceBasic;
import com.intalink.configoperations.domain.dataSource.vo.IkBpDataSourceBasicVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author whx
 */
public interface IkBpDataSourceBasicMapper extends BaseMapper<IkBpDataSourceBasic> {

    List<IkBpDataSourceBasic> selectDataSourceList(IkBpDataSourceBasicVo ikBpDataSourceBasic);

    IkBpDataSourceBasic selectDataSourceById(Integer dataSourceId);

    int deleteByDataSourceId(@Param("dataSourceIds") Integer[] dataSourceIds);

    List<IkBpDataSourceBasic> selectAll();
}




