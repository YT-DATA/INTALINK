package com.intalink.configoperations.service.dataTableRelationBasic.impl;

import com.intalink.configoperations.domain.dataTableRelationBasic.EigenvalueSuccessLinkEntity;
import com.intalink.configoperations.mapper.dataTableRelationBasic.IkBpDataTableRelationBasicMapper;
import com.intalink.configoperations.service.dataTableRelationBasic.IkRpDataTableRelationBasicService;
import com.intalink.configoperations.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author whx
 */
@Service
public class IkRpDataTableRelationBasicServiceImpl implements IkRpDataTableRelationBasicService {
    private static final String SUCCESS_KEY = "relationSuccess";//对比成功数据存放的key
    private static final String FAIL_KEY = "relationFail";//对比失败数据存放的key

    @Resource
    IkBpDataTableRelationBasicMapper ikBpDataTableRelationBasicMapper;

    public static void main(String[] args) {
        IkRpDataTableRelationBasicServiceImpl test = new IkRpDataTableRelationBasicServiceImpl();
//        test.setComparisonFlag("1","2",true);
//        test.setComparisonFlag("1","3",true);
//        test.setComparisonFlag("1","4",false);
//        test.setComparisonFlag("1","5",false);
        test.setComparisonFlag("2", "1", true);
        test.setComparisonFlag("2", "3", true);
        test.setComparisonFlag("2", "4", false);
        test.setComparisonFlag("2", "5", false);
//        test.setComparisonFlag("3","1",true);
//        test.setComparisonFlag("3","2",true);
//        test.setComparisonFlag("3","4",false);
//        test.setComparisonFlag("3","5",false);
//        test.setComparisonFlag("4","1",false);
//        test.setComparisonFlag("4","2",false);
//        test.setComparisonFlag("4","3",false);
//        test.setComparisonFlag("4","5",true);
//        test.setComparisonFlag("5","1",false);
//        test.setComparisonFlag("5","2",false);
//        test.setComparisonFlag("5","3",false);
//        test.setComparisonFlag("5","4",true);

    }

    /**
     * 判断两个字段是否需要比对
     *
     * @param columnKey           当前对比字段,如: 1-1-1(数据源ID-数据表ID-数据项ID)
     * @param comparisonColumnKey 对比目标字段,如: 1-1-1(数据源ID-数据表ID-数据项ID)
     * @return
     */
    public boolean getIsComparisonFlag(String columnKey, String comparisonColumnKey) {
        RedisUtil redisUtil = new RedisUtil();
        String failNodeKey = columnKey + "-FAIL";
        //   查看是否有F根节点,没有则创建
        createSuccessAndFailRelationKey(redisUtil);
        //   查看是否有Fail节点,没有则创建
        if (!redisUtil.isListExists(failNodeKey)) {// 检查key是否存在,如不存在则创建新list节点
            // 添加节点
            redisUtil.addListNode(FAIL_KEY, failNodeKey);
        }
        //查看该节点FAIL中是否包含comparisonColumnKey
        if (redisUtil.isValueInList(failNodeKey, comparisonColumnKey)) {
            return false;//包含则代表已经比对失败不需要比对
        } else {
            //查看columnKey值在SUCCESS中是否包含
            String successNodeKey = redisUtil.findKeyByValue(columnKey, SUCCESS_KEY);
            if (successNodeKey == null) {
                return true;//不包含则比对
            } else {
                //包含则不比对
                return false;
            }
        }
    }

