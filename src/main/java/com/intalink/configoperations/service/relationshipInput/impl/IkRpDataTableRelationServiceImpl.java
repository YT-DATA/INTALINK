package com.intalink.configoperations.service.relationshipInput.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.intalink.configoperations.web.domain.AjaxResult;
import com.intalink.configoperations.domain.dataColumn.IkBpDataColumnBasic;
import com.intalink.configoperations.domain.dataTable.IkBpDataTableBasic;
import com.intalink.configoperations.domain.dataTableRelationBasic.IkBpDataTableRelationBasic;
import com.intalink.configoperations.domain.model.IkBpDataModelBasic;
import com.intalink.configoperations.domain.relationshipInput.IkRpDataTableRelation;
import com.intalink.configoperations.domain.relationshipInput.vo.IkRpDataTableRelationVo;
import com.intalink.configoperations.mapper.dataColumn.IkBpDataColumnBasicMapper;
import com.intalink.configoperations.mapper.dataTable.IkBpDataTableBasicMapper;
import com.intalink.configoperations.mapper.dataTableRelationBasic.IkBpDataTableRelationBasicMapper;
import com.intalink.configoperations.mapper.model.IkBpDataModelBasicMapper;
import com.intalink.configoperations.mapper.relationshipInput.IkRpDataTableRelationMapper;
import com.intalink.configoperations.service.relationshipInput.IkRpDataTableRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author whx
 */
@Service
public class IkRpDataTableRelationServiceImpl implements IkRpDataTableRelationService {
    @Autowired
    private IkBpDataTableRelationBasicMapper ikBpDataTableRelationBasicMapper;
    @Autowired
    private IkBpDataModelBasicMapper ikBpDataModelBasicMapper;
    @Autowired
    private IkBpDataTableBasicMapper ikBpDataTableBasicMapper;
    @Autowired
    private IkBpDataColumnBasicMapper ikBpDataColumnBasicMapper;
    @Resource
    private IkRpDataTableRelationMapper ikRpDataTableRelationMapper;

    /**
     * 根据查询条件获取数据列表
     *
     * @param ikRpDataTableRelationVo
     * @return
     */
    @Override
    public List<IkRpDataTableRelationVo> selectListByData(IkRpDataTableRelationVo ikRpDataTableRelationVo) {
        return ikRpDataTableRelationMapper.selectListByData(ikRpDataTableRelationVo);
    }

    /**
     * 获取所有模型
     *
     * @return
     */
    @Override
    public List<IkBpDataModelBasic> selectModel() {
        List<IkBpDataModelBasic> ikBpDataModelBasics = ikBpDataModelBasicMapper.selectAll();
        return ikBpDataModelBasics;
    }

    /**
     * 根据模型id获取所有表
     *
     * @param dataModelId
     * @return
     */
    @Override
    public List<IkBpDataTableBasic> selectTable(Integer dataModelId) {
        List<IkBpDataTableBasic> ikBpDataTableBasics = ikBpDataTableBasicMapper.selectDataTableBasicByModelId(dataModelId);
        return ikBpDataTableBasics;
    }

    /**
     * 根据表id  获取所有数据项
     *
     * @param dataTableId
     * @return
     */
    @Override
    public List<IkBpDataColumnBasic> selectColumn(Integer dataTableId) {
        List<IkBpDataColumnBasic> ikBpDataColumnBasics = ikBpDataColumnBasicMapper.selectDataColumnBasicByTableId(dataTableId);
        return ikBpDataColumnBasics;
    }

    /**
     * 获取所有绑定关系模型
     *
     * @return
     */
    @Override
    public List<IkBpDataModelBasic> selectModelBind() {
        List<IkBpDataModelBasic> ikBpDataModelBasics = ikBpDataModelBasicMapper.selectModelBind();
        return ikBpDataModelBasics;
    }

