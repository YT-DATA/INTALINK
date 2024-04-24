package com.intalink.configoperations.service.model.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.intalink.configoperations.domain.dataTable.vo.IkBpDataTableBasicVo;
import com.intalink.configoperations.domain.model.IkBpDataModelBasic;
import com.intalink.configoperations.domain.model.vo.IkBpDataModelBasicVo;
import com.intalink.configoperations.enums.SystemOrTableTreatmentMethodEnum;
import com.intalink.configoperations.mapper.dataColumn.IkBpDataColumnBasicMapper;
import com.intalink.configoperations.mapper.dataTable.IkBpDataTableBasicMapper;
import com.intalink.configoperations.mapper.model.IkBpDataModelBasicMapper;
import com.intalink.configoperations.service.model.IkBpDataModelBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author whx
 */
@Service
public class IkBpDataModelBasicServiceImpl implements IkBpDataModelBasicService {

    @Autowired
    private IkBpDataModelBasicMapper ikBpDataModelBasicMapper;

    @Autowired
    private IkBpDataTableBasicMapper ikBpDataTableBasicMapper;

    @Autowired
    private IkBpDataColumnBasicMapper ikBpDataColumnBasicMapper;

    /**
     * 查询列表
     * @param ikBpDataModelBasicVo
     * @return
     */
    @Override
    public List<IkBpDataModelBasicVo> selectIkBpDataTableBasicLists(IkBpDataModelBasicVo ikBpDataModelBasicVo) {
        String dataModelName;
        if (ikBpDataModelBasicVo.getDataModelName() == null) {
            dataModelName = "%%";
        } else {
            dataModelName = "%" + ikBpDataModelBasicVo.getDataModelName() + "%";
        }
        ikBpDataModelBasicVo.setDataModelName(dataModelName);
        List<IkBpDataModelBasicVo> ikBpDataModelBasics = ikBpDataModelBasicMapper.selectataModelList(ikBpDataModelBasicVo);
        return ikBpDataModelBasics;
    }

    @Override
    public List<IkBpDataModelBasicVo> selectDataModelById(Integer[] dataModelIds) {
        return ikBpDataModelBasicMapper.selectDataModelById(dataModelIds);
    }

    /**
     * 根据参数Ids删除信息
     * @param dataModelIds
     */
    @Override
    public void deleteDataTableBasicByIds(Integer[] dataModelIds) {
        ikBpDataModelBasicMapper.deleteByDataTableId(dataModelIds);
        ikBpDataTableBasicMapper.deleteByDataModelId(dataModelIds);
        List<IkBpDataTableBasicVo> ikBpDataTableBasicVos = ikBpDataTableBasicMapper.selectByModelIds(dataModelIds);
        if (ikBpDataTableBasicVos.size()>0){
            Integer[] dataTableIds = new Integer[ikBpDataTableBasicVos.size()];
            List<Integer> ids = new ArrayList<>();
            for (IkBpDataTableBasicVo ikBpDataTableBasicVo : ikBpDataTableBasicVos) {
                ids.add(ikBpDataTableBasicVo.getDataTableId());
            }
            ids.toArray(dataTableIds);
            ikBpDataColumnBasicMapper.deleteByDataTableId(dataTableIds);
        }
    }

    /**
     * 插入/修改数据
     * @param ikBpDataModelBasic
     * @return
     */
    @Override
    public int insertOrUpdate(IkBpDataModelBasic ikBpDataModelBasic) {
        if (ikBpDataModelBasic.getDataModelId() == null) {
            // 新增
            ikBpDataModelBasic.setIsDel(0);
            return ikBpDataModelBasicMapper.insert(ikBpDataModelBasic);
        } else {
            //修改
            ikBpDataModelBasic.setIsDel(0);
            return ikBpDataModelBasicMapper.updateById(ikBpDataModelBasic);
        }
    }

