package com.intalink.configoperations.eigenvalue.test;

import com.intalink.configoperations.domain.dataSource.IkBpDataSourceBasic;
import com.intalink.configoperations.domain.dataSource.SDsnaDsSettingNew;
import com.intalink.configoperations.mapper.dataSource.IkBpDataSourceBasicMapper;
import com.intalink.configoperations.utils.DataSourceUtil;
import com.intalink.configoperations.web.controller.BaseController;
import com.intalink.configoperations.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author whx
 * @date 2024/7/31
 */
@RestController
@RequestMapping("/Eigenvalue")
public class EigenvalueController extends BaseController {

    @Autowired
    public IkBpDataSourceBasicMapper ikBpDataSourceBasicMapper;


    /**
     * 获取数据源数量
     */
    @GetMapping(value = "/getData")
    public AjaxResult getData() {
        // 获取Redis连接
        Jedis jedis = new Jedis("39.106.28.179", 6379);
        jedis.auth("Liuzong123456.");
        // 根据RelationShipData这个key获取所有数据
        List<String> relationShipData = jedis.lrange("RelationShipData", 0, -1);
        // 将relationShipData中的数据按照“-”分割，将数据源id存入Set集合中
        Set<String> dataSourceIdSets = new HashSet<>();
        for (String relationShipDatum : relationShipData) {
            if (relationShipDatum.contains("\"")) {
                relationShipDatum = relationShipDatum.replace("\"", "");
            }
            String[] split = relationShipDatum.split("-");
            dataSourceIdSets.add(split[0]);
        }
        // 根据数据源id获取数据源信息 包括表、字段以及字段信息
        Map<String, List<String>> dataSource = new HashMap<>();
        if (dataSourceIdSets.size() > 0) {
            for (String dataSourceIdSet : dataSourceIdSets) {
                // 根据数据源id获取数据对应信息   Map< 数据源id-数据表id-数据项id , 特征值 >
                dataSource = getDataSource(Integer.valueOf(dataSourceIdSet));
            }
        }

        // 将数据源信息存入Redis
        for (Map.Entry<String, List<String>> entry : dataSource.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();
            // 将 values 转换为数组以便存储
            String[] valuesArray = values.toArray(new String[0]);
            // 将 key-value 存入 Redis，使用 Redis 的列表（List）结构
            jedis.lpush(key, valuesArray);
        }
        // 关闭Redis连接
        jedis.close();
        return null;
    }

