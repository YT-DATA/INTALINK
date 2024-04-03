package com.intalink.configoperations.service.dataSource;

import com.intalink.configoperations.domain.dataSource.IkBpDataSourceBasic;
import com.intalink.configoperations.domain.dataSource.vo.IkBpDataSourceBasicVo;

import java.util.List;

/**
 * @author whx
 */
public interface IkBpDataSourceBasicService {

    List<IkBpDataSourceBasic> selectIkBpDataSourceBasicLists(IkBpDataSourceBasicVo ikBpDataSourceBasic);

    IkBpDataSourceBasic selectDataSourceBasicById(Integer dataSourceId);

    void deleteDataSourceBasicByIds(Integer[] dataSourceIds);

    int insertOrUpdate(IkBpDataSourceBasic ikBpDataSourceBasic);

    List<IkBpDataSourceBasic> selectAll();

    Integer count();
}
