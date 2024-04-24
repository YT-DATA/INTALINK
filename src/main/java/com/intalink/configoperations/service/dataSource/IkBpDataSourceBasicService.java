package com.intalink.configoperations.service.dataSource;

import com.intalink.configoperations.domain.dataSource.IkBpDataSourceBasic;
import com.intalink.configoperations.domain.dataSource.vo.IkBpDataSourceBasicVo;

import java.util.List;

/**
 * @author whx
 */
public interface IkBpDataSourceBasicService {
    /**
     * 查询列表
     * @param ikBpDataSourceBasic
     * @return
     */
    List<IkBpDataSourceBasic> selectIkBpDataSourceBasicLists(IkBpDataSourceBasicVo ikBpDataSourceBasic);

    /**
     * 根据参数Id获取详细信息
     * @param dataSourceId
     * @return
     */
    IkBpDataSourceBasic selectDataSourceBasicById(Integer dataSourceId);

    /**
     * 根据参数Ids删除信息
     * @param dataSourceIds
     */
    void deleteDataSourceBasicByIds(Integer[] dataSourceIds);

    /**
     * 插入/修改数据
     * @param ikBpDataSourceBasic
     * @return
     */
    int insertOrUpdate(IkBpDataSourceBasic ikBpDataSourceBasic);

    /**
     * 查询全部
     * @return
     */
    List<IkBpDataSourceBasic> selectAll();

    /**
     * 获取数据源数量
     * @return
     */
    Integer count();
}
