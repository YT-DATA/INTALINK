package com.intalink.configoperations.service.model;

import com.intalink.configoperations.domain.model.IkBpDataModelBasic;
import com.intalink.configoperations.domain.model.vo.IkBpDataModelBasicVo;

import java.util.List;
import java.util.Map;

/**
* @author whx
*/
public interface IkBpDataModelBasicService {
    /**
     * 查询列表
     * @param ikBpDataModelBasicVo
     * @return
     */
    List<IkBpDataModelBasicVo> selectIkBpDataTableBasicLists(IkBpDataModelBasicVo ikBpDataModelBasicVo);

    /**
     * 根据模型Id获取数据表和项的信息
     * @param dataModelIds
     * @return
     */
    List<IkBpDataModelBasicVo> selectDataModelById(Integer[] dataModelIds);

    /**
     * 根据参数Ids删除信息
     * @param dataModelIds
     */
    void deleteDataTableBasicByIds(Integer[] dataModelIds);

    /**
     * 插入/修改数据
     * @param ikBpDataModelBasic
     * @return
     */
    int insertOrUpdate(IkBpDataModelBasic ikBpDataModelBasic);

    /**
     * 导入模型信息数据
     * @param result
     * @return
     */
    Map<String, Object> modelImport(List<List<String>> result);

    /**
     * 查询全部
     * @return
     */
    List<IkBpDataModelBasic> selectAll();

    /**
     * 根据参数Ids删除信息
     * @return
     */
    Integer count();
}
