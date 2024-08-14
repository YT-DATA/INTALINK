package com.intalink.configoperations.service.dataTableRelationBasic;

/**
* @author whx
*/
public interface IkRpDataTableRelationBasicService {

    /**
     * 判断两个字段是否需要比对
     * @param columnKey  当前对比字段,如: 1-1-1(数据源ID-数据表ID-数据项ID)
     * @param comparisonColumnKey   对比目标字段,如: 1-1-1(数据源ID-数据表ID-数据项ID)
     * @return
     */
    public boolean getIsComparisonFlag(String columnKey,String comparisonColumnKey);
    /**
     * 设置比对成功与失败List
     * @param columnKey   当前对比字段,如: 1-1-1(数据源ID-数据表ID-数据项ID)
     * @param comparisonColumnKey   对比目标字段,如: 1-1-1(数据源ID-数据表ID-数据项ID)
     * @param flag 比对结果,比对成功为true,比对失败为false
     */
    public void setComparisonFlag(String columnKey,String comparisonColumnKey,boolean flag);


    /**
     * 查询columnKey所对应的特征值的key
     * @param columnKey   查询的字段,如: 1-1-1(数据源ID-数据表ID-数据项ID)
     */
    public String getEigenvalueBySourceTableColumn(String columnKey);
}
