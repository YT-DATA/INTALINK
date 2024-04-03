package com.intalink.configoperations.domain.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统信息表
 * @TableName ik_bp_system_basic
 */
@TableName(value ="ik_bp_system_basic")
@Data
public class IkBpSystemBasic implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.INPUT)
    private Integer systemId;

    /**
     * 系统名称
     */
    @NotBlank(message = "数据源名称不能为空")
    private String systemName;

    /**
     * 系统编码
     */
    @NotBlank(message = "数据源名称不能为空")
    private String systemCode;

    /**
     * 系统描述
     */
    private String systemRemark;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date creatTime;

    /**
     * 是否删除
     */
    private Integer isDel;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}