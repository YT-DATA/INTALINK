package com.intalink.configoperations.service.dataTable.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.intalink.configoperations.domain.dataTable.IkBpDataTableBasic;
import com.intalink.configoperations.domain.dataTable.vo.IkBpDataTableBasicVo;
import com.intalink.configoperations.domain.model.IkBpDataModelBasic;
import com.intalink.configoperations.enums.SystemOrTableTreatmentMethodEnum;
import com.intalink.configoperations.mapper.dataColumn.IkBpDataColumnBasicMapper;
import com.intalink.configoperations.mapper.dataTable.IkBpDataTableBasicMapper;
import com.intalink.configoperations.mapper.model.IkBpDataModelBasicMapper;
import com.intalink.configoperations.service.dataTable.IkBpDataTableBasicService;
import com.intalink.configoperations.web.domain.AjaxResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author whx
 */
@Service
public class IkBpDataTableBasicServiceImpl implements IkBpDataTableBasicService {

    @Autowired
    private IkBpDataTableBasicMapper ikBpDataTableBasicMapper;

    @Autowired
    private IkBpDataModelBasicMapper ikBpDataModelBasicMapper;

    @Autowired
    private IkBpDataColumnBasicMapper ikBpDataColumnBasicMapper;

    @Override
    public List<IkBpDataTableBasicVo> selectIkBpDataTableBasicLists(IkBpDataTableBasicVo ikBpDataTableBasicVo) {
        String dataTableName;
        if (ikBpDataTableBasicVo.getDataTableName() == null){
            dataTableName = "%%";
        }else {
            dataTableName = "%"+ikBpDataTableBasicVo.getDataTableName()+"%";
        }
        ikBpDataTableBasicVo.setDataTableName(dataTableName);
        return ikBpDataTableBasicMapper.selectDataSourceList(ikBpDataTableBasicVo);
    }

    @Override
    public void deleteDataTableBasicByIds(Integer[] dataTableIds) {
        ikBpDataTableBasicMapper.deleteByDataTableId(dataTableIds);
        ikBpDataColumnBasicMapper.deleteByDataTableId(dataTableIds);
    }

    @Override
    public AjaxResult insertOrUpdate(IkBpDataTableBasicVo ikBpDataTableBasic) {
        IkBpDataTableBasic ikBpDataTable = new IkBpDataTableBasic();
        ikBpDataTable.setDataTableCode(ikBpDataTableBasic.getDataTableCode());
        ikBpDataTable.setDataTableName(ikBpDataTableBasic.getDataTableName());
        ikBpDataTable.setDataTableRemark(ikBpDataTableBasic.getDataTableRemark());
        IkBpDataModelBasic ikBpDataModelBasic = ikBpDataModelBasicMapper.selectOneByModelName(ikBpDataTableBasic.getDataModelName());
        if (ikBpDataModelBasic == null){
            IkBpDataModelBasic ik = new IkBpDataModelBasic();
            ik.setDataModelName(ikBpDataTableBasic.getDataModelName());
            ik.setDataModelRemark(ikBpDataTableBasic.getDataModelName());
            ik.setIsDel(0);
            ikBpDataModelBasicMapper.insert(ik);
            ikBpDataTable.setDataModelId(ikBpDataModelBasicMapper.selectOneByModelName(ikBpDataTableBasic.getDataModelName()).getDataModelId());
        }else {
            ikBpDataModelBasic.setIsDel(0);
            ikBpDataModelBasicMapper.updateById(ikBpDataModelBasic);
            ikBpDataTable.setDataModelId(ikBpDataModelBasic.getDataModelId());
        }
        if (ikBpDataTableBasic.getDataTableId() == null) {
            // 新增
            ikBpDataTable.setIsDel(0);
            return AjaxResult.success(ikBpDataTableBasicMapper.insert(ikBpDataTable));
        } else {
            //修改
            ikBpDataTable.setDataTableId(ikBpDataTableBasic.getDataTableId());
            ikBpDataTable.setIsDel(0);
            return AjaxResult.success(ikBpDataTableBasicMapper.updateById(ikBpDataTable));
        }
    }

    @Override
    public List<IkBpDataTableBasic> selectDataTableBasicByModelId(Integer dataModelId) {
        return ikBpDataTableBasicMapper.selectDataTableBasicByModelId(dataModelId);
    }

    @Override
    public Map<String, Object> tableImport(List<List<String>> result,Integer dataModelId) {
        Map<String, Object> resultMap = new HashMap<>();
        List<Map> existsTables = new ArrayList<>();
        Map existsTable = null;
        Set<Map> tableSet = new HashSet<>();
        List<IkBpDataTableBasic> tableDetailList = new ArrayList<IkBpDataTableBasic>();
        for (int i = 1; i < result.size(); i++) {
            //数据表名称
            String tableName = result.get(i).get(0);
            String tableCode = result.get(i).get(1);
            if (StringUtils.isBlank(tableName)) {
                tableName = tableCode;
            }
            IkBpDataTableBasic tableBasic = ikBpDataTableBasicMapper.selectByModelIdAndTableCode(dataModelId, tableCode);
            if (tableBasic != null) {
                existsTable = new HashMap();
                existsTable.put("modelId", dataModelId);
                existsTable.put("tableName", tableName);
                existsTable.put("tableCode", tableCode);
                tableSet.add(existsTable);
                continue;
            }

            IkBpDataTableBasic ikBpDataTableBasic = new IkBpDataTableBasic();
            ikBpDataTableBasic.setDataTableName(tableName);
            ikBpDataTableBasic.setDataTableCode(tableCode);
            ikBpDataTableBasic.setDataModelId(dataModelId);

            tableDetailList.add(ikBpDataTableBasic);
        }
        //将数据项信息分批量的插入到数据库中
        int size = tableDetailList.size();
        if (size > 0) {
            int pointsDataLimit = 100;//限制条数
            if (pointsDataLimit < size) {  //每一百条数据插入一次
                int part = size / pointsDataLimit;//分批数
                for (int i = 0; i < part; i++) {
                    List<IkBpDataTableBasic> listPage = tableDetailList.subList(0, pointsDataLimit);
                    insertOrUpdateList(listPage);
                    //剔除
                    tableDetailList.subList(0, pointsDataLimit).clear();
                }
                if (tableDetailList.size() > 0) {
                    insertOrUpdateList(tableDetailList);
                }
            } else {
                //插入数据
                insertOrUpdateList(tableDetailList);
            }
        }
        tableSet.forEach(table -> {
            existsTables.add(table);
        });
        if (existsTables.size() > 0) {
            resultMap.put("existsTables", existsTables);
            resultMap.put("systemOrTableTreatmentMethods", SystemOrTableTreatmentMethodEnum.getList());
            //return resultMap;
        }
        return resultMap;
    }

    @Override
    public Integer count() {
        QueryWrapper<IkBpDataTableBasic> qw = new QueryWrapper<>();
        qw.eq("is_del",0);
        return ikBpDataTableBasicMapper.selectCount(qw);
    }

    public int insertOrUpdateList(List<IkBpDataTableBasic> ikBpDataTableBasics) {
        for (IkBpDataTableBasic ikBpDataTableBasic : ikBpDataTableBasics) {
            if (ikBpDataTableBasic.getDataTableId() == null) {
                // 新增
                ikBpDataTableBasic.setIsDel(0);
                ikBpDataTableBasicMapper.insert(ikBpDataTableBasic);
            } else {
                //修改
                ikBpDataTableBasic.setIsDel(0);
                ikBpDataTableBasicMapper.updateById(ikBpDataTableBasic);
            }
        }
        return 1;
    }
}




