package com.intalink.configoperations.domain.scoringScheme;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 评分方案表
 * @TableName ik_fp_scoring_scheme_basic
 */
@TableName(value ="ik_fp_scoring_scheme_basic")
@Data
public class IkFpScoringSchemeBasic implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.INPUT)
    private Integer scoringSchemeId;

    /**
     * 评分方案名称
     */
    @NotBlank(message = "评分方案名称不能为空")
    private String scoringSchemeName;

    /**
     * 评分方案描述
     */
    private String scoringSchemeRemark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}