    /**
     * 设置比对成功与失败List
     *
     * @param columnKey           当前对比字段,如: 1-1-1(数据源ID-数据表ID-数据项ID)
     * @param comparisonColumnKey 对比目标字段,如: 1-1-1(数据源ID-数据表ID-数据项ID)
     * @param flag                比对结果,比对成功为true,比对失败为false
     */
    public void setComparisonFlag(String columnKey, String comparisonColumnKey, boolean flag) {
        RedisUtil redisUtil = new RedisUtil();
        String failNodeKey = columnKey + "-FAIL";
        String successNodeKey = columnKey + "-SUCCESS";
        //   查看是否有根节点,没有则创建
        createSuccessAndFailRelationKey(redisUtil);
        //需增加的Fail值的LIST
        List<String> addFailList = new ArrayList<>();

        //需增加Success值的LIST
        List<String> addSuccessList = new ArrayList<>();
        if (flag) {//比对成功逻辑
            //  查看对比成功的节点,是否与其他节点对比成功过
            successNodeKey = redisUtil.findKeyByValue(comparisonColumnKey, SUCCESS_KEY);
            addSuccessList.add(columnKey);
            if (successNodeKey == null) {//如果不存在,则创建节点
                successNodeKey = columnKey + "-SUCCESS";
                // 添加节点
                redisUtil.addListNode(SUCCESS_KEY, successNodeKey);
                // 增加数据
                addSuccessList.add(comparisonColumnKey);
            }

            /** 查看comparisonColumnKey是否有比对失败的点,这些点之后都不需要与columnKey进行比对 */

            if (redisUtil.isListExists(comparisonColumnKey + "-FAIL")) {// 检查key是否存在,如不存在则不需操作
                //查询该节点下所有数据,这些数据之后都不需要与columnKey进行比对
                addFailList = redisUtil.fetchAllFromList(comparisonColumnKey + "-FAIL");
            }
            /** 删除特征值KEY  */
            //获取对比目标字段成功池key
            String comparisonColumnSuccessKey = redisUtil.findKeyByValue(comparisonColumnKey, SUCCESS_KEY);
            String comparisonColumnEigenvalueKey = comparisonColumnKey;//对比目标字段特征值key
            String columnEigenvalueKey = columnKey;//当前字段特征值key
            boolean addOrUpdateFlag = false;//新增或修改成功池与特征值关联表数据判断   默认新增
            if(comparisonColumnSuccessKey!=null){//如果不为空,需要去库里查询该成功池对应的特征值的key
               comparisonColumnEigenvalueKey = ikBpDataTableRelationBasicMapper.searchEigenvalueSuccessLink(comparisonColumnSuccessKey).getEigenvalueKey();
                addOrUpdateFlag=true;
            }
            EigenvalueSuccessLinkEntity eigenvalueSuccessLinkEntity = new EigenvalueSuccessLinkEntity();
            eigenvalueSuccessLinkEntity.setSuccessKey(successNodeKey);
            //查看哪个特征值中内容更多
            if(redisUtil.fetchAllFromList(comparisonColumnEigenvalueKey).size() < redisUtil.fetchAllFromList(columnEigenvalueKey).size()){
                //删除特征值较少的那个key
                redisUtil.removeRedisNode(comparisonColumnEigenvalueKey);
                eigenvalueSuccessLinkEntity.setEigenvalueKey(columnEigenvalueKey);
                if(addOrUpdateFlag){//修改成功池与特征值关联表数据
                    ikBpDataTableRelationBasicMapper.updateEigenvalueSuccessLink(eigenvalueSuccessLinkEntity);
                }else{//新增修改成功池与特征值关联表数据
                    ikBpDataTableRelationBasicMapper.insertEigenvalueSuccessLink(eigenvalueSuccessLinkEntity);
                }
            }else{
                //删除特征值较少的那个key
                redisUtil.removeRedisNode(columnEigenvalueKey);
                eigenvalueSuccessLinkEntity.setEigenvalueKey(comparisonColumnEigenvalueKey);
                if(!addOrUpdateFlag){//新增修改成功池与特征值关联表数据
                    ikBpDataTableRelationBasicMapper.insertEigenvalueSuccessLink(eigenvalueSuccessLinkEntity);
                }
            }
        } else {
            // 查看comparisonColumnKey是否有比对成功的点,并查询出来
            String searchFailNode = redisUtil.findKeyByValue(comparisonColumnKey, SUCCESS_KEY);
            if (searchFailNode != null) {
                //查询该节点下所有数据,这些数据之后都不需要与columnKey进行比对
                addFailList = redisUtil.fetchAllFromList(searchFailNode);
            } else {
                addFailList.add(comparisonColumnKey);
            }
            /** 在comparisonColumnKey的失败池中也加入columnKey----------开始---------*/
            //   查看是否有Fail节点,没有则创建
            if (!redisUtil.isListExists(comparisonColumnKey + "-FAIL")) {// 检查key是否存在,如不存在则创建新list节点
                // 添加节点
                redisUtil.addListNode(FAIL_KEY, comparisonColumnKey + "-FAIL");
            }
            List<String> otherNodeFile = new ArrayList<String>();
            otherNodeFile.add(columnKey);
            redisUtil.batchInsertToList(comparisonColumnKey + "-FAIL", otherNodeFile);
            /** 在comparisonColumnKey的失败池中也加入columnKey----------结束---------*/
        }
        if (addFailList.size() > 0) {
            //将查询出来的节点添加到Fail节点中
            //  添加比对失败的节点值
            redisUtil.batchInsertToList(failNodeKey, addFailList);
        }
        if (addSuccessList.size() > 0) {
            //  添加比对成功的节点值
            redisUtil.batchInsertToList(successNodeKey, addSuccessList);
        }
    }

    @Override
    public String getEigenvalueBySourceTableColumn(String columnKey) {
        RedisUtil redisUtil = new RedisUtil();
        //获取查询字段成功池key
        String successKey = redisUtil.findKeyByValue(columnKey, SUCCESS_KEY);
        if (successKey != null) {
            //如果不为空,需要去库里查询该成功池对应的特征值的key
            String comparisonColumnEigenvalueKey = ikBpDataTableRelationBasicMapper.searchEigenvalueSuccessLink(successKey).getEigenvalueKey();
            if(comparisonColumnEigenvalueKey != null && !"".equals(comparisonColumnEigenvalueKey)){
                return comparisonColumnEigenvalueKey;
            }else{
                return null;
            }
        }
        return null;
    }


    /**
     * 为比对成功与失败集合创建初始化节点
     *
     * @param redisUtil
     */
    public void createSuccessAndFailRelationKey(RedisUtil redisUtil) {
        if (!redisUtil.isListExists(SUCCESS_KEY)) {// 检查key是否存在,如不存在则创建新list节点
            // 添加根节点
            redisUtil.addListNode(null, SUCCESS_KEY);
        }
        if (!redisUtil.isListExists(FAIL_KEY)) {// 检查key是否存在,如不存在则创建新list节点
            // 添加根节点
            redisUtil.addListNode(null, FAIL_KEY);
        }
    }
}




