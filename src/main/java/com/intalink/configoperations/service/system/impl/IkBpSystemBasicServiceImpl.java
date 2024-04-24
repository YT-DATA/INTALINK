package com.intalink.configoperations.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.intalink.configoperations.domain.dataSourceSystem.IkBpDataSourceSystemRelationBasic;
import com.intalink.configoperations.domain.system.IkBpSystemBasic;
import com.intalink.configoperations.domain.system.vo.IkBpSystemBasicVo;
import com.intalink.configoperations.domain.systemModelRelation.IkBpDataSourceDataModelRelationBasic;
import com.intalink.configoperations.mapper.dataSourceSystem.IkBpDataSourceSystemRelationBasicMapper;
import com.intalink.configoperations.mapper.system.IkBpSystemBasicMapper;
import com.intalink.configoperations.mapper.systemModelRelation.IkBpDataSourceDataModelRelationBasicMapper;
import com.intalink.configoperations.service.system.IkBpSystemBasicService;
import com.intalink.configoperations.web.domain.AjaxResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author whx
 */
@Service
public class IkBpSystemBasicServiceImpl implements IkBpSystemBasicService {

    @Autowired
    private IkBpSystemBasicMapper ikBpSystemBasicMapper;

    @Autowired
    private IkBpDataSourceSystemRelationBasicMapper ikBpDataSourceSystemRelationBasicMapper;

    @Autowired
    private IkBpDataSourceDataModelRelationBasicMapper ikBpDataSourceDataModelRelationBasicMapper;

    /**
     * 获取列表
     * @param ikBpSystemBasicVo
     * @return
     */
    @Override
    public List<IkBpSystemBasicVo> selectSystemBasicLists(IkBpSystemBasicVo ikBpSystemBasicVo) {
        return ikBpSystemBasicMapper.selectSystemBasicLists(ikBpSystemBasicVo);
    }

    /**
     * 根据参数Id获取详细信息
     * @param systemId
     * @return
     */
    @Override
    public IkBpSystemBasic selectSystemBasicById(Integer systemId) {
        return ikBpSystemBasicMapper.selectSystemBasicById(systemId);
    }

    /**
     * 根据参数Ids删除信息
     * @param systemIds
     * @return
     */
    @Override
    public int deleteSystemBasicByIds(Integer[] systemIds) {
        return ikBpSystemBasicMapper.deleteBysystemId(systemIds);
    }

    /**
     * 插入/修改数据
     * @param ikBpSystemBasicVo
     * @return
     */
    @Override
    public AjaxResult insertOrUpdate(IkBpSystemBasicVo ikBpSystemBasicVo) {
        String insertOrUpdate = "update";
        Integer systemId = 0;
        if (ikBpSystemBasicVo.getSystemId() == null) {
            // 新增
            AjaxResult ajaxResult = insertSystem(ikBpSystemBasicVo);
            if (!ajaxResult.get("code").equals(200)) {
                return ajaxResult;
            } else {
                insertOrUpdate = "insert";
            }
        } else {
            //修改
            systemId = ikBpSystemBasicVo.getSystemId();
            ikBpSystemBasicVo.setIsDel(0);
            IkBpSystemBasic ikBpSystemBasic = new IkBpSystemBasic();
            ikBpSystemBasic.setSystemId(systemId);
            ikBpSystemBasic.setSystemCode(ikBpSystemBasicVo.getSystemCode());
            ikBpSystemBasic.setSystemName(ikBpSystemBasicVo.getSystemName());
            ikBpSystemBasic.setSystemRemark(ikBpSystemBasicVo.getSystemRemark());
            ikBpSystemBasic.setCreatTime(ikBpSystemBasicVo.getCreatTime());
            ikBpSystemBasicMapper.updateById(ikBpSystemBasic);
        }
        if ("insert".equals(insertOrUpdate)) {
            QueryWrapper<IkBpSystemBasic> qw = new QueryWrapper<>();
            qw.eq("system_name", ikBpSystemBasicVo.getSystemName())
                    .eq("system_code", ikBpSystemBasicVo.getSystemCode())
                    .eq("system_remark", ikBpSystemBasicVo.getSystemRemark());
            IkBpSystemBasic ikBpDataSourceBasic1 = ikBpSystemBasicMapper.selectOne(qw);
            systemId = ikBpDataSourceBasic1.getSystemId();
        }

        bindDataSourceSystem(ikBpSystemBasicVo.getDataSourceId(), systemId);
        bindModelSystem(systemId, ikBpSystemBasicVo.getDataModelId());
        return AjaxResult.success();
    }

    @Override
    public Integer count() {
        QueryWrapper<IkBpSystemBasic> qw = new QueryWrapper<>();
        qw.eq("is_del", 0);
        return ikBpSystemBasicMapper.selectCount(qw);
    }

