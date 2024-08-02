package com.intalink.configoperations.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;

public class ResultUtils {


    /**
     * list 转XML
     * @param list
     * @return
     * @throws Exception
     */
    public String convertListToXml(List<?> list) throws Exception {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // 创建XML文档
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("list");
        doc.appendChild(rootElement);

        // 遍历列表并添加到XML
        for (Object item : list) {
            Element itemElement = doc.createElement("item");
            rootElement.appendChild(itemElement);

            // 根据对象类型设置文本内容
            if (item instanceof String) {
                itemElement.appendChild(doc.createTextNode((String) item));
            } else if (item instanceof Integer) {
                itemElement.appendChild(doc.createTextNode(item.toString()));
            } else if (item instanceof Double) {
                itemElement.appendChild(doc.createTextNode(item.toString()));
            } else if (item instanceof Float) {
                itemElement.appendChild(doc.createTextNode(item.toString()));
            } else if (item instanceof Long) {
                itemElement.appendChild(doc.createTextNode(item.toString()));
            } else if (item instanceof Short) {
                itemElement.appendChild(doc.createTextNode(item.toString()));
            } else if (item instanceof Byte) {
                itemElement.appendChild(doc.createTextNode(item.toString()));
            } else if (item instanceof Character) {
                itemElement.appendChild(doc.createTextNode(item.toString()));
            } else if (item instanceof Boolean) {
                itemElement.appendChild(doc.createTextNode(item.toString()));
            } else if (item instanceof Map) {
                // 处理Map类型的元素
                Map<?, ?> map = (Map<?, ?>) item;
                for (Map.Entry<?, ?> entry : map.entrySet()) {
                    Element mapItemElement = doc.createElement("map-item");
                    itemElement.appendChild(mapItemElement);
                    Element keyElement = doc.createElement("key");
                    keyElement.appendChild(doc.createTextNode(entry.getKey().toString()));
                    mapItemElement.appendChild(keyElement);
                    Element valueElement = doc.createElement("value");
                    valueElement.appendChild(doc.createTextNode(entry.getValue().toString()));
                    mapItemElement.appendChild(valueElement);
                }
            } else {
                // 对于其他复杂类型，可以添加更多的条件或使用toString()方法
                itemElement.appendChild(doc.createTextNode(item.toString()));
            }
        }

        // 将Document转换为XML字符串
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(String.valueOf((new StringBuilder()).append("")));
        transformer.transform(source, result);

        return result.getWriter().toString();
    }


    /**
     * list 转json
     * @param list
     * @return
     */
    public static String convertListToJson(List<?> list) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(list);
        } catch (Exception e) {
            throw new RuntimeException("Error converting list to JSON", e);
        }
    }

}
