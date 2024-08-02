package com.intalink.configoperations.domain.dataSource;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统数据源 实体
 */
@Data
public class SDsnaDsSettingNew implements Serializable {

    /**
     * 数据源id
     */
    private String datasourceId;

    /**
     * 数据源信息
     */
    private String datasourceDesc;

    /**
     * 数据源类型
     */
    private String datasourceType;

    /**
     * 数据源IP
     */
    private String ip;

    /**
     * 端口
     */
    private String port;

    /**
     * 连接方式(0：服务名，1：SID )  当数据库类型为Oracle时，该属性才有值
     */
    private Integer connectionType;

    /**
     * 数据库连接名
     */
    private String databaseName;

    /**
     * SID (当数据源类型为Oracle且连接方式为SID时，该属性才有值)
     */
    private String sid;

    /**
     * 数据源连接用户
     */
    private String datasourceUser;

    /**
     * 数据源连接密码
     */
    private String datasourcePass;

    /**
     * 数据源拥有者
     */
    private String datasourceOwner;

    /**
     * 数据源版本信息
     */
    private String datasourceVersion;

    /**
     * 排序
     */
    private Short showNo;

    @TableField(exist = false)
    private boolean hasDbLink;

    @TableField(exist = false)
    private String dbLinkName;


}