    public AjaxResult insertSystem(IkBpSystemBasicVo ikBpSystemBasicVo) {
        ikBpSystemBasicVo.setIsDel(0);
        QueryWrapper<IkBpSystemBasic> qw = new QueryWrapper<>();
        qw.eq("system_name", ikBpSystemBasicVo.getSystemName());
        IkBpSystemBasic ikBpSystemBasic1 = ikBpSystemBasicMapper.selectOne(qw);
        if (ikBpSystemBasic1 != null) {
            return new AjaxResult(300, "系统名称相同");
        } else {
            IkBpSystemBasic ikBpSystemBasic = new IkBpSystemBasic();
            Date date = new Date();
            ikBpSystemBasic.setSystemName(ikBpSystemBasicVo.getSystemName());
            ikBpSystemBasic.setSystemCode(ikBpSystemBasicVo.getSystemCode());
            ikBpSystemBasic.setSystemRemark(ikBpSystemBasicVo.getSystemRemark());
            ikBpSystemBasic.setCreatTime(date);
            ikBpSystemBasic.setIsDel(0);
            ikBpSystemBasicMapper.insert(ikBpSystemBasic);
            return AjaxResult.success();
        }
    }

    public AjaxResult bindDataSourceSystem(Integer dataSourceId, Integer systemId) {
        // 一个系统只能绑定一个数据源
        LambdaQueryWrapper<IkBpDataSourceSystemRelationBasic> qw = new LambdaQueryWrapper<>();
        qw.eq(IkBpDataSourceSystemRelationBasic::getSystemId, systemId);
        IkBpDataSourceSystemRelationBasic ikBpDataSourceSystemRelationBasicOne = ikBpDataSourceSystemRelationBasicMapper.selectOne(qw);

//        if (dataSourceId == null || dataSourceId == 0) {
//            if (ikBpDataSourceSystemRelationBasicOne != null) {
//                return AjaxResult.success(
//                        ikBpDataSourceSystemRelationBasicMapper.deleteById(ikBpDataSourceSystemRelationBasicOne.getDataSourceSystemRelationId()));
//            }
//            return ;
//        } else {
        IkBpDataSourceSystemRelationBasic ikBpDataSourceSystemRelationBasic = new IkBpDataSourceSystemRelationBasic();
        ikBpDataSourceSystemRelationBasic.setSystemId(systemId);
        ikBpDataSourceSystemRelationBasic.setDatasourceId(dataSourceId);
        if (ikBpDataSourceSystemRelationBasicOne == null || StringUtils.isBlank(String.valueOf(ikBpDataSourceSystemRelationBasicOne.getSystemId()))) {
            // 新增
//                return ikBpDataSourceSystemRelationBasicMapper.insert(ikBpDataSourceSystemRelationBasic);
            return AjaxResult.success(ikBpDataSourceSystemRelationBasicMapper.insert(ikBpDataSourceSystemRelationBasic));
        } else {
            //修改
            ikBpDataSourceSystemRelationBasic.setDataSourceSystemRelationId(ikBpDataSourceSystemRelationBasicOne.getDataSourceSystemRelationId());
            return AjaxResult.success(ikBpDataSourceSystemRelationBasicMapper.updateById(ikBpDataSourceSystemRelationBasic));
        }
//        }
    }

    public AjaxResult bindModelSystem(Integer systemId, Integer dataModeId) {
        // 一个系统只能绑定一个模型
        LambdaQueryWrapper<IkBpDataSourceDataModelRelationBasic> qw = new LambdaQueryWrapper<>();
        qw.eq(IkBpDataSourceDataModelRelationBasic::getSystemId, systemId);
        IkBpDataSourceDataModelRelationBasic ikBpDataSourceSystemRelationBasicOne = ikBpDataSourceDataModelRelationBasicMapper.selectOne(qw);
        IkBpDataSourceDataModelRelationBasic ikBpDataSourceDataModelRelationBasic = new IkBpDataSourceDataModelRelationBasic();
        ikBpDataSourceDataModelRelationBasic.setSystemId(systemId);
        ikBpDataSourceDataModelRelationBasic.setDataModelId(dataModeId);
        if (ikBpDataSourceSystemRelationBasicOne == null || StringUtils.isBlank(String.valueOf(ikBpDataSourceSystemRelationBasicOne.getSystemId()))) {
            // 新增
            return AjaxResult.success(ikBpDataSourceDataModelRelationBasicMapper.insert(ikBpDataSourceDataModelRelationBasic));
        } else {
            //修改
            ikBpDataSourceDataModelRelationBasic.setDataSourceDataModelRelationId(ikBpDataSourceSystemRelationBasicOne.getDataSourceDataModelRelationId());
            return AjaxResult.success(ikBpDataSourceDataModelRelationBasicMapper.updateById(ikBpDataSourceDataModelRelationBasic));
        }
    }
}




