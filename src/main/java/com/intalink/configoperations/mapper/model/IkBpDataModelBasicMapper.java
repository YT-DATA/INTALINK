package com.intalink.configoperations.mapper.model;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.intalink.configoperations.domain.model.IkBpDataModelBasic;
import com.intalink.configoperations.domain.model.vo.IkBpDataModelBasicVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author whx
* @createDate 2024-03-18 18:09:15
 * 模型mapper
*/
public interface IkBpDataModelBasicMapper extends BaseMapper<IkBpDataModelBasic> {
    /**
     * 获取模型数据
     * @param ikBpDataModelBasicVo
     * @return
     */
    List<IkBpDataModelBasicVo> selectataModelList(IkBpDataModelBasicVo ikBpDataModelBasicVo);

    /**
     * 根据模型id获取模型数据
     * @param dataModelIds
     * @return
     */
    List<IkBpDataModelBasicVo> selectDataModelById(@Param("dataModelIds") Integer[] dataModelIds);

    /**
     * 根据模型id删除对应模型
     * @param dataModelIds
     */
    void deleteByDataTableId(@Param("dataModelIds") Integer[] dataModelIds);

    /**
     * 保存
     * @param list
     */
    void saveAll(@Param("list") List<IkBpDataModelBasic> list);

    /**
     * 根据模型模型获取模型数据
     * @param dataModelName
     * @return
     */
    IkBpDataModelBasic selectOneByModelName(String dataModelName);

    /**
     * 查询全部模型
     * @return
     */
    List<IkBpDataModelBasic> selectAll();

    /**
     * 保存模型
     * @param ikBpDataModelBasic
     */
    void saveModel(IkBpDataModelBasic ikBpDataModelBasic);

    /**
     * 获取模型下拉列表
     * @return
     */
    List<IkBpDataModelBasic> selectModelBind();

    /**
     * 获取模型下拉列表
     * @return
     */
    List<IkBpDataModelBasic> selectRModelBind();
}




