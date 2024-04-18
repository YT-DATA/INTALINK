package com.intalink.configoperations.service.dataColumn.impl;

import com.intalink.configoperations.domain.dataColumn.IkBpDataColumnBasic;
import com.intalink.configoperations.domain.dataColumn.vo.IkBpDataColumnBasicVo;
import com.intalink.configoperations.domain.dataTable.IkBpDataTableBasic;
import com.intalink.configoperations.domain.model.IkBpDataModelBasic;
import com.intalink.configoperations.enums.SystemOrTableTreatmentMethodEnum;
import com.intalink.configoperations.mapper.dataColumn.IkBpDataColumnBasicMapper;
import com.intalink.configoperations.mapper.dataTable.IkBpDataTableBasicMapper;
import com.intalink.configoperations.mapper.model.IkBpDataModelBasicMapper;
import com.intalink.configoperations.service.dataColumn.IkBpDataColumnBasicService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

/**
 * @author whx
 */
@Service
public class IkBpDataColumnBasicServiceImpl implements IkBpDataColumnBasicService {

    @Autowired
    private IkBpDataColumnBasicMapper ikBpDataColumnBasicMapper;

    @Autowired
    private IkBpDataTableBasicMapper ikBpDataTableBasicMapper;

    @Autowired
    private IkBpDataModelBasicMapper ikBpDataModelBasicMapper;

    /**
     * 查询列表
     * @param ikBpDataColumnBasicVo
     * @return
     */
    @Override
    public List<IkBpDataColumnBasicVo> selectIkBpDataColumnBasicLists(IkBpDataColumnBasicVo ikBpDataColumnBasicVo) {
        String dataColumnName;
        if (ikBpDataColumnBasicVo.getDataColumnName() == null) {
            dataColumnName = "%%";
        } else {
            dataColumnName = "%" + ikBpDataColumnBasicVo.getDataColumnName() + "%";
        }
        ikBpDataColumnBasicVo.setDataColumnName(dataColumnName);
        return ikBpDataColumnBasicMapper.selectDataColumnList(ikBpDataColumnBasicVo);
    }

    /**
     * 根据参数Id获取详细信息
     * @param dataColumnId
     * @return
     */
    @Override
    public IkBpDataColumnBasic selectDataColumnBasicById(Integer dataColumnId) {
        return ikBpDataColumnBasicMapper.selectDataColumnById(dataColumnId);
    }

    /**
     * 根据参数Ids删除信息
     * @param dataColumnIds
     */
    @Override
    public void deleteDataColumnBasicByIds(Integer[] dataColumnIds) {
        ikBpDataColumnBasicMapper.deleteByDataColumnId(dataColumnIds);
    }

    /**
     * 插入/修改数据
     * @param ikBpDataColumnBasic
     * @return
     */
    @Override
    @Transactional
    public int insertOrUpdate(IkBpDataColumnBasicVo ikBpDataColumnBasic) {
        IkBpDataColumnBasic ik = new IkBpDataColumnBasic();
        ik.setDataColumnCode(ikBpDataColumnBasic.getDataColumnCode());
        ik.setDataColumnName(ikBpDataColumnBasic.getDataColumnName());
        ik.setDataColumnRemark(ikBpDataColumnBasic.getDataColumnRemark());
        Integer modelId = null;
        if (ikBpDataColumnBasic.getDataModelId() == null) {
            IkBpDataModelBasic ikBpDataModelBasic = new IkBpDataModelBasic();
            ikBpDataModelBasic.setDataModelCode(ikBpDataColumnBasic.getDataColumnName());
            ikBpDataModelBasic.setDataModelName(ikBpDataColumnBasic.getDataColumnName());
            ikBpDataModelBasic.setDataModelRemark(ikBpDataColumnBasic.getDataColumnName());
            ikBpDataModelBasic.setIsDel(0);
            ikBpDataModelBasicMapper.saveModel(ikBpDataModelBasic);
            modelId = ikBpDataModelBasic.getDataModelId();
        }else {
            modelId =ikBpDataColumnBasic.getDataModelId();
        }
        IkBpDataTableBasic ikBpDataTableBasic = ikBpDataTableBasicMapper.selectByTableName(ikBpDataColumnBasic.getDataTableName(), ikBpDataColumnBasic.getDataModelId());
        if (ikBpDataTableBasic == null) {
            IkBpDataTableBasic ikBpDataTableBasic1 = new IkBpDataTableBasic();
            ikBpDataTableBasic1.setDataTableName(ikBpDataColumnBasic.getDataTableName());
            ikBpDataTableBasic1.setDataTableCode(ikBpDataColumnBasic.getDataTableName());
            ikBpDataTableBasic1.setDataTableRemark(ikBpDataColumnBasic.getDataTableName());
            ikBpDataTableBasic1.setDataModelId(modelId);
            ikBpDataTableBasic1.setIsDel(0);
            ikBpDataTableBasicMapper.insert(ikBpDataTableBasic1);
            ik.setDataTableId(ikBpDataTableBasicMapper.selectByTableName(ikBpDataColumnBasic.getDataTableName(), ikBpDataColumnBasic.getDataModelId()).getDataTableId());
        } else {
            ikBpDataTableBasic.setIsDel(0);
            ikBpDataTableBasicMapper.updateById(ikBpDataTableBasic);
            ik.setDataTableId(ikBpDataTableBasic.getDataTableId());
        }

        if (ikBpDataColumnBasic.getDataColumnId() == null) {
            // 新增
            ik.setIsDel(0);
            return ikBpDataColumnBasicMapper.insert(ik);
        } else {
            //修改
            ik.setDataColumnId(ikBpDataColumnBasic.getDataColumnId());
            ik.setIsDel(0);
            return ikBpDataColumnBasicMapper.updateById(ik);
        }
    }

