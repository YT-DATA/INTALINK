package com.intalink.configoperations.service.findTableAddress.impl;


import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.intalink.configoperations.mapper.findTableAddress.FindTableAddressMapper;
import com.intalink.configoperations.service.findTableAddress.FindTableAddressService;
import com.intalink.configoperations.utils.FindTableAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FindTableAddressServiceImpl implements FindTableAddressService {
    @Autowired
    FindTableAddressMapper findTableAddressMapper;

    @Override
    public JSONArray paths(String[] columns) {
        //找到所有列对应的表ID
        String[] tables = new String[columns.length];
        for(int i = 0;i<columns.length;i++){
            tables[i] = findTableAddressMapper.selectTableByColumnId(columns[i]);
        }

        //所有关系加上关系中的表达式
        List<Map<String, Object>> datas =  findTableAddressMapper.findTableRelationList();
        //获取路径的工具类
        FindTableAddress findTableAddress = new FindTableAddress();
        //存放所有表达式的..
        Map<String,String> regExpMap = new HashMap<String,String>();
        // 添加边
        for(int i = 0; i < datas.size(); i++){
            Map<String, Object> map = datas.get(i);
            regExpMap.put(""+map.get("mainTable")+"|"+map.get("relationTable"),(String)map.get("merged_relations"));
            findTableAddress.addEdge(map.get("mainTable")+"", map.get("relationTable")+"");
        }

        // 导出图为图片
//        findTableAddress.exportGraphToImage("graph.png");
        //获取所有路径
        JSONArray paths = findTableAddress.findAllPaths(tables);
        JSONArray addresss = new JSONArray();
        if(paths!=null && paths.size()>0){
            for (int i=0;i<paths.size();i++) {
                JSONArray path = paths.getJSONArray(i);
                JSONArray address = new JSONArray();
                //重构路径,插入表达式
                for(int o=0;o<path.size();o++){
                    JSONObject addressRelation = path.getJSONObject(o);
                    String key = addressRelation.getString("before")+"|"+addressRelation.getString("after");
                    String key1 = addressRelation.getString("after")+"|"+addressRelation.getString("before");
                    if(regExpMap.get(key)!=null){
                        addressRelation.put("MainTable",addressRelation.getString("before"));
                        addressRelation.put("RelationTable",addressRelation.getString("after"));
                        addressRelation.put("relation",regExpMap.get(key));
                        // TODO 处理表间关系

                    }else if(regExpMap.get(key1)!=null){
                        addressRelation.put("MainTable",addressRelation.getString("after"));
                        addressRelation.put("RelationTable",addressRelation.getString("before"));
                        addressRelation.put("relation",regExpMap.get(key1));
                    }
                    address.add(addressRelation);
                    //TODO 向表关系表中增加一条关系
                }

                //TODO 新增
                findTableAddressMapper.insertLinkResult(address.toJSONString());
                addresss.add(address);
            }
        }

        return addresss;
    }
}
