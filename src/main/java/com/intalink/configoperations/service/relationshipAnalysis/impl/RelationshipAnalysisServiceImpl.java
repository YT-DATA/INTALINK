package com.intalink.configoperations.service.relationshipAnalysis.impl;

import com.intalink.configoperations.domain.dataColumn.IkBpDataColumnBasic;
import com.intalink.configoperations.domain.dataRelationShip.vo.RelationShip;
import com.intalink.configoperations.domain.dataSource.IkBpDataSourceBasic;
import com.intalink.configoperations.domain.dataTable.IkBpDataTableBasic;
import com.intalink.configoperations.mapper.dataColumn.IkBpDataColumnBasicMapper;
import com.intalink.configoperations.mapper.dataSource.IkBpDataSourceBasicMapper;
import com.intalink.configoperations.mapper.dataTable.IkBpDataTableBasicMapper;
import com.intalink.configoperations.service.relationshipAnalysis.RelationshipAnalysisService;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.sql.*;
import java.util.*;

@Service
public class RelationshipAnalysisServiceImpl implements RelationshipAnalysisService {

    //1.关联redis登内存数据库进行存取
    //2.redis当中存储数据的具体形式，需要明确数据源、数据表、数据项、数据类型、数据值
    //3.对内存数据库当中的数据进行随机抽样，进行数据项比对，进行数据项的关联分析
    //4.关联分析的结果，并且需要将结果进行存储，方便后续的查询和分析


    //1.将数据库的数据表、数据项全量存储到redis当中，并对数据项
    //2.对数据库当中的每个数据项的数据进行随机抽样，并存储到redis数据库当中
    //3.将数据库当中的数据项数值进行对比

    private static final String REDIS_HOST = "39.106.28.179"; // Redis服务器地址
    private static final int REDIS_PORT = 6379;               // Redis服务器端口
    private static final String REDIS_PASSWORD = "Liuzong123456.";

    @Autowired
    public IkBpDataSourceBasicMapper ikBpDataSourceBasicMapper;
    @Autowired
    public IkBpDataTableBasicMapper ikBpDataTableBasicMapper;
    @Autowired
    public IkBpDataColumnBasicMapper ikBpDataColumnBasicMapper;


