package com.intalink.configoperations.domain.relationshipInput;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName ik_rp_data_table_relation
 */
@TableName(value ="ik_rp_data_table_relation")
@Data
public class IkRpDataTableRelation implements Serializable {
    /**
     * 数据项关系id
     */
    @TableId(type = IdType.INPUT)
    private Integer dataTableRelationId;
    /**
     * 数据列Id
     */
    private Integer dataColumnId;

    /**
     * 关联数据项Id
     */
    private Integer relationDataColumnId;

    /**
     * 关联类型
     */
    private Integer relationType;

    /**
     * 关联语句字符串
     */
    private String relationStr;
}