    /**
     * 根据数据源id获取此数据源下所有表、字段以及字段信息
     *
     * @param dataSourceId
     * @return
     */
    private Map<String, List<String>> getDataSource(Integer dataSourceId) {
        // 目前一个模型只能绑定一个系统，一个系统只能绑定一个数据源
        // 根据数据源id获取当前系统下的模型数据  包括表、字段以及字段信息
        // 获取数据源信息
        IkBpDataSourceBasic ikBpDataSourceBasic = ikBpDataSourceBasicMapper.selectDataSourceById(dataSourceId);
        // 正则表达式匹配规则
        Pattern pattern = Pattern.compile("^(.*?):(\\d+)/(.*?)\\?");
        Matcher matcher = pattern.matcher(ikBpDataSourceBasic.getUrl());
        Map<Map<String, String>, List<String>> tableColumnEigenvalue = new HashMap<>();

        if (matcher.find()) {
            Map<String, List<String>> tableColumnMap = new HashMap<>();
            // 获取主机地址
            String host = matcher.group(1);
            // 获取端口号
            String port = matcher.group(2);
            // 获取数据库名称
            String database = matcher.group(3);
            // 创建数据连接对象  todo  后期需要从库里直接获取数据
            // todo 密码解密
            SDsnaDsSettingNew sDsnaDsSettingNew = new SDsnaDsSettingNew();
            sDsnaDsSettingNew.setDatasourceType(ikBpDataSourceBasic.getDatabaseType());
            sDsnaDsSettingNew.setIp(host);
            sDsnaDsSettingNew.setPort(port);
            sDsnaDsSettingNew.setDatabaseName(database);
            sDsnaDsSettingNew.setDatasourceUser("root");
            sDsnaDsSettingNew.setDatasourcePass("Liuzong123456.");
            // 连接方式(0：服务名，1：SID )  当数据库类型为Oracle时，该属性才有值
            // SID (当数据源类型为Oracle且连接方式为SID时，该属性才有值)
            if (ikBpDataSourceBasic.getDatabaseType().equals("oracle")) {
                sDsnaDsSettingNew.setConnectionType(0);
                sDsnaDsSettingNew.setSid("");
            }
            // 根据不 数据库进行获取 所有表名称、表里有多少数据、字段名称、字段是不是主键、字段类型、字段长度、字段精度
            // 1. 根据不同数据库类型 编写不同的sql
            String sql = "";
            if (ikBpDataSourceBasic.getDatabaseType().equals("oracle")) {

            } else if (ikBpDataSourceBasic.getDatabaseType().equals("mysql")) {
                sql = "SELECT T.TABLE_NAME,T.TABLE_ROWS,C.COLUMN_KEY,C.COLUMN_NAME,C.DATA_TYPE,C.CHARACTER_MAXIMUM_LENGTH " +
                        "FROM INFORMATION_SCHEMA.TABLES T JOIN INFORMATION_SCHEMA.COLUMNS C ON T.TABLE_NAME = C.TABLE_NAME AND T.TABLE_SCHEMA = C.TABLE_SCHEMA " +
                        "WHERE T.TABLE_SCHEMA = '" + database + "' AND C.DATA_TYPE NOT IN ('decimal','date','datetime','time','boolean') AND C.COLUMN_KEY != 'PRI' AND  T.TABLE_ROWS > 0 ORDER BY T.TABLE_NAME";
            } else if (ikBpDataSourceBasic.getDatabaseType().equals("sqlserver")) {

            } else if (ikBpDataSourceBasic.getDatabaseType().equals("postgresql")) {

            } else if (ikBpDataSourceBasic.getDatabaseType().equals("db2")) {

            } else if (ikBpDataSourceBasic.getDatabaseType().equals("sqlite")) {

            } else if (ikBpDataSourceBasic.getDatabaseType().equals("h2")) {

            } else if (ikBpDataSourceBasic.getDatabaseType().equals("dm")) {

            } else if (ikBpDataSourceBasic.getDatabaseType().equals("kingbase")) {
            } else {
                System.out.println("数据库类型不正确，无法解析。");
            }

            Set<LinkedHashMap<String, Object>> linkedHashMaps = DataSourceUtil.selectSqlNew(sDsnaDsSettingNew, sql);

            for (LinkedHashMap<String, Object> linkedHashMap : linkedHashMaps) {
                int tableRows = 100;
                // 需要判断 具体有多少值 根据算法获取一个比例之后获取
                tableRows = calculateSampleSize(Integer.parseInt(linkedHashMap.get("TABLE_ROWS").toString()), 1.96, 0.04);

                List<String> randomSamples = getRandomSamples(
                        getEigenVale(sDsnaDsSettingNew, String.valueOf(linkedHashMap.get("TABLE_NAME")), String.valueOf(linkedHashMap.get("COLUMN_NAME"))), tableRows);

                List<Map<String, Object>> maps = ikBpDataSourceBasicMapper.selectTableIdAndColumnId(
                        ikBpDataSourceBasic.getDataSourceId(), linkedHashMap.get("TABLE_NAME").toString(), linkedHashMap.get("COLUMN_NAME").toString());
                String dataSourceIdRedis = "" + ikBpDataSourceBasic.getDataSourceId();

                if (maps.size() > 0 && randomSamples.size() > 0 ) {
                    tableColumnMap.put(dataSourceIdRedis + "-" + maps.get(0).get("dataTableId").toString() + "-" + maps.get(0).get("dataColumnId").toString(), randomSamples);
                }
//                    System.out.println(eigenVale);

            }
            return tableColumnMap;
        } else {
            System.out.println("URL格式不正确，无法解析。");
        }

        return null;
    }

//         getRedisData(tableColumnEigenvalue)


    /**
     * 获取特征值
     *
     * @param sDsnaDsSettingNew
     * @param tableName
     * @param columnName
     * @return
     */
    private List<String> getEigenVale(SDsnaDsSettingNew sDsnaDsSettingNew, String tableName, String columnName) {
        String sql = "SELECT  " + columnName + " FROM " + tableName + " where " + columnName + " is not null and " + columnName + " != '' GROUP BY " + columnName;
        List<LinkedHashMap<String, Object>> rs = DataSourceUtil.executeQuery(sDsnaDsSettingNew, sql);
        // 使用 ConcurrentHashMap 收集并行处理结果
        List<String> countList =
                // 使用并行流处理结果集
                rs.parallelStream()
                        .map(row -> {
                            try {
                                return row.get(columnName).toString();
                            } catch (Exception e) {
                                e.printStackTrace();
                                return null;
                            }
                        })
                        .filter(value -> value != null)
                        .collect(Collectors.toList());

        return countList;
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

}
