package com.intalink.configoperations.constant;

/**
 * 数据库连接驱动 常量类
 */
public class DatabaseDriverConstants {

    // Oracle
    public static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";

    // MySQL
    public static final String MYSQL_DRIVER_5 = "com.mysql.jdbc.Driver"; // MySQL 5.x 版本
    public static final String MYSQL_DRIVER_8 = "com.mysql.cj.jdbc.Driver"; // MySQL 8.x 版本及以上

    // SQL Server
    public static final String SQL_SERVER_DRIVER_4 = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // SQL Server 4.x 版本
    public static final String SQL_SERVER_DRIVER_6 = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // SQL Server 6.x 版本及以上

    // PostgreSQL
    public static final String POSTGRESQL_DRIVER = "org.postgresql.Driver";

    // 达梦数据库（DM）
    public static final String DM_DRIVER = "dm.jdbc.driver.DmDriver";

    // 人大金仓数据库（Kingbase）
    public static final String KINGBASE_DRIVER = "com.kingbase.Driver";

}