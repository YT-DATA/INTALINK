package com.intalink.configoperations.utils;

import com.alibaba.fastjson.JSONObject;
import com.intalink.configoperations.constant.DatabaseDriverConstants;
import com.intalink.configoperations.domain.dataSource.SDsnaDsSettingNew;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.*;

/**
 * 数据源工具类
 */
public class DataSourceUtil {

    private static final String MYSQL_TEMPLATE = "jdbc:mysql://%s:%s/%s?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai&allowMultiQueries=true&allowPublicKeyRetrieval=true";
    private static final String ORACLE_TEMPLATE_SID = "jdbc:oracle:thin:@%s:%s:%s";
    private static final String ORACLE_TEMPLATE_SERVICE_NAME = "jdbc:oracle:thin:@%s:%s/%s";
    private static final String SQLSERVER_TEMPLATE = "jdbc:sqlserver://%s:%s;databaseName=%s";
    private static final String POSTGRESQL_TEMPLATE = "jdbc:postgresql://%s:%s/%s";
    private static final String DM_TEMPLATE = "jdbc:dm://%s:%s/%s";
    private static final String KINGBASE_TEMPLATE = "jdbc:kingbase://%s:%s/%s";

    /**
     * 根据传入信息拼接数据源url
     *
     * @param datasourceType 数据源类型
     * @param ip             ip
     * @param port           端口
     * @param connectionType 连接方式(0：服务名，1：SID )  当数据库类型为Oracle时，该属性才有值
     * @param databaseName   数据库连接名
     * @param sid            SID (当数据源类型为Oracle且连接方式为SID时，该属性才有值)
     * @return
     */
    public static String buildUrl(String datasourceType, String ip, String port, Integer connectionType, String databaseName, String sid) {
        if (StringUtils.isEmpty(datasourceType) || StringUtils.isEmpty(ip) || StringUtils.isEmpty(port) || StringUtils.isEmpty(databaseName)) {
            throw new IllegalArgumentException("数据源链接获取失败，请检查数据源参数完整性!");
        }
        String url = null;
        switch (datasourceType.toLowerCase()) {
            case "mysql":
                url = String.format(MYSQL_TEMPLATE, ip, port, databaseName);
                break;
            case "oracle":
                if (connectionType == 0) {
                    url = String.format(ORACLE_TEMPLATE_SERVICE_NAME, ip, port, databaseName);
                } else if (connectionType == 1) {
                    url = String.format(ORACLE_TEMPLATE_SID, ip, port, sid);
                }
                break;
            case "sqlserver":
                url = String.format(SQLSERVER_TEMPLATE, ip, port, databaseName);
                break;
            case "postgresql":
                url = String.format(POSTGRESQL_TEMPLATE, ip, port, databaseName);
                break;
            case "dm":
                url = String.format(DM_TEMPLATE, ip, port, databaseName);
                break;
            case "kingbase":
                url = String.format(KINGBASE_TEMPLATE, ip, port, databaseName);
                break;
            default:
                throw new IllegalArgumentException("当前类型数据源暂不兼容：" + datasourceType);
        }
        return url;
    }

    /**
     * 获取数据库驱动信息
     *
     * @param datasourceType 数据源（数据库）类型
     * @return
     */
    //TODO 后续考虑获取数据库版本信息，从而获取数据库连接驱动信息
    public static String getDriver(String datasourceType) {
        String driver = null;
        switch (datasourceType.toLowerCase()) {
            case "mysql":
                driver = DatabaseDriverConstants.MYSQL_DRIVER_8;
                break;
            case "oracle":
                driver = DatabaseDriverConstants.ORACLE_DRIVER;
                break;
            case "sqlserver":
                driver = DatabaseDriverConstants.SQL_SERVER_DRIVER_4;
                break;
            case "postgresql":
                driver = DatabaseDriverConstants.POSTGRESQL_DRIVER;
                break;
            case "dm":
                driver = DatabaseDriverConstants.DM_DRIVER;
                break;
            case "kingbase":
                driver = DatabaseDriverConstants.KINGBASE_DRIVER;
                break;
            default:
                throw new IllegalArgumentException("未找到匹配当前数据源类型的驱动信息！");
        }
        return driver;
    }

    /**
     * 将List<JSONObject> 转换成 List<LinkedHashMap<String, Object>>
     *
     * @param jsonObjects 要转换的JSONObject列表
     * @return 转换后的LinkedHashMap列表
     */
    public static List<LinkedHashMap<String, Object>> convertJsonList(List<JSONObject> jsonObjects) {
        List<LinkedHashMap<String, Object>> resultList = new ArrayList<>();

        for (JSONObject jsonObject : jsonObjects) {
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            for (String key : jsonObject.keySet()) {
                Object value = jsonObject.get(key);
                map.put(key, value);
            }
            resultList.add(map);
        }

        return resultList;
    }

