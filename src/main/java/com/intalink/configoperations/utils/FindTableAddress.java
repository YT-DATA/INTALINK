package com.intalink.configoperations.utils;


import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static guru.nidi.graphviz.model.Factory.*;

public class FindTableAddress {


    private Map<String, List<String>> graph = new HashMap<>();

    public void addEdge(String source, String destination) {
        graph.computeIfAbsent(source, k -> new ArrayList<>()).add(destination);
        graph.computeIfAbsent(destination, k -> new ArrayList<>()).add(source); // 无向图，所以也要添加反向边
    }

    /**
     *  查找全部路径
     * @param tables  要找的所有表
     * @return
     */
    public JSONArray findAllPaths(String[] tables) {
        JSONArray paths = new JSONArray();
        for (String start : tables){
            findPathsRecursive(start, tables, new JSONArray(), paths,null,new ArrayList<>());
        }
        return paths;
    }

    private void findPathsRecursive(String current, String[] tables, JSONArray path, JSONArray paths,String before,List<String> pathList) {
        if(before!=null){
            JSONObject tableRelation = new JSONObject();
            tableRelation.put("before",before);
            tableRelation.put("after",current);
            path.add(tableRelation);
        }else{
            JSONObject tableRelation = new JSONObject();
            tableRelation.put("before",null);
            tableRelation.put("after",current);
            path.add(tableRelation);
        }
        pathList.add(current);
        if (pathList.containsAll(Arrays.asList(tables))) {
            paths.add(new JSONArray(path));
        } else if (graph.containsKey(current)) {
            for (String next : graph.get(current)) {
                if (!pathList.contains(next)) { // 避免循环
                    findPathsRecursive(next, tables, path, paths,current,pathList);
                }
            }
        }
        path.remove(path.size() - 1); // 回溯
        pathList.remove(pathList.size() - 1); // 回溯
    }

    /**
     * 图片保存本地
     * @param filename
     */
    public void exportGraphToImage(String filename) {
        MutableGraph g = mutGraph("example").setDirected(true);
        graph.forEach((node, edges) -> {
            MutableNode graphNode = mutNode(node);
            edges.forEach(edge -> graphNode.addLink(to(mutNode(edge)).with(Color.RED)));
            g.add(graphNode);
        });

        try {
            // 导出图片   当前默认导出到E盘
            Graphviz.fromGraph(g).render(Format.PNG).toFile(new File("E:\\" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
//        List<Map<String, String>> datas = getData();
//        FindTableAddress finder = new FindTableAddress();
//        // 添加边
//        for(int i = 0; i < datas.size(); i++){
//            Map<String, String> map = datas.get(i);
//            finder.addEdge(map.get("col1"), map.get("col2"));
//        }
//
//        // 导出图为图片
//        finder.exportGraphToImage("graph.png");
//        List<List<String>> paths = finder.findAllPaths("1", "26");
//        for (List<String> path : paths) {
//            System.out.println(String.join(" -> ", path));
//        }
    }



}