    /**
     * 根据表id获取数据项数据
     * @param dataTableId
     * @return
     */
    @Override
    public List<IkBpDataColumnBasic> selectDataColumnBasicByTableId(Integer dataTableId) {
        return ikBpDataColumnBasicMapper.selectDataColumnBasicByTableId(dataTableId);
    }

    /**
     * 导入数据项信息数据
     * @param result
     * @param dataModelId
     * @param dataTableId
     * @return
     */
    @Override
    public Map<String, Object> columnImport(List<List<String>> result, Integer dataModelId, Integer dataTableId) {
        Map<String, Object> resultMap = new HashMap<>();
        List<Map> existsColumns = new ArrayList<>();
        Map existsTable = null;
        Set<Map> columnSet = new HashSet<>();
        List<IkBpDataColumnBasic> columnDetailList = new ArrayList<IkBpDataColumnBasic>();
        for (int i = 1; i < result.size(); i++) {
            //数据项名称
            String columnName = result.get(i).get(0);
            String columnCode = result.get(i).get(1);
            if (StringUtils.isBlank(columnName)) {
                columnName = columnCode;
            }
            IkBpDataColumnBasic columnBasic = ikBpDataColumnBasicMapper.selectByTableIdAndColumnCode(dataTableId, columnCode);
            if (columnBasic != null) {
                existsTable = new HashMap();
                existsTable.put("tableId", dataTableId);
                existsTable.put("columnName", columnName);
                existsTable.put("columnCode", columnCode);
                columnSet.add(existsTable);
                continue;
            }

            IkBpDataColumnBasic ikBpDataColumnBasic = new IkBpDataColumnBasic();
            ikBpDataColumnBasic.setDataColumnName(columnName);
            ikBpDataColumnBasic.setDataColumnRemark(columnName);
            ikBpDataColumnBasic.setDataColumnCode(columnCode);
            ikBpDataColumnBasic.setDataTableId(dataTableId);

            columnDetailList.add(ikBpDataColumnBasic);
        }
        //将数据项信息分批量的插入到数据库中
        int size = columnDetailList.size();
        if (size > 0) {
            int pointsDataLimit = 100;//限制条数
            if (pointsDataLimit < size) {  //每一百条数据插入一次
                int part = size / pointsDataLimit;//分批数
                for (int i = 0; i < part; i++) {
                    List<IkBpDataColumnBasic> listPage = columnDetailList.subList(0, pointsDataLimit);
                    insertOrUpdateList(listPage);
                    //剔除
                    columnDetailList.subList(0, pointsDataLimit).clear();
                }
                if (columnDetailList.size() > 0) {
                    insertOrUpdateList(columnDetailList);
                }
            } else {
                //插入数据
                insertOrUpdateList(columnDetailList);
            }
        }
        columnSet.forEach(column -> {
            existsColumns.add(column);
        });
        if (existsColumns.size() > 0) {
            resultMap.put("existsColumns", existsColumns);
            resultMap.put("systemOrTableTreatmentMethods", SystemOrTableTreatmentMethodEnum.getList());
            //return resultMap;
        }
        return resultMap;
    }

    /**
     * 新增/插入数据项信息
     * @param ikBpDataColumnBasics
     * @return
     */
    public int insertOrUpdateList(List<IkBpDataColumnBasic> ikBpDataColumnBasics) {
        for (IkBpDataColumnBasic ikBpDataColumnBasic : ikBpDataColumnBasics) {
            if (ikBpDataColumnBasic.getDataColumnId() == null) {
                // 新增
                ikBpDataColumnBasic.setIsDel(0);
                ikBpDataColumnBasicMapper.insert(ikBpDataColumnBasic);
            } else {
                //修改
                ikBpDataColumnBasic.setIsDel(0);
                ikBpDataColumnBasicMapper.updateById(ikBpDataColumnBasic);
            }
        }
        return 1;
    }
}