    /**
     * 根据模型id获取所有绑定关系表
     *
     * @param dataModelId
     * @return
     */
    @Override
    public List<IkBpDataTableBasic> selectTableBind(Integer dataModelId) {
        List<IkBpDataTableBasic> ikBpDataTableBasics = ikBpDataTableBasicMapper.selectTableBind(dataModelId);
        return ikBpDataTableBasics;
    }

    /**
     * 根据表id  获取所有数据绑定关系项
     *
     * @param dataTableId
     * @return
     */
    @Override
    public List<IkBpDataColumnBasic> selectColumnBind(Integer dataTableId) {
        List<IkBpDataColumnBasic> ikBpDataColumnBasics = ikBpDataColumnBasicMapper.selectColumnBind(dataTableId);
        return ikBpDataColumnBasics;
    }

    /**
     * 获取关联模型绑定信息
     *
     * @return
     */
    @Override
    public List<IkBpDataModelBasic> selectRModelBind() {
        List<IkBpDataModelBasic> ikBpDataModelBasics = ikBpDataModelBasicMapper.selectRModelBind();
        return ikBpDataModelBasics;
    }

    /**
     * 获取关联数据表绑定信息
     *
     * @param dataModelId
     * @return
     */
    @Override
    public List<IkBpDataTableBasic> selectRTableBind(Integer dataModelId) {
        List<IkBpDataTableBasic> ikBpDataTableBasics = ikBpDataTableBasicMapper.selectRTableBind(dataModelId);
        return ikBpDataTableBasics;
    }

    /**
     * 获取关联数据项绑定信息
     *
     * @param dataTableId
     * @return
     */
    @Override
    public List<IkBpDataColumnBasic> selectRColumnBind(Integer dataTableId) {
        List<IkBpDataColumnBasic> ikBpDataColumnBasics = ikBpDataColumnBasicMapper.selectRColumnBind(dataTableId);
        return ikBpDataColumnBasics;
    }

    /**
     * 根据参数Ids删除信息
     *
     * @param dataTableRelationIds
     * @return
     */
    @Override
    public int deleteRelationBasicByIds(Integer[] dataTableRelationIds) {
        return ikRpDataTableRelationMapper.deleteRelationBasicByIds(dataTableRelationIds);
    }

    /**
     * 获取关联关系数据
     *
     * @return
     */
    @Override
    public List<IkBpDataTableRelationBasic> selectTableRelation() {
        return ikBpDataTableRelationBasicMapper.selectAll();
    }

    /**
     * 新增或修改（true新增，false修改）
     *
     * @param ikRpDataTableRelationVos
     * @param insertOrUpdate
     * @return
     */
    @Override
    public AjaxResult insertOrUpdate(List<IkRpDataTableRelationVo> ikRpDataTableRelationVos, Boolean insertOrUpdate) {
        if (insertOrUpdate) {
            //新增
            if (insert(ikRpDataTableRelationVos) > 0) {
                return AjaxResult.success();
            } else {
                return AjaxResult.error();
            }
        } else {
            if (updateById(ikRpDataTableRelationVos.get(0)) > 0) {
                return AjaxResult.success();
            } else {
                return AjaxResult.error("此数据项之间已存在关系，不可重复绑定");
            }
        }
    }

