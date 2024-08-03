package com.intalink.configoperations.service.dataTableRP.impl;

import com.intalink.configoperations.domain.pdm.*;
import com.intalink.configoperations.mapper.dataColumn.IkBpDataColumnBasicMapper;
import com.intalink.configoperations.mapper.dataTable.IkBpDataTableBasicMapper;
import com.intalink.configoperations.service.dataTableRP.IHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

import java.util.List;

@Service
public class IHandleServiceImpl implements IHandleService {

    @Autowired
    private IkBpDataTableBasicMapper ikBpDataTableBasicMapper;

    @Autowired
    private IkBpDataColumnBasicMapper ikBpDataColumnBasicMapper;

    @Transactional
    public int tableRpFromPdm(InputStream pdmStream, String modelId) throws Exception {
        PdmModel<OPackage, PdmTable> pdmModel = this.getPdmModel(pdmStream, modelId);
        List<OPackage> allPkgList = new ArrayList();
        if (pdmModel.getcPackages() != null) {
            allPkgList.addAll(pdmModel.getcPackages());
            Iterator var5 = pdmModel.getcPackages().iterator();

            while (var5.hasNext()) {
                OPackage pkg = (OPackage) var5.next();
                List<OPackage> childPackages = this.getAllChildPackages(pkg);
                if (childPackages != null) {
                    allPkgList.addAll(childPackages);
                }
            }
        }

        List<PdmTable> allTable = pdmModel.getcTables();
        List<OColumn> allColumns = new ArrayList();
        Map<Map<String, String>, Map<String, String>> allOReferences = new HashMap<>();
        if (!allPkgList.isEmpty()) {
            this.sortPackages(allPkgList);
        }

        if (allTable != null && !allTable.isEmpty()) {
            allTable.addAll(this.getAllChildTables(allPkgList));
        } else {
            allTable = this.getAllChildTables(allPkgList);
        }

        if (!allTable.isEmpty()) {
            allColumns = this.getAllChildColumns(allTable);
            allOReferences = this.getAllOReferences(allTable);
        }

        System.out.println(allTable);
        System.out.println(allColumns);
        System.out.println(allOReferences);

        return 1;
    }

    @Override
    public int tableRpFromLdm(InputStream ldmStream, String modelId) throws Exception {
        String ldmContent = convertInputStreamToString(ldmStream);
        String htmlContent = convertLDMToHTML(ldmContent);
        Source source = new Source(htmlContent);
        List<Element> elements = source.getAllElements("o:Relationship"); // 获取所有 <o:Relationship> 标签
        for (Element element : elements) {
            if (element.toString() != null) {
                // 不为空则继续进行获取标签值

                // 调试输出 <o:Relationship> 元素内容
                System.out.println("Relationship Element: " + element.toString());
            }
            System.out.println("数据是空");
        }
        return 1;
    }

    // 将InputStream转换为字符串
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }

    // 将.LDM内容转换为HTML
    private static String convertLDMToHTML(String ldmContent) {
        // 这里可以编写具体的转换逻辑，将.LDM内容转换为HTML
        // 这里只是简单示例，假设直接将.LDM内容包裹在HTML的<pre>标签中
        return "<html><body><pre>" + ldmContent + "</pre></body></html>";
    }

    private PdmModel getPdmModel(InputStream stream, String mid) throws Exception {
        PdmReader.initEntityClass(OPackage.class, PdmTable.class, OColumn.class);
        PdmModel<OPackage, PdmTable> pdmModel = PdmReader.readerPdmXml(stream, mid);
        stream.close();
        return pdmModel;
    }

    private List<OPackage> getAllChildPackages(OPackage root) throws Exception {
        List<OPackage> all = new ArrayList();
        if (root.getcPackages() == null) {
            return null;
        } else {
            List<OPackage> list = root.getcPackages();
            Iterator var4 = list.iterator();

            while (var4.hasNext()) {
                OPackage pkg = (OPackage) var4.next();
                all.add(pkg);
                List<OPackage> childPackages = this.getAllChildPackages(pkg);
                if (childPackages != null) {
                    all.addAll(childPackages);
                }
            }

            return all;
        }
    }

    private List<PdmTable> getAllChildTables(List<OPackage> allPackages) {
        List<PdmTable> list = new ArrayList();
        Iterator var3 = allPackages.iterator();

        while (var3.hasNext()) {
            OPackage pkg = (OPackage) var3.next();
            if (pkg.getcTables() != null) {
                List<PdmTable> arr = pkg.getcTables();
                list.addAll(arr);
            }
        }

        return list;
    }

    private List<OColumn> getAllChildColumns(List<PdmTable> allTables) {
        List<OColumn> list = new ArrayList();
        Iterator var3 = allTables.iterator();

        while (var3.hasNext()) {
            PdmTable table = (PdmTable) var3.next();
            if (table.getcColumens() != null) {
                for (OColumn getcColumen : table.getcColumens()) {
                    getcColumen.setDataTableCode(table.getTableCode());
                }
                list.addAll(table.getcColumens());
            }
        }

        return list;
    }

    private Map<Map<String, String>, Map<String, String>> getAllOReferences(List<PdmTable> allTables) {
        Iterator var3 = allTables.iterator();
        // 存储 某个表下的 某个数据项 是 哪个表下的哪个数据项
        Map<Map<String, String>, Map<String, String>> RPMap = new HashMap<>();

        while (var3.hasNext()) {
            PdmTable table = (PdmTable) var3.next();
            if (table.getcColumens() != null) {
                for (OColumn getcColumen : table.getcColumens()) {
                    if (getcColumen.getForeignKeyValue() != null) {
                        Map<String, String> map = new HashMap<>();
                        // 某个表下的数据项
                        map.put(table.getTableCode(), getcColumen.getColumnCode());
                        Map<String, String> relationMap = new HashMap<>();
                        relationMap.put(getcColumen.getForeignKeyTable(), getcColumen.getForeignKeyValue());
                        //
                        RPMap.put(map, relationMap);
                    }
                    System.out.println("*********");
                }
            }
        }

        return RPMap;
    }

    private void sortPackages(List<OPackage> list) {
        list.sort(new Comparator<OPackage>() {
            public int compare(OPackage o1, OPackage o2) {
                return o1.getLevel() < o2.getLevel() ? 1 : 0;
            }
        });
    }
}