    /**
     * 根据数据源信息和查询SQL获取数据查询结果集
     *
     * @param sDsnaDsSetting 数据库连接信息
     * @param SQL
     * @return
     */
    public static List<JSONObject> selectSql(SDsnaDsSettingNew sDsnaDsSetting, String SQL) {

        List<JSONObject> list = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
//            获取拼接的数据url
            String url = buildUrl(sDsnaDsSetting.getDatasourceType(), sDsnaDsSetting.getIp(), sDsnaDsSetting.getPort(), sDsnaDsSetting.getConnectionType(), sDsnaDsSetting.getDatabaseName(), sDsnaDsSetting.getSid());
//            根据当前数据源类型获取驱动
            String driver = getDriver(sDsnaDsSetting.getDatasourceType());

            Class.forName(driver);
            conn = DriverManager.getConnection(url, sDsnaDsSetting.getDatasourceUser(), sDsnaDsSetting.getDatasourcePass());

            st = conn.createStatement();
            rs = st.executeQuery(SQL);
            ResultSetMetaData md = null;//获取键名
            md = rs.getMetaData();   //md中的数据类型 sqlType = "ntext"

            int columnCount = rs.getMetaData().getColumnCount();
            boolean flag = true;
            int index = 0;
            while (rs.next()) {
                JSONObject rowData = new JSONObject();//声明Map
                for (int i = 1; i <= columnCount; i++) {//循环bean里的字段  text
                    if (flag) {
                    }
                    rowData.put(md.getColumnName(i), rs.getObject(i) + "");//获取键名及值
                }
                System.out.println(++index);
                flag = false;
                list.add(rowData);
            }
            System.out.println("结果:" + list.toString());
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    System.out.println("清理了rs");
                } catch (Exception e) {
                }
            }
            if (st != null) try {
                st.close();
                System.out.println("清理了st");
            } catch (Exception e) {
            }
            if (conn != null) try {
                conn.close();
                System.out.println("清理了conn");
            } catch (Exception e) {
            }
        }
        return list;
    }

    public static Set<LinkedHashMap<String, Object>> selectSqlNew(SDsnaDsSettingNew sDsnaDsSetting, String SQL) {

        Set<LinkedHashMap<String, Object>> list = new HashSet<>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
//            获取拼接的数据url
            String url = buildUrl(sDsnaDsSetting.getDatasourceType(), sDsnaDsSetting.getIp(), sDsnaDsSetting.getPort(), sDsnaDsSetting.getConnectionType(), sDsnaDsSetting.getDatabaseName(), sDsnaDsSetting.getSid());
//            根据当前数据源类型获取驱动
            String driver = getDriver(sDsnaDsSetting.getDatasourceType());

            Class.forName(driver);
            conn = DriverManager.getConnection(url, sDsnaDsSetting.getDatasourceUser(), sDsnaDsSetting.getDatasourcePass());

            st = conn.createStatement();
            rs = st.executeQuery(SQL);
            ResultSetMetaData md = null;//获取键名
            md = rs.getMetaData();   //md中的数据类型 sqlType = "ntext"

            int columnCount = rs.getMetaData().getColumnCount();
            int index = 0;
            while (rs.next()) {
                LinkedHashMap<String, Object> rowData = new LinkedHashMap<>();//声明Map
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = md.getColumnLabel(i); // 获取列的别名或原始名称
                    rowData.put(columnName, rs.getObject(i));
                }
                list.add(rowData);
            }
            System.out.println("结果:" + list.toString());
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    System.out.println("清理了rs");
                } catch (Exception e) {
                }
            }
            if (st != null) try {
                st.close();
                System.out.println("清理了st");
            } catch (Exception e) {
            }
            if (conn != null) try {
                conn.close();
                System.out.println("清理了conn");
            } catch (Exception e) {
            }
        }
        return list;
    }

    /**
     * 获取特征值sql
     * @param sDsnaDsSetting
     * @param sql
     * @return
     */
    public static List<LinkedHashMap<String, Object>> executeQuery(SDsnaDsSettingNew sDsnaDsSetting, String sql) {
        List<LinkedHashMap<String, Object>> list = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
//            获取拼接的数据url
            String url = buildUrl(sDsnaDsSetting.getDatasourceType(), sDsnaDsSetting.getIp(), sDsnaDsSetting.getPort(), sDsnaDsSetting.getConnectionType(), sDsnaDsSetting.getDatabaseName(), sDsnaDsSetting.getSid());
//            根据当前数据源类型获取驱动
            String driver = getDriver(sDsnaDsSetting.getDatasourceType());

            Class.forName(driver);
            conn = DriverManager.getConnection(url, sDsnaDsSetting.getDatasourceUser(), sDsnaDsSetting.getDatasourcePass());

            st = conn.createStatement();
            rs = st.executeQuery(sql);
            ResultSetMetaData md = null;//获取键名
            md = rs.getMetaData();   //md中的数据类型 sqlType = "ntext"

            int columnCount = rs.getMetaData().getColumnCount();
            int index = 0;
            while (rs.next()) {
                LinkedHashMap<String, Object> rowData = new LinkedHashMap<>();//声明Map
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = md.getColumnLabel(i); // 获取列的别名或原始名称
                    rowData.put(columnName, rs.getObject(i));
                }
                list.add(rowData);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    System.out.println("清理了rs");
                } catch (Exception e) {
                }
            }
            if (st != null) try {
                st.close();
                System.out.println("清理了st");
            } catch (Exception e) {
            }
            if (conn != null) try {
                conn.close();
                System.out.println("清理了conn");
            } catch (Exception e) {
            }
        }
        return list;
    }

    public static void main(String[] args) {
        testMysql();
    }


    public static void testMysql() {
//        数据库连接信息
        String ip = "";
        String port = "";
        String databaseName = "";
        String userName = "";
        String pass = "";

//        封装link对象
        SDsnaDsSettingNew link = new SDsnaDsSettingNew();
        link.setDatasourceType("mysql");
        link.setIp(ip);
        link.setPort(port);
        link.setDatabaseName(databaseName);
        link.setDatasourceUser(userName);
        link.setDatasourcePass(pass);
//        编写查询sql
        String sql = "select * from sys_user";
//        调用工具类方法获取查询结果集
        List<JSONObject> jsonObjects = DataSourceUtil.selectSql(link, sql);
        System.out.println(jsonObjects);
    }

}