    @Transactional
    public int updateById(IkRpDataTableRelationVo ikRpDataTableRelationVo) {
        QueryWrapper<IkRpDataTableRelation> qw = new QueryWrapper<>();
        qw.eq("data_column_id", ikRpDataTableRelationVo.getDataColumnId()).eq("relation_data_column_id", ikRpDataTableRelationVo.getRelationDataColumnId());
        IkRpDataTableRelation ikRpDataTableRelation1 = ikRpDataTableRelationMapper.selectOne(qw);
        if (ikRpDataTableRelation1 != null) {
            return -1;
        }
        IkRpDataTableRelation ikRpDataTableRelation = new IkRpDataTableRelation();
        ikRpDataTableRelation.setDataTableRelationId(ikRpDataTableRelationVo.getDataTableRelationId());
        if (ikRpDataTableRelationVo.getDataColumnId() > ikRpDataTableRelationVo.getRelationDataColumnId()) {
            ikRpDataTableRelation.setDataColumnId(ikRpDataTableRelationVo.getRelationDataColumnId());
            ikRpDataTableRelation.setRelationDataColumnId(ikRpDataTableRelationVo.getDataColumnId());
        } else {
            ikRpDataTableRelation.setDataColumnId(ikRpDataTableRelationVo.getDataColumnId());
            ikRpDataTableRelation.setRelationDataColumnId(ikRpDataTableRelationVo.getRelationDataColumnId());
        }
        ikRpDataTableRelation.setRelationType(ikRpDataTableRelationVo.getRelationType());
        ikRpDataTableRelation.setRelationStr(ikRpDataTableRelationVo.getRelationStr());

        ikRpDataTableRelationMapper.updateById(ikRpDataTableRelation);
        return 1;
    }

    @Transactional
    public int insert(List<IkRpDataTableRelationVo> ikRpDataTableRelationVos) {
        List<IkRpDataTableRelation> newList = new ArrayList<>();
        for (IkRpDataTableRelationVo ikRpDataTableRelationVo : ikRpDataTableRelationVos) {
            /**
             * 验证数据的唯一性
             */
            IkRpDataTableRelation ikRpDataTableRelation = new IkRpDataTableRelation();
            if (ikRpDataTableRelationVo.getDataColumnId() > ikRpDataTableRelationVo.getRelationDataColumnId()) {
                ikRpDataTableRelation.setDataColumnId(ikRpDataTableRelationVo.getRelationDataColumnId());
                ikRpDataTableRelation.setRelationDataColumnId(ikRpDataTableRelationVo.getDataColumnId());
            } else {
                ikRpDataTableRelation.setDataColumnId(ikRpDataTableRelationVo.getDataColumnId());
                ikRpDataTableRelation.setRelationDataColumnId(ikRpDataTableRelationVo.getRelationDataColumnId());
            }
            ikRpDataTableRelation.setRelationType(ikRpDataTableRelationVo.getRelationType());
            ikRpDataTableRelation.setRelationStr(ikRpDataTableRelationVo.getRelationStr());
            Boolean theOne = theOne(ikRpDataTableRelation);
            if (theOne) {
                continue;
            }
            newList.add(ikRpDataTableRelation);
        }
        if (newList.size() > 0) ikRpDataTableRelationMapper.insertAll(newList);
        return 1;
    }

    public Boolean theOne(IkRpDataTableRelation ikRpDataTableRelation) {
        QueryWrapper<IkRpDataTableRelation> qw = new QueryWrapper<>();
        qw.eq("data_column_id", ikRpDataTableRelation.getDataColumnId()).eq("relation_data_column_id", ikRpDataTableRelation.getRelationDataColumnId());
        IkRpDataTableRelation ikRpDataTableRelationOne = ikRpDataTableRelationMapper.selectOne(qw);
        if (ikRpDataTableRelationOne != null && ikRpDataTableRelationOne.getDataTableRelationId() != null) {
            IkRpDataTableRelationVo ikRpDataTableRelationVo = new IkRpDataTableRelationVo();
            ikRpDataTableRelationVo.setDataTableRelationId(ikRpDataTableRelationOne.getDataTableRelationId());
            ikRpDataTableRelationVo.setDataColumnId(ikRpDataTableRelationOne.getDataColumnId());
            ikRpDataTableRelationVo.setRelationDataColumnId(ikRpDataTableRelationOne.getRelationDataColumnId());
            ikRpDataTableRelationVo.setRelationType(ikRpDataTableRelation.getRelationType());
            ikRpDataTableRelationVo.setRelationStr(ikRpDataTableRelation.getRelationStr());
            updateById(ikRpDataTableRelationVo);
            return true;
        } else {
            return false;
        }
    }
}




