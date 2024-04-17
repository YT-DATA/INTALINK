package com.intalink.configoperations.service.dataTableRP.impl;

import com.intalink.configoperations.domain.pdm.*;
import com.intalink.configoperations.mapper.dataColumn.IkBpDataColumnBasicMapper;
import com.intalink.configoperations.mapper.dataTable.IkBpDataTableBasicMapper;
import com.intalink.configoperations.service.dataTableRP.IHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.*;

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

        return 1;
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
