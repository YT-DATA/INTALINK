package com.intalink.configoperations.domain.pdm;

import com.intalink.configoperations.utils.PrimaryKeyGenarator;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class PdmReader {
    private static PdmInitClass initClass = null;
    private static List<OReferences> references;
    private static List<Element> columnList;
    private static List<Element> tableList;

    public PdmReader() {
    }

    public static <P extends OPackage, T extends OTable, C extends OColumn> void initEntityClass(Class<P> pClass, Class<T> tClass, Class<C> cClass) throws Exception {
        initClass = new PdmInitClass(pClass, tClass, cClass);
    }

    public static <P extends OPackage, T extends OTable> PdmModel<P, T> readerPdmXml(InputStream inputStream, String modelBusinessKey) throws Exception {
        if (initClass == null) {
            throw new Exception("请先调用initEntityClass()方法初始化");
        } else {
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            Element root = document.getRootElement();
            Element model = (Element)root.selectSingleNode("//o:Model");
            references = getReferences(model);
            columnList = model.selectNodes("//c:Columns");
            tableList = model.selectNodes("//c:Tables");
            String modelId = model.attributeValue("Id");
            String modelName = model.elementText("Name");
            PdmModel<P, T> pdmModel = new PdmModel();
            pdmModel.setModelName(modelName);
            pdmModel.setModelId(modelId);
            pdmModel.setModelBusinessKey(modelBusinessKey);
            Element packages = model.element("Packages");
            Element tables = model.element("Tables");
            List<P> rootPackages = getRootPackages(modelBusinessKey, (String)null, 1, packages);
            List<T> rootTables = getChildrenTables(modelBusinessKey, (String)null, tables);
            if (rootPackages != null) {
                pdmModel.setcPackages(rootPackages);
            }

            if (rootTables != null) {
                pdmModel.setcTables(rootTables);
            }

            return pdmModel;
        }
    }

    private static <P extends OPackage> List<P> getRootPackages(String modelBusinessKey, String parentPkgKey, int level, Element pkgRoot) throws Exception {
        List<P> list = new ArrayList();
        if (pkgRoot == null) {
            return null;
        } else {
            List<Element> elements = pkgRoot.elements();
            OPackage pkg;
            if (elements != null && elements.size() > 0) {
                for(Iterator var6 = elements.iterator(); var6.hasNext(); list.add((P) pkg)) {
                    Element pEle = (Element)var6.next();
                    String businessKey = PrimaryKeyGenarator.getId();
                    pkg = (OPackage)initClass.getpClass().newInstance();
                    pkg.setPackageName(pEle.elementText("Name"));
                    pkg.setPackageId(pEle.attributeValue("Id"));
                    pkg.setPackageCode(pEle.elementText("Code"));
                    pkg.setBusinessKey(businessKey);
                    pkg.setPdmModelId(modelBusinessKey);
                    pkg.setParentPkgBusinessKey(parentPkgKey);
                    pkg.setLevel(level);
                    if (!StringUtils.isEmpty(pEle.elementText("Comment"))) {
                        pkg.setRemark(pEle.elementText("Comment"));
                    }
                    List tables = getChildrenTables(modelBusinessKey, businessKey, pEle.element("Tables"));
                    if (tables != null) {
                        pkg.setcTables(tables);
                    }

                    int n = level + 1;
                    List children = getRootPackages(modelBusinessKey, pkg.getBusinessKey(), n, pEle.element("Packages"));
                    if (children != null) {
                        pkg.setcPackages(children);
                    }
                }
            }

            return list;
        }
    }

    private static List<OReferences> getReferences(Element model) {
        List<Element> all = model.selectNodes("//c:References");
        if (all != null && !all.isEmpty()) {
            List<OReferences> list = new ArrayList();
            Iterator var3 = all.iterator();

            while(true) {
                List refEles;
                do {
                    do {
                        if (!var3.hasNext()) {
                            return list.isEmpty() ? null : list;
                        }

                        Element element = (Element)var3.next();
                        refEles = element.elements();
                    } while(refEles == null);
                } while(refEles.size() <= 0);

                List<OReferences> refs = new ArrayList();
                Iterator var7 = refEles.iterator();

                while(var7.hasNext()) {
                    Element ele = (Element)var7.next();
                    if (ele.element("ParentTable") != null && ele.element("ParentTable").element("Table") != null) {
                        OReferences references = new OReferences();
                        references.setParentTableId(ele.element("ParentTable").element("Table").attributeValue("Ref"));
                        references.setChildrenTableId(ele.element("ChildTable").element("Table").attributeValue("Ref"));
                        if (ele.element("Joins") != null && ele.element("Joins").element("ReferenceJoin").element("Object1") != null
                                && ele.element("Joins").element("ReferenceJoin").element("Object1").element("Column")!=null
                                && ele.element("Joins").element("ReferenceJoin").element("Object2") != null
                                && ele.element("Joins").element("ReferenceJoin").element("Object2").element("Column")!=null) {
                            String left = ele.element("Joins").element("ReferenceJoin").element("Object1").element("Column").attributeValue("Ref");
                            String right = ele.element("Joins").element("ReferenceJoin").element("Object2").element("Column").attributeValue("Ref");
                            references.setLeftColId(left);
                            references.setRightColId(right);
                        }
                        if (references.getLeftColId()!=null&&references.getRightColId()!=null)
                        refs.add(references);
                    }
                }

                if (!refs.isEmpty()) {
                    list.addAll(refs);
                }
            }
        } else {
            return null;
        }
    }

    private static <T extends OTable, C extends OColumn> List<T> getChildrenTables(String modelBusinessKey, String pkgBusinessKey, Element tableElement) throws Exception {
        if (tableElement == null) {
            return null;
        } else {
            List<Element> elements = tableElement.elements();
            if (elements == null && elements.isEmpty()) {
                return null;
            } else {
                List<T> list = new ArrayList();

                OTable t;
                for(Iterator var5 = elements.iterator(); var5.hasNext(); list.add((T) t)) {
                    Element ele = (Element)var5.next();
                    t = (OTable)initClass.gettClass().newInstance();
                    String businessKey = PrimaryKeyGenarator.getId();
                    t.setTableName(ele.elementText("Name"));
                    t.setTableId(ele.attributeValue("Id"));
                    t.setTableCode(ele.elementText("Code"));
                    t.setPackageBusinessKey(pkgBusinessKey);
                    t.setPdmModelId(modelBusinessKey);
                    t.setBusinessKey(businessKey);
                    if (!StringUtils.isEmpty(ele.elementText("Comment"))) {
                        String comment = ele.elementText("Comment");
                        if(comment.length()>85){
                            comment = comment.substring(0, 85);
                        }
                        t.setRemark(comment);
                    }
                    setTableReference(t);
                    Element keyEle = ele.element("Keys");
                    List<C> columns = getColumns(modelBusinessKey, businessKey, ele.element("Columns"));
                    if (columns != null && columns.size() > 1) {
                        setColumns(columns, keyEle);
                        t.setcColumens(columns);
                    }
                }

                return list;
            }
        }
    }

    private static <C extends OColumn> void setColumns(List<C> columns, Element keyEle) throws Exception {
        List<String> pkList = getPkList(keyEle);
        if (pkList != null && columns != null) {
            Iterator var3 = columns.iterator();

            while(true) {
                OColumn column;
                String id;
                do {
                    if (!var3.hasNext()) {
                        return;
                    }

                    column = (OColumn)var3.next();
                    id = column.getColumnId();
                    if (pkList.contains(id)) {
                        column.setIsPk("1");
                    }
                } while(references == null);

                Iterator var6 = references.iterator();

                while(var6.hasNext()) {
                    OReferences ref = (OReferences)var6.next();
                    if (ref.getRightColId() != null && ref.getRightColId().equals(id)) {
                        column.setIsFk("1");
                        String foreignTable = getForeignTableCode(ref.getParentTableId());
                        String foreignValue = getForeignColumnCode(ref.getLeftColId());
                        column.setForeignKeyTable(foreignTable);
                        column.setForeignKeyValue(foreignValue);
                    }
                }
            }
        }
    }

    private static String getForeignColumnCode(String leftColId) {
        if (columnList != null && !columnList.isEmpty()) {
            Iterator var1 = columnList.iterator();

            while(var1.hasNext()) {
                Element ele = (Element)var1.next();
                List<Element> cols = ele.elements();
                Iterator var4 = cols.iterator();

                while(var4.hasNext()) {
                    Element c = (Element)var4.next();
                    if (c.attributeValue("Id").equals(leftColId)) {
                        return c.elementText("Code");
                    }
                }
            }

            return null;
        } else {
            return null;
        }
    }

    private static String getForeignTableCode(String parentTableId) {
        if (tableList != null && !tableList.isEmpty()) {
            Iterator var1 = tableList.iterator();

            while(var1.hasNext()) {
                Element ele = (Element)var1.next();
                List<Element> tabs = ele.elements();
                Iterator var4 = tabs.iterator();

                while(var4.hasNext()) {
                    Element tab = (Element)var4.next();
                    if (tab.attributeValue("Id").equals(parentTableId)) {
                        return tab.elementText("Code");
                    }
                }
            }

            return null;
        } else {
            return null;
        }
    }

    private static List<String> getPkList(Element keyEle) throws Exception {
        if (keyEle == null) {
            return null;
        } else if (keyEle.element("Key") != null && keyEle.element("Key").element("Key.Columns") != null) {
            List<Element> keys = keyEle.element("Key").element("Key.Columns").elements();
            if (keys != null && !keys.isEmpty()) {
                List<String> ids = new ArrayList();
                Iterator var3 = keys.iterator();

                while(var3.hasNext()) {
                    Element key = (Element)var3.next();
                    String ref = key.attributeValue("Ref");
                    ids.add(ref);
                }

                return ids.isEmpty() ? null : ids;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private static <C extends OColumn> List<C> getColumns(String modelBusinessKey, String tabBusinessKey, Element colElement) throws Exception {
        if (colElement == null) {
            return null;
        } else {
            List<Element> elements = colElement.elements();
            if (elements != null && !elements.isEmpty()) {
                List<C> list = new ArrayList();

                OColumn col;
                for(Iterator var5 = elements.iterator(); var5.hasNext(); list.add((C) col)) {
                    Element ele = (Element)var5.next();
                    String businessKey = PrimaryKeyGenarator.getId();
                    col = (OColumn)initClass.getcClass().newInstance();
                    col.setBusinessKey(businessKey);
                    col.setModelId(modelBusinessKey);
                    col.setDataType(ele.elementText("DataType"));
                    col.setDataLength(StringUtils.isEmpty(ele.elementText("Length")) ? null : new Integer(ele.elementText("Length")));
                    col.setTableBusinessKey(tabBusinessKey);
                    col.setColumnId(ele.attributeValue("Id"));
                    col.setColumnCode(ele.elementText("Code"));
                    col.setColumnName(ele.elementText("Name"));
                    col.setIsPk("0");
                    col.setIsFk("0");
                    col.setIsNotNull(StringUtils.isEmpty(ele.elementText("Column.Mandatory")) ? "0" : "1");
                    Integer acc = StringUtils.isEmpty(ele.elementText("Precision")) ? null : new Integer(ele.elementText("Precision"));
                    col.setDataAccuracy(acc);
                    if (!StringUtils.isEmpty(ele.elementText("Comment"))) {
                        String comment = ele.elementText("Comment");
                        if(comment.length()>85){
                            comment = comment.substring(0, 85);
                        }
                        col.setColumnRemark(comment);
                    }
                }

                return list;
            } else {
                return null;
            }
        }
    }

    private static <T extends OTable> void setTableReference(T table) throws Exception {
        if (references != null && !references.isEmpty()) {
            String id = table.getTableId();
            Set<String> parentList = new TreeSet();
            Set<String> childList = new TreeSet();
            Iterator var4 = references.iterator();

            while(var4.hasNext()) {
                OReferences ref = (OReferences)var4.next();
                String code;
                if (ref.getParentTableId().equals(id)) {
                    code = getTableCode(ref.getChildrenTableId());
                    if (code != null) {
                        childList.add(code);
                    }
                }

                if (ref.getChildrenTableId().equals(id)) {
                    code = getTableCode(ref.getParentTableId());
                    if (code != null) {
                        parentList.add(code);
                    }
                }
            }

            if (!parentList.isEmpty()) {
                table.setParentTableCode(new ArrayList(parentList));
            }

            if (!childList.isEmpty()) {
                table.setChildrenTableCodes(new ArrayList(childList));
            }
        }

    }

    private static String getTableCode(String tableId) throws Exception {
        if (tableList.isEmpty()) {
            return null;
        } else {
            Iterator var1 = tableList.iterator();

            while(var1.hasNext()) {
                Element tab = (Element)var1.next();
                List<Element> elements = tab.elements();
                if (elements == null || elements.isEmpty()) {
                    return null;
                }

                Iterator var4 = elements.iterator();

                while(var4.hasNext()) {
                    Element ele = (Element)var4.next();
                    if (tableId.equals(ele.attributeValue("Id"))) {
                        return ele.elementText("Code");
                    }
                }
            }

            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        initEntityClass(TestPackage.class, TestTable.class, OColumn.class);
        InputStream in = new FileInputStream(new File("C:\\Users\\yuzy\\Desktop\\项目结构基础模型1_1.pdm"));
        PdmModel<TestPackage, TestTable> pdmModel = readerPdmXml(in, "root");
        in.close();
    }
}

