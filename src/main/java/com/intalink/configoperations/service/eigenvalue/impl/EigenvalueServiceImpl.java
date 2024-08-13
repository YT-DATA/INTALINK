package com.intalink.configoperations.service.eigenvalue.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.intalink.configoperations.domain.dataRelationShip.vo.DataItem;
import com.intalink.configoperations.domain.dataRelationShip.vo.DataTable;
import com.intalink.configoperations.domain.dataSource.IkBpDataSourceBasic;
import com.intalink.configoperations.domain.dataSource.SDsnaDsSettingNew;
import com.intalink.configoperations.mapper.dataSource.IkBpDataSourceBasicMapper;
import com.intalink.configoperations.service.eigenvalue.EigenvalueService;
import com.intalink.configoperations.utils.DESUtils;
import com.intalink.configoperations.utils.DataSourceUtil;
import com.intalink.configoperations.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

import java.lang.reflect.Type;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author whx
 * @date 2024/7/31
 */
@Service
public class EigenvalueServiceImpl implements EigenvalueService {

    @Autowired
    public IkBpDataSourceBasicMapper ikBpDataSourceBasicMapper;

    public Jedis jedis = RedisUtil.getJedis();

//    public Jedis getJedis() {
////        Jedis jedis = new Jedis("39.106.28.179", 6379);
//        Jedis jedis = new Jedis("localhost", 6379);
////        jedis.auth("Liuzong123456.");
//        return jedis;
//    }

    /**
     * 将指定数据源数据存入Redis
     */
    public void putData(List<Integer> dataSourceIds) {

//        Jedis jedis = getJedis();

        //  根据数据源id获取数据对应信息
        if (dataSourceIds != null && dataSourceIds.size() > 0) {
            // 删除这些数据源相关的信息
            deleteByDataSourceId(dataSourceIds, jedis);
            //将这些数据源Id进行字符串拼接存入Redis 对应的Key是 dataSource
            addDataSource(dataSourceIds, jedis);
            System.out.println("数据源信息存入Redis成功");
        }
        jedis.close();
    }

