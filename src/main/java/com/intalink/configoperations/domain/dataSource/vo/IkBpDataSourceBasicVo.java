package com.intalink.configoperations.domain.dataSource.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.intalink.configoperations.web.domain.BaseEntity;
import lombok.Data;
import javax.validation.constraints.NotBlank;


/**
 * 数据源信息表
 *
 * @TableName ik_bp_data_source_basic
 */
@TableName(value = "ik_bp_data_source_basic")
@Data
public class IkBpDataSourceBasicVo extends BaseEntity {
    /**
     * 主键
     */
    @TableId
    private Integer dataSourceId;

    /**
     * 数据源名称
     */
    @NotBlank(message = "数据源名称不能为空")
    private String dataSourceName;

    /**
     * 数据源IP
     */
    @NotBlank(message = "数据源url不能为空")
    private String url;

    /**
     * 用户名称
     */
    @NotBlank(message = "用户名称不能为空")
    private String userName;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 数据库类型
     */
    @NotBlank(message = "数据库类型不能为空")
    private String databaseType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}