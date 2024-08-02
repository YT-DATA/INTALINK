package com.intalink.configoperations.utils;

import java.sql.*;

public class DBUtils {
    //请求地址
    private String JDBC_URL = "jdbc:mysql://39.106.105.4:3306/intalink-opensource?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
    //数据库用户
    private String JDBC_USER = "root";
    //数据库密码
    private String JDBC_PASSWORD = "Liuzong123456.";


    /**
     * 根据sql语句获取结果集
     *
     * @param sqlstr
     * @return
     */
    public ResultSet getResultSet(String dataSourceType, String sqlstr) {
        try {
            String className = "";
            switch (dataSourceType) {
                case "mysql":
                    className = "com.mysql.cj.jdbc.Driver";
                    break;
                case "oracle":
                    className = "oracle.jdbc.driver.OracleDriver";
                    break;
            }
            // Load MySQL JDBC Driver1
            Class.forName(className);
            // Establish connection to MySQL
            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                // Create prepared statement
                PreparedStatement stmt = conn.prepareStatement(sqlstr);
                // Execute the query and obtain result set
                ResultSet resultSet = stmt.executeQuery();
                if (resultSet.next()) {
                    return resultSet;
                }
                return null;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
