package com.intalink.configoperations.mapper.dataTableRelationBasic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.intalink.configoperations.domain.dataTableRelationBasic.EigenvalueSuccessLinkEntity;
import com.intalink.configoperations.domain.dataTableRelationBasic.IkBpDataTableRelationBasic;

import java.util.List;

/**
* @author whx
 * 关联关系基础mapper
*/
public interface IkBpDataTableRelationBasicMapper extends BaseMapper<IkBpDataTableRelationBasic> {
    /**
     * 获取全部关联关系
     * @return
     */
    List<IkBpDataTableRelationBasic> selectAll();

    /**
     * 新增成功池和特征值关联关系
     * @param EigenvalueSuccessLinkEntity
     * @return
     */
    int insertEigenvalueSuccessLink(EigenvalueSuccessLinkEntity EigenvalueSuccessLinkEntity);


    /**
     * 修改成功池和特征值关联关系
     * @param EigenvalueSuccessLinkEntity
     * @return
     */
    int updateEigenvalueSuccessLink(EigenvalueSuccessLinkEntity EigenvalueSuccessLinkEntity);


    /**
     * 查询成功池和特征值关联关系
     * @param successKey 成功池key
     * @return
     */
    EigenvalueSuccessLinkEntity searchEigenvalueSuccessLink(String successKey);


}




