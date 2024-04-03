package com.intalink.configoperations.domain.system.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.intalink.configoperations.web.domain.BaseEntity;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 系统信息表
 * @TableName ik_bp_system_basic
 */
@TableName(value ="ik_bp_system_basic")
@Data
public class IkBpSystemBasicVo extends BaseEntity {
    /**
     * 主键
     */
    @TableId
    private Integer systemId;

    /**
     * 系统名称
     */
    @NotBlank(message = "系统名称不能为空")
    private String systemName;

    /**
     * 系统编码
     */
    @NotBlank(message = "系统编码不能为空")
    private String systemCode;

    /**
     * 系统描述
     */
    private String systemRemark;

    /**
     * 数据源Id
     */
    private Integer dataSourceId;

    /**
     * 数据源名称
     */
    private String dataSourceName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date creatTime;

    /**
     * 模型Id
     */
    private Integer dataModelId;

    /**
     * 模型名称
     */
    private String dataModelName;

    /**
     * 模型名称
     */
    private Integer isDel;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}