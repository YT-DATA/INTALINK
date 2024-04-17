package com.intalink.configoperations.domain.relationshipInput.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.intalink.configoperations.web.domain.BaseEntity;
import lombok.Data;

/**
 *
 * @TableName ik_rp_data_table_relation
 */
@TableName(value ="ik_rp_data_table_relation")
@Data
public class IkRpDataTableRelationVo extends BaseEntity {

    /**
     * 主键id
     */
    private Integer dataTableRelationId;



    /**
     * 主数据项数据  模型-表-项
     */
    private String dataModelTableColumn;

    /**
     * 关联数据项  模型-表-项
     */
    private String relationDataModelTableColumn;

    /**
     * 关联类型
     */
    private Integer relationType;

    /**
     * 关联语句字符串
     */
    private String relationStr;


    /**
     * 主数据模型Id
     */
    private Integer dataModelId;


    /**
     * 主数据模型编码
     */
    private String dataModelCode;


    /**
     * 关联数据模型Id
     */
    private Integer relationDataModelId;


    /**
     * 关联数据模型编码
     */
    private String relationDataModelCode;

    /**
     * 主数据表Id
     */
    private Integer dataTableId;

    /**
     * 主数据表编码
     */
    private String dataTableCode;

    /**
     * 关联数据表Id
     */
    private Integer relationDataTableId;

    /**
     * 关联数据表编码
     */
    private String relationDataTableCode;

    /**
     * 主数据项Id
     */
    private Integer dataColumnId;

    /**
     * 主数据项编码
     */
    private String dataColumnCode;

    /**
     * 关联数据项Id
     */
    private Integer relationDataColumnId;

    /**
     * 关联数据项Id
     */
    private String relationDataColumnCode;


}