    /**
     * 导入模型信息数据
     * @param result
     * @return
     */
    @Override
    public Map<String, Object> modelImport(List<List<String>> result) {
        Map<String, Object> resultMap = new HashMap<>();
        List<Map> existsSystems = new ArrayList<>();
        //找到已经存在的模型
        Map existsModel = null;
        Set<Map> modelSet = new HashSet<>();
        List<IkBpDataModelBasic> modelDetailList = new ArrayList<IkBpDataModelBasic>();
        for (int i = 1; i < result.size(); i++) {
            //1.拿到模型名称
            String modelName = result.get(i).get(0);
            String modelCode = null;
            Integer modelId = null;
            if (result.get(i).size() > 1) {
                //模型代码
                modelCode = result.get(i).get(1);
            }
            //2.模型存在,则加入重复数据中
            IkBpDataModelBasic ikBpDataModelBasic1 = ikBpDataModelBasicMapper.selectOneByModelName(modelName);
            if (ikBpDataModelBasic1 != null) {
                modelId = ikBpDataModelBasic1.getDataModelId();
                existsModel = new HashMap();
                existsModel.put("modelName", modelName);
                existsModel.put("modelCode", modelCode);
                modelSet.add(existsModel);
                continue;
            }
            IkBpDataModelBasic ikBpDataModelBasic = new IkBpDataModelBasic();
            ikBpDataModelBasic.setDataModelId(modelId);
            ikBpDataModelBasic.setDataModelName(modelName);
            ikBpDataModelBasic.setDataModelCode(modelCode);
            if (result.get(i).size() > 2) {
                //系统描述
                String modelRemark = result.get(i).get(2);
                ikBpDataModelBasic.setDataModelRemark(modelRemark);
            }
            modelDetailList.add(ikBpDataModelBasic);
        }
        //将数据项信息分批量的插入到数据库中
        int size = modelDetailList.size();
        if (size > 0) {
            int pointsDataLimit = 100;//限制条数
            if (pointsDataLimit < size) {  //每一百条数据插入一次
                int part = size / pointsDataLimit;//分批数
                for (int i = 0; i < part; i++) {
                    List<IkBpDataModelBasic> listPage = modelDetailList.subList(0, pointsDataLimit);
                    insertOrUpdateList(listPage);
                    //剔除
                    modelDetailList.subList(0, pointsDataLimit).clear();
                }
                if (modelDetailList.size() > 0) {
                    insertOrUpdateList(modelDetailList);
                }
            } else {
                //插入数据
                insertOrUpdateList(modelDetailList);
            }
        }
        modelSet.forEach(model -> {
            existsSystems.add(model);
        });
        if (existsSystems.size() > 0) {
            resultMap.put("existsSystems", existsSystems);
            resultMap.put("systemOrTableTreatmentMethods", SystemOrTableTreatmentMethodEnum.getList());
            //return resultMap;
        }
        return resultMap;
    }

    /**
     * 查询全部
     * @return
     */
    @Override
    public List<IkBpDataModelBasic> selectAll() {
        return ikBpDataModelBasicMapper.selectAll();
    }

    /**
     * 根据参数Ids删除信息
     * @return
     */
    @Override
    public Integer count() {
        QueryWrapper<IkBpDataModelBasic> qw = new QueryWrapper<>();
        qw.eq("is_del",0);
        return ikBpDataModelBasicMapper.selectCount(qw);
    }

    /**
     * 新增或删除的公共方法
     * @param ikBpDataModelBasics
     * @return
     */
    public int insertOrUpdateList(List<IkBpDataModelBasic> ikBpDataModelBasics) {
        for (IkBpDataModelBasic ikBpDataModelBasic : ikBpDataModelBasics) {
            if (ikBpDataModelBasic.getDataModelId() == null) {
                // 新增
                ikBpDataModelBasic.setIsDel(0);
                ikBpDataModelBasicMapper.insert(ikBpDataModelBasic);
            } else {
                //修改
                ikBpDataModelBasic.setIsDel(0);
                ikBpDataModelBasicMapper.updateById(ikBpDataModelBasic);
            }
        }
        return 1;
    }

}