    /**
     * 从redis当中获取全部的数据集
     */
    public static List<String> fetchRelationShipData(String dataSetKey) {
        List<String> dataList = new ArrayList<>();
        // 创建Jedis连接
        Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);
        try {
            // 设置密码
            jedis.auth(REDIS_PASSWORD);
            // 检查是否连接成功
            System.out.println("链接Redis: " + jedis.ping());
            // 获取数据集
            dataList = jedis.lrange(dataSetKey, 0, -1); // 如果是List类型
            // 打印数据
            System.out.println("打印RelationShipData:");
            for (String data : dataList) {
                System.out.println(data);
            }
            // 关闭连接
            jedis.close();
            System.out.println("关闭链接");
        } catch (Exception e) {
            System.err.println("链接失败，失败原因: " + e.getMessage());
        }
        return dataList;
    }

    /**
     * 计算所需的样本大小
     *
     * @param totalPopulation 总数据量
     * @param confidenceLevel 置信水平对应的z分数（例如1.96对应95%置信水平）
     * @param marginOfError   允许的误差（例如0.05表示5%）
     * @return 计算出的样本大小
     */
    public static int calculateSampleSize(int totalPopulation, double confidenceLevel, double marginOfError) {
        double p = 0.5; // 假设事件发生的概率为0.5，因为这样可以使样本大小达到最大
        double z = confidenceLevel; // 置信水平的z分数
        double E = marginOfError; // 允许的误差

        double numerator = totalPopulation * Math.pow(z, 2) * p * (1 - p);
        double denominator = (totalPopulation - 1) * Math.pow(E, 2) + Math.pow(z, 2) * p * (1 - p);
        int sampleSize = (int) Math.ceil(numerator / denominator);

        return sampleSize;
    }


    /**
     * 随机取样方法
     *
     * @param <T>        泛型，表示列表中元素的类型
     * @param dataList   待取样的数据列表
     * @param sampleSize 需要的样本数量
     * @return 返回一个随机样本列表
     */
    public static <T> List<T> getRandomSamples(List<T> dataList, int sampleSize) {
        // 确保样本数量不超过数据量
        if (dataList.size() < sampleSize) {
            // 如果数据量小于样本数量
            sampleSize = dataList.size();
        }
        Collections.shuffle(dataList);  // 随机化列表顺序
        return new ArrayList<>(dataList.subList(0, sampleSize));  // 返回子列表作为样本
    }

    /**
     * 测试方法主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        // 示例字段数据
//        List<String> data = fetchRelationShipData("RelationShipData");
//
//        // 计算所需的样本大小
//        int sampleSize = calculateSampleSize(data.size(), 1.96, 0.05);
//        // 随机取样,样本量根据数据量算法决定
//        List<String> samplesData = getRandomSamples(data, sampleSize);
//        System.out.println("获取到的随机数据结果: " + samplesData);

        // 进行数据比对，进行关联分析
        // 如何确定redis当中存储的数据格式，能确保比对。

        dataRelationShip();

    }


    /**
     * 数据关联分析
     */
    public static void dataRelationShip() {
        // 假定当前redis当中存在两份数据集，一份为数据源-数据库-数据表-数据项
        // 另一份数据集为对应数据项的数据值
//        dataSet: dataSource-database-table-dataItem
//                 dataSource-database-table-dataItem1
//        dataSource-database-table-dataItem:1,3,5,7,9
//        dataSource-database-table-dataItem1:3,5,9,10

        //获取数据项数据集
        List<String> dataList = fetchRelationShipData("RelationShipData");
        for (String data : dataList) {
            //遍历每一个数据项，进行比对
            System.out.println("数据项: " + data);
            //获取数据项的数据值
            List<String> valueList = fetchRelationShipData(data);
            //数据源Id
            String dataSourceId = data.split("-")[0];
            //数据表Id
            String tableId = data.split("-")[1];
            //数据项ID
            String dataItemId = data.split("-")[2];
            //获取数据表信息
            IkBpDataTableBasic ikBpDataTableBasic = selectByDataTableId(Integer.parseInt(tableId));
            //比对过程
            for (String data1 : dataList) {
                //排除自身比对
                if (!data1.equals(data)) {
                    String tableId1 = data1.split("-")[1];
                    List<String> theSameTableColumn = getTheSameTableColumn(tableId1);


                    //比较值列
                    List<String> valueList1 = fetchRelationShipData(data1);
                    //关系分析
                    relationShipsAnalysis(data, data1, valueList, valueList1);
                }
            }
        }
    }


    /**
     * 获取相同表的列
     *
     * @param tableId
     * @return
     */
    public static List<String> getTheSameTableColumn(String tableId) {
        List<String> returnList = new ArrayList<>();
        List<String> dataList = fetchRelationShipData("RelationShipData");
        for (String data : dataList) {
            if (data.split("-")[1].equals(tableId)) {
                returnList.add(data);
            }
        }
        return returnList;
    }


    /**
     * 数据关联分析
     *
     * @param data
     * @param data1
     * @param valueList
     * @param valueList1
     */
    public static void relationShipsAnalysis(String data, String data1, List<String> valueList, List<String> valueList1) {
        //定义关系数据集
        List<RelationShip> relationShipList = new ArrayList<>();
        //判断逻辑1
        //计算交集、差集1、差集2
        Map<String, List<String>> resultMap = calculateOperations(valueList, valueList1);
        // 分别获取交集、差集1、差集2
        //交集
        List<String> intersection = resultMap.get("intersection");
        //差集1
        List<String> difference1 = resultMap.get("difference1");
        //差集2
        List<String> difference2 = resultMap.get("difference2");
        //交集同List和List1完全相等，则认为关联
        if (intersection.size() == valueList.size() && intersection.size() == valueList1.size()) {
            RelationShip relationShip = new RelationShip();
            relationShip.setMainColumn(data);
            relationShip.setRelatedColumn(data1);
            relationShip.setRelationShipTypeStr("");//关联表达式
            //添加进关联表达式结果
            relationShipList.add(relationShip);
        } else {
            //列1同交集的差集或者列2同交集的差集为0
            if (difference1.size() == 0 || difference2.size() == 0) {
                RelationShip relationShip = new RelationShip();
                relationShip.setMainColumn(data);
                relationShip.setRelatedColumn(data1);
                relationShip.setRelationShipTypeStr("");//关联表达式
                //添加进关联表达式结果
                relationShipList.add(relationShip);
            }
            int difference1Count = 0;
            int difference2Count = 0;
            //列1同交集的差集大于0
            if (difference1.size() > 0) {
                //列1同交集的差集 到数据源库当中验证
                for (String value : difference1) {
                    //验证列1同交集的差集 到数据源库当中验证
                    //设定value的规则为：数据源Id-数据表Id-数据项Id-数据值

                    //对关键数值进行截取
                    String dataSourceId = data.split("-")[0];
                    String tableId = data.split("-")[1];
                    String dataItemId = data.split("-")[2];
                    String dataValue = value;
                    difference1Count += getRelationShip(dataSourceId, tableId, dataItemId, dataValue);
                }
                //验证结果
                if (difference1Count == difference1.size()) {
                    RelationShip relationShip = new RelationShip();
                    relationShip.setMainColumn(data);
                    relationShip.setRelatedColumn(data1);
                    relationShip.setRelationShipTypeStr("");//关联表达式
                    //添加进关联表达式结果
                    relationShipList.add(relationShip);
                }
            }
            //列2同交集的差集大于0
            if (difference2.size() > 0) {
                //列1同交集的差集 到数据源库当中验证
                for (String value : difference2) {
                    //验证列1同交集的差集 到数据源库当中验证
                    //设定value的规则为：数据源Id-数据表Id-数据项Id-数据值

                    //对关键数值进行截取
                    String dataSourceId = data1.split("-")[0];
                    String tableId = data1.split("-")[1];
                    String dataItemId = data1.split("-")[2];
                    String dataValue = value;
                    difference2Count += getRelationShip(dataSourceId, tableId, dataItemId, dataValue);
                }
                //验证结果
                if (difference2Count == difference2.size()) {
                    RelationShip relationShip = new RelationShip();
                    relationShip.setMainColumn(data1);
                    relationShip.setRelatedColumn(data);
                    relationShip.setRelationShipTypeStr("");//关联表达式
                    //添加进关联表达式结果
                    relationShipList.add(relationShip);
                }
            }
            if (difference1Count == difference1.size() && difference2Count <= difference2.size()) {
                //列1=列2（全等）
                //列2有质量问题
                RelationShip relationShip = new RelationShip();
                relationShip.setMainColumn(data);
                relationShip.setRelatedColumn(data1);
                relationShip.setRelationShipTypeStr("");//关联表达式
                //添加进关联表达式结果
                relationShipList.add(relationShip);
            }
            if (difference1Count == difference1.size() && difference2Count == 0) {
                //列2是主表（或主数据）列2包含列1
                RelationShip relationShip = new RelationShip();
                relationShip.setMainColumn(data1);
                relationShip.setRelatedColumn(data);
                relationShip.setRelationShipTypeStr("");//关联表达式
                //添加进关联表达式结果
                relationShipList.add(relationShip);
            }
            if (difference1Count <= difference1.size() && difference1Count > 0 && difference2Count == difference2.size()) {
                //列1=列2（全等）
                //列1有质量问题
                RelationShip relationShip = new RelationShip();
                relationShip.setMainColumn(data);
                relationShip.setRelatedColumn(data1);
                relationShip.setRelationShipTypeStr("");//关联表达式
                //添加进关联表达式结果
                relationShipList.add(relationShip);
            }
            if (difference1Count == 0 && difference2Count == difference2.size()) {
                //列1是主表（或主数据）列1包含列2
                RelationShip relationShip = new RelationShip();
                relationShip.setMainColumn(data);
                relationShip.setRelatedColumn(data1);
                relationShip.setRelationShipTypeStr("");//关联表达式
                //添加进关联表达式结果
                relationShipList.add(relationShip);
            }
            if (difference1Count < difference1.size() && difference1Count > 0 && difference2Count < difference2.size() && difference2Count > 0) {
                //列1=列2
                //第一种情况：二者通过率均达到一定比例，未通过部分做为数据质量问题；
                //第二种情况：一个通过率高，另一个通过率低；高者达到一定比例后，视为子集，另一列为主表数据。
                RelationShip relationShip = new RelationShip();
                relationShip.setMainColumn(data);
                relationShip.setRelatedColumn(data1);
                relationShip.setRelationShipTypeStr("");//关联表达式
                //添加进关联表达式结果
                relationShipList.add(relationShip);
            }
            if (difference1Count == 0 && difference2Count == 0) {
                //前四种判断方法没有得到相等结论时，继续判断：
                //如果差集的数据集与取样数据相比，所占比例极低，且扩大一次取样比例后，差集与取样比例相比，仍只占小比例，那么适用前两种判断逻辑，差集部分视为数据质量；如果差集所占比例较大，则上述两种组合，视为数据项无关系。
            }
        }
    }


    /**
     * 分别求取交集、差集1、差集2
     *
     * @param list1
     * @param list2
     * @return
     */
    public static Map<String, List<String>> calculateOperations(List<String> list1, List<String> list2) {
        Set<String> intersection = new HashSet<>(list1);
        intersection.retainAll(list2); // 交集

        Set<String> difference1 = new HashSet<>(list1);
        difference1.removeAll(intersection); // list1 差集 (list1 - 交集)

        Set<String> difference2 = new HashSet<>(list2);
        difference2.removeAll(intersection); // list2 差集 (list2 - 交集)

        Map<String, List<String>> resultMap = new HashMap<>();
        // 交集
        resultMap.put("intersection", new ArrayList<>(intersection));
        // 差集1
        resultMap.put("difference1", new ArrayList<>(difference1));
        // 差集2
        resultMap.put("difference2", new ArrayList<>(difference2));

        return resultMap;
    }

    /**
     * 判断当前数值在这个数据源对应的数据库中是否存在
     *
     * @param dataSourceId
     * @param dataTableId
     * @param dataItemId
     * @param dataValue
     * @return
     */
    public static int getRelationShip(String dataSourceId, String dataTableId, String dataItemId, String dataValue) {
        try {
            System.out.println("getRelationShip:" + dataSourceId + " " + dataTableId + " " + dataItemId + " " + dataValue);
            int result = 0;
            //获取数据源信息
            IkBpDataSourceBasic ikBpDataSourceBasic = selectDataSourceById(Integer.parseInt(dataSourceId));
            //如果数据源不为空
            if (ikBpDataSourceBasic != null) {
                //获取数据表信息
                IkBpDataTableBasic ikBpDataTableBasic = selectByDataTableId(Integer.parseInt(dataTableId));
                //获取字段信息
                IkBpDataColumnBasic ikBpDataColumnBasic = selectByTableIdAndColumnCode(Integer.parseInt(dataItemId));
                String sqlstr = "select * from " + ikBpDataTableBasic.getDataTableName() + " where " + ikBpDataColumnBasic.getDataColumnName() + " = '" + dataValue + "'";
                //获取sql语句的执行结果
                result = getSqlResult(sqlstr, ikBpDataSourceBasic.getUrl(), ikBpDataSourceBasic.getDatabaseType(), ikBpDataSourceBasic.getUserName(), ikBpDataSourceBasic.getPassword());
            }
            return result;
        } catch (Exception e) {
            return 0;
        }
    }


    /**
     * 获取数据源对应的数据库信息
     *
     * @param sqlstr
     * @param dataSource
     * @param dataSourceType
     * @param userName
     * @param Password
     * @return
     */
    public static int getSqlResult(String sqlstr, String dataSource, String dataSourceType, String userName, String Password) {

        // 数据库URL，用户名和密码
        String url = "";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        // 返回结果 默认不存在
        int result = 0;// 0：不存在，1：存在

        try {
            // 根据数据库类型加载不同的驱动
            switch (dataSourceType.toLowerCase()) {
                case "mysql":
                    Class.forName("com.mysql.jdbc.Driver");
                    url = "jdbc:mysql://" + dataSource;
                    break;
                case "postgresql":
                    Class.forName("org.postgresql.Driver");
                    url = "jdbc:postgresql://" + dataSource;
                    break;
                case "oracle":
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    url = "jdbc:oracle:thin:@" + dataSource;
                    break;
                default:
                    System.out.println("Unsupported database type");
                    return 0;
            }

            // 建立数据库连接
            conn = DriverManager.getConnection(url, userName, Password);

            // 创建语句对象
            stmt = conn.createStatement();

            // 执行SQL查询
            rs = stmt.executeQuery(sqlstr);

            // 处理查询结果
            if (rs.next() && rs.getInt(1) > 0) {
                result = 1; // 存在
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            // 关闭资源
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
        }
    }

    /**
     * 根据字段Id查询字段名称
     *
     * @param dataItemId
     * @return
     */
    public static IkBpDataColumnBasic selectByTableIdAndColumnCode(Integer dataItemId) {
        try {
            String sqlstr = "select * from ik_bp_data_column_basic where data_column_id = '" + dataItemId + "' ";
            // JDBC URL, username and password of MySQL server
            String JDBC_URL = "jdbc:mysql://39.106.105.4:3306/intalink-opensource?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
            String JDBC_USER = "root";
            String JDBC_PASSWORD = "Liuzong123456.";
            try {
                // Load MySQL JDBC Driver1
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Establish connection to MySQL
                try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                    // Create prepared statement
                    PreparedStatement stmt = conn.prepareStatement(sqlstr);
                    // Execute the query and obtain result set
                    ResultSet result = stmt.executeQuery();
                    IkBpDataColumnBasic ikBpDataColumnBasic = new IkBpDataColumnBasic();
                    if (result.next()) {
                        ikBpDataColumnBasic.setDataTableId(result.getInt("data_table_id"));
                        ikBpDataColumnBasic.setDataColumnName(result.getString("data_column_name"));
                        return ikBpDataColumnBasic;
                    } else {
                        return null;
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return null;
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据表Id查询表名称信息
     *
     * @param dataTableId
     * @return
     */
    public static IkBpDataTableBasic selectByDataTableId(Integer dataTableId) {
        try {
            String sqlstr = "select * from ik_bp_data_table_basic where data_table_id = '" + dataTableId + "'";
            // JDBC URL, username and password of MySQL server
            String JDBC_URL = "jdbc:mysql://39.106.105.4:3306/intalink-opensource?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
            String JDBC_USER = "root";
            String JDBC_PASSWORD = "Liuzong123456.";
            try {
                // Load MySQL JDBC Driver1
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Establish connection to MySQL
                try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                    // Create prepared statement
                    PreparedStatement stmt = conn.prepareStatement(sqlstr);
                    // Execute the query and obtain result set
                    ResultSet result = stmt.executeQuery();
                    IkBpDataTableBasic ikBpDataTableBasic = new IkBpDataTableBasic();
                    if (result.next()) {
                        ikBpDataTableBasic.setDataTableId(result.getInt("data_table_id"));
                        ikBpDataTableBasic.setDataTableName(result.getString("data_table_name"));
                        return ikBpDataTableBasic;
                    } else {
                        return null;
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return null;
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据数据源Id查询数据源信息
     *
     * @param dataSourceId
     * @return
     */
    public static IkBpDataSourceBasic selectDataSourceById(Integer dataSourceId) {
        try {
            String sqlstr = "select * from ik_bp_data_source_basic where data_source_id = '" + dataSourceId + "'";

            // JDBC URL, username and password of MySQL server
            String JDBC_URL = "jdbc:mysql://39.106.105.4:3306/intalink-opensource?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
            String JDBC_USER = "root";
            String JDBC_PASSWORD = "Liuzong123456.";
            try {
                // Load MySQL JDBC Driver1
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Establish connection to MySQL
                try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                    // Create prepared statement
                    PreparedStatement stmt = conn.prepareStatement(sqlstr);
                    // Execute the query and obtain result set
                    ResultSet result = stmt.executeQuery();
                    IkBpDataSourceBasic ikBpDataSourceBasic = new IkBpDataSourceBasic();
                    if (result.next()) {
                        ikBpDataSourceBasic.setDataSourceId(result.getInt("data_source_id"));
                        ikBpDataSourceBasic.setDataSourceName(result.getString("data_source_name"));
                        ikBpDataSourceBasic.setUrl(result.getString("url"));
                        ikBpDataSourceBasic.setDatabaseType(result.getString("database_type"));
                        ikBpDataSourceBasic.setUserName(result.getString("user_name"));
                        ikBpDataSourceBasic.setPassword(result.getString("password"));
                        return ikBpDataSourceBasic;
                    } else {
                        return null;
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return null;
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }


}