    /**
     * 将指定数据源数据存入Redis
     */
    public int putDataNew() {
        try {
//            Jedis jedis = getJedis();
            List<IkBpDataSourceBasic> ikBpDataSourceBasics = ikBpDataSourceBasicMapper.selectAll();
            List<Integer> dataSourceIds = ikBpDataSourceBasics.stream().map(IkBpDataSourceBasic::getDataSourceId).collect(Collectors.toList());

            //  根据数据源id获取数据对应信息
            if (dataSourceIds != null && dataSourceIds.size() > 0) {
                // 删除这些数据源相关的信息
                System.out.println("开始删除redis中相关的数据");
                deleteByDataSourceId(dataSourceIds, jedis);
                //将这些数据源Id进行字符串拼接存入Redis 对应的Key是 dataSource
                addDataSource(dataSourceIds, jedis);
                System.out.println("数据源信息存入Redis成功");
            }
            jedis.close();
            return 0;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 1;
    }

    /**
     * 第一层 根据数据源id插入到Redis中    dataSource：['dataSource-10','dataSource-11','dataSource-12']
     *
     * @param dataSourceIds
     * @param jedis
     */
    private void addDataSource(List<Integer> dataSourceIds, Jedis jedis) {
        for (Integer dataSourceId : dataSourceIds) {

            // 根据数据源id获取数据对应信息
            IkBpDataSourceBasic dataSource = getDataSource(dataSourceId);
            // 重新定义Value的格式
            if (dataSource != null && dataSource.getDataSourceId() != null) {
                String newDataSourceValue = "dataSource-" + dataSource.getDataSourceId();
                // 判断新数据源值是否存在
                boolean memberExists = jedis.sismember("dataSource", newDataSourceValue);
                // 如果新数据不存在，则插入新数据
                if (!memberExists) {
                    jedis.sadd("dataSource", newDataSourceValue);
                    //  第二层需要 数据源的Value  作为数据表信息的key  value  dataTable-  加上数据表的id
                    // 第二层是 数据表信息  key:value  String:List<String>    dataSource-21:[dataTable-10, dataTable-11]
                    // 添加第二层信息
                    // 根据数据源获取所有表和列的信息
                    List<Map<String, Object>> tableAndColomnByDataSourceId = getTableAndColomnByDataSourceId(dataSourceId);
                    addDataTable(newDataSourceValue, tableAndColomnByDataSourceId, jedis);
                }
            }
        }
    }

    /**
     * 第二层  根据数据源id获取数据源信息 并保存到Redis中   dataSource-10：[ 'dataTable-1','dataTable-2'   ...]
     *
     * @param newDataSourceValue
     * @param tableAndColomnByDataSourceId
     */
    private void addDataTable(String newDataSourceValue, List<Map<String, Object>> tableAndColomnByDataSourceId, Jedis jedis) {
        // 根据数据源获取所有表和列的信息
        Set<String> table = new HashSet<>();
        for (Map<String, Object> stringObjectMap : tableAndColomnByDataSourceId) {
//            System.out.println(stringObjectMap.get("data_table_id"));
            String newRedisTableValue = "dataTable-" + stringObjectMap.get("data_table_id").toString();
            boolean memberExists = jedis.sismember(newDataSourceValue, newRedisTableValue);
            if (!memberExists) {
                // 如果新成员不存在，则插入新成员
                jedis.sadd(newDataSourceValue, newRedisTableValue);
            }
            table.add(stringObjectMap.get("data_table_id").toString());
        }
        // 第三层是 数据项信息  dataTable-1：[{
        //                                   "dataSource"：“dataSource-10”，
        //       				             “dataItem”:[{
        //                                               "dataItem":"11-12-13"，
        //       		                            	  “dataType”：“varchar”，
        //       		                            	  “dataLength”：“255”，
        //       		                            	  “dataTable”：“dataTable-10”
        //                                               },
        //                                               {
        //                                               "dataItem":"11-12-14"，
        //       		                            	  “dataType”：“int”，
        //       		                            	  “dataLength”：“255”，
        //       		                                  “dataTable”：“dataTable-10”
        //                                             }]
        //                               }]

        addDataColumn(table, newDataSourceValue, jedis);
    }

    /**
     * 第三层  根据数据源id获取数据源信息 并保存到Redis中
     *
     * @param tableAndColomnByDataSourceData
     * @param newDataSourceValue
     * @param jedis
     */
    private void addDataColumn(Set<String> tableAndColomnByDataSourceData, String newDataSourceValue, Jedis jedis) {
        String[] split = newDataSourceValue.split("-");

        for (String tableId : tableAndColomnByDataSourceData) {
            List<DataItem> dataItems = new ArrayList<>();
            // 根据TableID获取所有列的信息
            List<Map<String, Object>> maps = ikBpDataSourceBasicMapper.selectByTableId(tableId);
            // "dataItem":"11-12-13"

            for (Map<String, Object> tableAndColomnByDataSourceDatum : maps) {
                DataItem dataItem = new DataItem();
                dataItem.setDataItem(split[1] + "-" + tableId + "-" + tableAndColomnByDataSourceDatum.get("data_column_id").toString());
                if (tableAndColomnByDataSourceDatum.get("data_type") != null && tableAndColomnByDataSourceDatum.get("data_type") != "") {
                    String[] split1 = tableAndColomnByDataSourceDatum.get("data_type").toString().split("\\(");
                    if (tableAndColomnByDataSourceDatum.get("data_type").toString().contains("(")) {
                        // “dataType”：“int”，
                        dataItem.setDataType(split1[0]);

                        //  “dataTypeLength”：“255”，
                        dataItem.setDataTypeLength(split1[1].split("\\)")[0]);
                    } else {
                        // “dataType”：“int”，
                        dataItem.setDataType(split1[0]);

                        //  “dataTypeLength”：“255”，
                        dataItem.setDataTypeLength("0");
                    }
                }
                // “dataTable”：“dataTable-10”
                dataItem.setDataTable("dataTable-" + tableAndColomnByDataSourceDatum.get("data_table_id").toString());

                // 添加第四层   Map< 数据源id-数据表id-数据项id , 特征值 >
                String s = addDataEigenvalue(dataItem.getDataItem(), jedis);
                dataItem.setDataNum(Integer.valueOf(s));
                dataItems.add(dataItem);
            }
            DataTable dataSourceRedis = new DataTable();
            dataSourceRedis.setDataSource(newDataSourceValue);
            dataSourceRedis.setDataItem(dataItems);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStr = null;
            try {
                jsonStr = objectMapper.writeValueAsString(dataSourceRedis);
                jedis.set("dataTable-" + maps.get(0).get("data_table_id").toString(), jsonStr);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        // 将对象转成String
    }


    /**
     * 第四层  Map< 数据源id-数据表id-数据项id , 特征值 >
     *
     * @param dataSourceDataTableDataColumn
     * @param jedis
     */
    private String addDataEigenvalue(String dataSourceDataTableDataColumn, Jedis jedis) {
        String[] split = dataSourceDataTableDataColumn.split("-");


        IkBpDataSourceBasic ikBpDataSourceBasic = getDataSource(Integer.valueOf(split[0]));

        // 获取新数据源信息
        SDsnaDsSettingNew sDsnaDsSettingNew = getSDsnaDsSettingNew(ikBpDataSourceBasic);

        // 根据数据表id和数据项id获取 数据表名和数据项名称
        List<Map<String, Object>> maps = ikBpDataSourceBasicMapper.selectDataByColumnId(split[2]);
        String tableName = "";
        String columnName = "";
        String rowNum = "";
        String eigenvalueNum = "0";
        if (maps.size() > 0) {
            tableName = maps.get(0).get("data_table_code").toString();
            columnName = maps.get(0).get("data_column_code").toString();
            rowNum = maps.get(0).get("data_length").toString();
            System.out.println(maps.get(0).get("data_type"));
            if (maps.get(0).get("data_type") != null && maps.get(0).get("data_type") != "") {
                // 'decimal','date','datetime','time','boolean'
                if (!"decimal".equals(maps.get(0).get("data_type")) &&
                        !"date".equals(maps.get(0).get("data_type")) &&
                        !"datetime".equals(maps.get(0).get("data_type")) &&
                        !"time".equals(maps.get(0).get("data_type")) &&
                        !"boolean".equals(maps.get(0).get("data_type")) &&
                        !"timestamp".equals(maps.get(0).get("data_type")) &&
                        !"int".equals(maps.get(0).get("data_type")) &&
                        !"int".equals(maps.get(0).get("data_type")) &&
                        !maps.get(0).get("data_type").toString().contains("tinyint")
                ) {

                    // 如果rowNum等于0的话，则获取这个表的总数量
                    if (rowNum.equals("0")) {
                        Set<LinkedHashMap<String, Object>> dataByIkBpDataSourceBasic = getDataByIkBpDataSourceBasic(ikBpDataSourceBasic, tableName, columnName);
                        for (LinkedHashMap<String, Object> stringObjectLinkedHashMap : dataByIkBpDataSourceBasic) {
                            if (stringObjectLinkedHashMap.get("TABLE_ROWS") != null)
                                rowNum = stringObjectLinkedHashMap.get("TABLE_ROWS").toString();
                        }
                    }
                    // 根据数据源id获取数据对应信息   Map< 数据源id-数据表id-数据项id , 特征值 >
                    for (Map.Entry<String, List<String>> entry : getEigenvalueKeyNew(rowNum, tableName, columnName, sDsnaDsSettingNew, ikBpDataSourceBasic).entrySet()) {
                        String key = entry.getKey();
                        List<String> values = entry.getValue();
                        // 将 values 转换为数组以便存储
                        String[] valuesArray = values.toArray(new String[0]);
                        eigenvalueNum = String.valueOf(valuesArray.length);
                        // 将 key-value 存入 Redis，使用 Redis 的列表（List）结构
                        jedis.lpush(key, valuesArray);
                    }
                }

            }

        }
        return eigenvalueNum;
    }

    /**
     * 根据数据源id、所获取的数据源下所有表、字段以及字段信息获取所有的表和项的id
     *
     * @return
     */
    private List<Map<String, Object>> getTableAndColomnByDataSourceId(Integer dataSourceId) {
        List<Map<String, Object>> maps = ikBpDataSourceBasicMapper.selectOneData(dataSourceId);
        return maps;
    }

    /**
     * 根据数据源id删除Redis中对应的数据
     *
     * @param dataSourceId
     * @param jedis
     */
    private void deleteByDataSourceId(List<Integer> dataSourceId, Jedis jedis) {
        // 将传递的dataSourceId转换为字符串数组
        String[] elementsToDelete = dataSourceId.stream()
                .map(id -> "dataSource-" + id)
                .toArray(String[]::new);
        try {

            for (String element : elementsToDelete) {
                deleteRedisDataByDataSourceKey(jedis, element);
            }
        } catch (JedisDataException e) {
            System.out.println("redis删除数据失败错误信息：" + e);
            System.out.println("删除Redis数据失败");
        }
    }

    /**
     * 删除第一层相应的数据源信息
     *
     * @param jedis
     * @param dataSource
     */
    private void deleteRedisDataByDataSourceKey(Jedis jedis, String dataSource) {
        // 删除第一层的数据源   dataSource = dataSource-XXX
        jedis.srem("dataSource", dataSource);
        // 递归删除第二层及其以下的数据
        deleteDataTable(jedis, dataSource);
    }

    /**
     * 删除第二层数据源对应的数据表信息
     *
     * @param jedis
     * @param dataSource
     */
    private void deleteDataTable(Jedis jedis, String dataSource) {
        // 获取第二层的所有数据表名称
        Set<String> dataTables = jedis.smembers(dataSource);

        for (String dataTable : dataTables) {
            // 删除第二层的数据表
            jedis.srem(dataSource, dataTable);

            // 递归删除第三层及其以下的数据
            deleteDataColumns(jedis, dataTable);
        }
    }

    /**
     * 删除第三层数据表的数据项信息
     *
     * @param jedis
     * @param dataTable
     */
    private void deleteDataColumns(Jedis jedis, String dataTable) {
        // 获取第三层的所有数据项名称
        String redisDataByRedisKey = getRedisDataByRedisKey(jedis, dataTable);
        if (redisDataByRedisKey != null && redisDataByRedisKey.length() > 0 && !"Unsupported data type".equals(redisDataByRedisKey)) {
            // 创建 JsonParser 对象
            JsonParser parser = new JsonParser();
            // 解析 JSON 字符串
            JsonElement jsonElement = parser.parse(redisDataByRedisKey);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            Gson gson = new Gson();
            Type type = new TypeToken<List<Map<String, Object>>>() {
            }.getType();
            List<Map<String, Object>> dataList = gson.fromJson(jsonObject.get("dataItem"), type);

            List<String> dataItems = new ArrayList<>();
            for (Map<String, Object> item : dataList) {
                //  删除第五层数据项中dataItemValue为key对应的数据
                deleteEigenvalue(jedis, (String) item.get("dataItem"));
            }
            jedis.del(dataTable);
        }
    }

    /**
     * 根据rediskey的类型获取数据
     */
    private String getRedisDataByRedisKey(Jedis jedis, String redisKey) {
        try {
            // 检查数据类型
            String type = jedis.type(redisKey);
            switch (type) {
                case "string":
                    return jedis.get(redisKey);
                case "list":
                    return jedis.lrange(redisKey, 0, -1).toString();
                case "set":
                    return jedis.smembers(redisKey).toString();
                case "zset":
                    return jedis.zrange(redisKey, 0, -1).toString();
                case "hash":
                    return jedis.hgetAll(redisKey).toString();
                default:
                    return "Unsupported data type";
            }
        } catch (JedisDataException e) {
            // 捕获异常并处理
            return "Error: " + e.getMessage();
        }
    }

    /**
     * 删除第五层数据项中dataItemValue为key对应的数据
     *
     * @param jedis
     * @param dataItemValue
     */
    private void deleteEigenvalue(Jedis jedis, String dataItemValue) {
        // 直接根据dataItemValue删除第五层数据
        jedis.del(dataItemValue);
    }

    /**
     * 根据数据源id获取对应信息
     *
     * @param dataSourceId
     * @return
     */
    private IkBpDataSourceBasic getDataSource(Integer dataSourceId) {
        return ikBpDataSourceBasicMapper.selectDataSourceById(dataSourceId);
    }

    /**
     * 根据数据源信息获取新的数据源信息
     */
    private SDsnaDsSettingNew getSDsnaDsSettingNew(IkBpDataSourceBasic ikBpDataSourceBasic) {
        // 正则表达式匹配规则 获取对应的数据  ip  端口  数据库名称
        Pattern pattern = Pattern.compile("^(.*?):(\\d+)/(.*?)\\?");
        Matcher matcher = pattern.matcher(ikBpDataSourceBasic.getUrl());

        if (matcher.find()) {
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
//            sDsnaDsSettingNew.setDatasourceUser("root");
//            sDsnaDsSettingNew.setDatasourcePass("Liuzong123456.");
            sDsnaDsSettingNew.setDatasourceUser(ikBpDataSourceBasic.getUserName());
            sDsnaDsSettingNew.setDatasourcePass(DESUtils.decrypt(ikBpDataSourceBasic.getPassword()));
            // 连接方式(0：服务名，1：SID )  当数据库类型为Oracle时，该属性才有值
            // SID (当数据源类型为Oracle且连接方式为SID时，该属性才有值)
            if (ikBpDataSourceBasic.getDatabaseType().equals("oracle")) {
                sDsnaDsSettingNew.setConnectionType(0);
                sDsnaDsSettingNew.setSid("");
            }
            return sDsnaDsSettingNew;
        } else {
            System.out.println("URL格式不正确，无法解析。");
            return null;
        }
    }

    /**
     * 根据数据源id获取此数据源下所有表、字段以及字段信息
     *
     * @param ikBpDataSourceBasic
     * @return
     */
    private Set<LinkedHashMap<String, Object>> getDataByIkBpDataSourceBasic(IkBpDataSourceBasic ikBpDataSourceBasic,String tableName,String columnName) {
        SDsnaDsSettingNew sDsnaDsSettingNew = getSDsnaDsSettingNew(ikBpDataSourceBasic);
        // 根据不 数据库进行获取 所有表名称、表里有多少数据、字段名称、字段是不是主键、字段类型、字段长度、字段精度
        // 根据不同数据库类型 编写不同的sql
        String sql = "";
        if (ikBpDataSourceBasic.getDatabaseType().equals("oracle")) {

        } else if (ikBpDataSourceBasic.getDatabaseType().equals("mysql")) {
            sql = "SELECT T.TABLE_NAME,T.TABLE_ROWS,C.COLUMN_KEY,C.COLUMN_NAME,C.DATA_TYPE,C.CHARACTER_MAXIMUM_LENGTH " +
                    "FROM INFORMATION_SCHEMA.TABLES T JOIN INFORMATION_SCHEMA.COLUMNS C ON T.TABLE_NAME = C.TABLE_NAME AND T.TABLE_SCHEMA = C.TABLE_SCHEMA " +
                    "WHERE T.TABLE_SCHEMA = '" + sDsnaDsSettingNew.getDatabaseName() + "' AND T.TABLE_NAME = '"+ tableName +"' AND C.COLUMN_NAME = '"+ columnName +"' AND C.DATA_TYPE NOT IN ('decimal','date','datetime','time','boolean') AND C.COLUMN_KEY != 'PRI' AND  T.TABLE_ROWS > 0 ORDER BY T.TABLE_NAME";
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

        //  根据sql获取数据
        return DataSourceUtil.selectSqlNew(sDsnaDsSettingNew, sql);

    }


    /**
     * 根据TABLE_NAME和COLUMN_NAME获取对应的Id
     *
     * @param dataSourceId
     * @param tableName
     * @param columnName
     * @return
     */
    private List<Map<String, Object>> getTableColumnEigenvalue(int dataSourceId, String tableName, String columnName) {
        List<Map<String, Object>> maps = ikBpDataSourceBasicMapper.selectTableIdAndColumnId(dataSourceId, tableName, columnName);
//        System.out.println(maps);
        return maps;
    }

    /**
     * 获取特征值
     *
     * @param linkedHashMaps
     * @param sDsnaDsSettingNew
     * @param ikBpDataSourceBasic
     * @return
     */
    private Map<String, List<String>> getEigenvalueKey(Set<LinkedHashMap<String, Object>> linkedHashMaps, SDsnaDsSettingNew sDsnaDsSettingNew, IkBpDataSourceBasic ikBpDataSourceBasic) {
        Map<String, List<String>> tableColumnMap = new HashMap<>();
        for (LinkedHashMap<String, Object> linkedHashMap : linkedHashMaps) {
            int tableRows = 100;
            // 需要判断 具体有多少值 根据算法获取一个比例之后获取
            tableRows = calculateSampleSize(Integer.parseInt(linkedHashMap.get("TABLE_ROWS").toString()), 1.96, 0.04);

            // 根据特征值获取随机样本
            List<String> randomSamples = getRandomSamples(
                    getEigenVale(sDsnaDsSettingNew, String.valueOf(linkedHashMap.get("TABLE_NAME")), String.valueOf(linkedHashMap.get("COLUMN_NAME"))), tableRows);

            // 根据表名称、字段名称获取dataTableId、dataColumnId
            List<Map<String, Object>> maps = getTableColumnEigenvalue(ikBpDataSourceBasic.getDataSourceId(), linkedHashMap.get("TABLE_NAME").toString(), linkedHashMap.get("COLUMN_NAME").toString());

            // 获取数据源Id
            String dataSourceIdRedis = "" + ikBpDataSourceBasic.getDataSourceId();

            // 将数据源-数据表-数据项:特征值 存储到map中返回
            if (maps.size() > 0 && randomSamples.size() > 0) {
                tableColumnMap.put(dataSourceIdRedis + "-" + maps.get(0).get("dataTableId").toString() + "-" + maps.get(0).get("dataColumnId").toString(), randomSamples);
            }

        }
        return tableColumnMap;
    }

    /**
     * 获取特征值2
     *
     * @param rowNum
     * @param tableName
     * @param columnName
     * @param sDsnaDsSettingNew
     * @param ikBpDataSourceBasic
     * @return
     */
    private Map<String, List<String>> getEigenvalueKeyNew(String rowNum, String tableName, String columnName, SDsnaDsSettingNew sDsnaDsSettingNew, IkBpDataSourceBasic ikBpDataSourceBasic) {
        Map<String, List<String>> tableColumnMap = new HashMap<>();
        int tableRows = 100;
        // 需要判断 具体有多少值 根据算法获取一个比例之后获取
        tableRows = calculateSampleSize(Integer.parseInt(rowNum), 1.96, 0.04);

        // 根据特征值获取随机样本
        List<String> randomSamples = getRandomSamples(
                getEigenVale(sDsnaDsSettingNew, tableName, columnName), tableRows);

        // 根据表名称、字段名称获取dataTableId、dataColumnId
        List<Map<String, Object>> maps = getTableColumnEigenvalue(ikBpDataSourceBasic.getDataSourceId(), tableName, columnName);

        // 获取数据源Id
        String dataSourceIdRedis = "" + ikBpDataSourceBasic.getDataSourceId();

        // 将数据源-数据表-数据项:特征值 存储到map中返回
        if (maps.size() > 0 && randomSamples.size() > 0) {
            tableColumnMap.put(dataSourceIdRedis + "-" + maps.get(0).get("dataTableId").toString() + "-" + maps.get(0).get("dataColumnId").toString(), randomSamples);
        }
        return tableColumnMap;
    }


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
