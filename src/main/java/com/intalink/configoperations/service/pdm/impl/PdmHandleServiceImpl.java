package com.intalink.configoperations.service.pdm.impl;

import com.intalink.configoperations.domain.dataTable.IkBpDataTableBasic;
import com.intalink.configoperations.domain.pdm.*;
import com.intalink.configoperations.exception.HandleDataAccessException;
import com.intalink.configoperations.mapper.dataColumn.IkBpDataColumnBasicMapper;
import com.intalink.configoperations.mapper.dataTable.IkBpDataTableBasicMapper;
import com.intalink.configoperations.service.pdm.IPdmHandleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PdmHandleServiceImpl implements IPdmHandleService {

    @Autowired
    private IkBpDataTableBasicMapper ikBpDataTableBasicMapper;

    @Autowired
    private IkBpDataColumnBasicMapper ikBpDataColumnBasicMapper;

    @Transactional
    public int autoSaveFromPdm(InputStream pdmStream, String modelId) throws Exception {
        PdmModel<OPackage, PdmTable> pdmModel = this.getPdmModel(pdmStream, modelId);
        List<OPackage> allPkgList = new ArrayList();
        if (pdmModel.getcPackages() != null) {
            allPkgList.addAll(pdmModel.getcPackages());
            Iterator var5 = pdmModel.getcPackages().iterator();

            while(var5.hasNext()) {
                OPackage pkg = (OPackage)var5.next();
                List<OPackage> childPackages = this.getAllChildPackages(pkg);
                if (childPackages != null) {
                    allPkgList.addAll(childPackages);
                }
            }
        }

        List<PdmTable> allTable = pdmModel.getcTables();
        List<OColumn> allColumns = new ArrayList();
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
        }
        if (!allTable.isEmpty()) {
            Iterator var17 = allTable.iterator();
            List<IkBpDataTableBasic> newTable = new ArrayList<>();
            for (PdmTable pdmTable : allTable) {
                IkBpDataTableBasic ikBpDataTableBasic = new IkBpDataTableBasic();
                ikBpDataTableBasic.setDataTableCode(pdmTable.getTableCode());
                ikBpDataTableBasic.setDataTableName(pdmTable.getTableName());
                ikBpDataTableBasic.setDataModelId(Integer.valueOf(modelId));
                ikBpDataTableBasic.setIsDel(0);
                newTable.add(ikBpDataTableBasic);
            }
            while(var17.hasNext()) {
                PdmTable tab = (PdmTable)var17.next();
                List<String> chis = tab.getChildrenTableCodes();
                List<String> pars = tab.getParentTableCode();
                String join;
                if (pars != null && !pars.isEmpty()) {
                    join = StringUtils.join(pars, ",");
                    tab.setParentCodes(join);
                }

                if (chis != null && !chis.isEmpty()) {
                    join = StringUtils.join(chis, ",");
                    tab.setChildCode(join);
                }
            }
            Map<String,Integer> tableMap = new HashMap<>();
            try {
                //一次执行的数量
                int submitSize = 100;
                //需要执行的次数  直接取到次数值，不用分析余数
                int num = new Double(Math.ceil(newTable.size() / Double.valueOf(submitSize))).intValue();
                for (int i = 0; i < num; i++) {
                    List<IkBpDataTableBasic> list = newTable.stream().skip(i * submitSize).limit(submitSize).collect(Collectors.toList());
                    for (IkBpDataTableBasic ikBpDataTableBasic : list) {
                        ikBpDataTableBasicMapper.saveTables(ikBpDataTableBasic);
                        tableMap.put(ikBpDataTableBasic.getDataTableCode(),ikBpDataTableBasic.getDataTableId());
                    }

                    int x= 1;
                    System.out.println(x);
                }
            } catch (DataAccessException var13) {
                throw HandleDataAccessException.getDrxdException(var13, "保存数据时，发生意外");
            }

            if (!((List)allColumns).isEmpty()) {
                try {
                    //一次执行的数量
                    int submitSize = 100;
                    //需要执行的次数  直接取到次数值，不用分析余数
                    int num = new Double(Math.ceil(allColumns.size() / Double.valueOf(submitSize))).intValue();
                    for (int i = 0; i < num; i++) {
                        List<OColumn> list = allColumns.stream().skip(i * submitSize).limit(submitSize).collect(Collectors.toList());
                        for (OColumn oColumn : list) {
                            if (tableMap.get(oColumn.getDataTableCode()) != null){
                                oColumn.setDataTableId(tableMap.get(oColumn.getDataTableCode()));
                            }
                        }
                        ikBpDataColumnBasicMapper.saveColumns(list);
                    }
                } catch (DataAccessException var12) {
                    throw HandleDataAccessException.getDrxdException(var12, "保存数据时，发生意外");
                }
            }
        }
        ikBpDataTableBasicMapper.deleteNullTableByModelId(modelId);
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

            while(var4.hasNext()) {
                OPackage pkg = (OPackage)var4.next();
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

        while(var3.hasNext()) {
            OPackage pkg = (OPackage)var3.next();
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

        while(var3.hasNext()) {
            PdmTable table = (PdmTable)var3.next();
            if (table.getcColumens() != null) {
                for (OColumn getcColumen : table.getcColumens()) {
                    getcColumen.setDataTableCode(table.getTableCode());
                }
                list.addAll(table.getcColumens());
            }
        }

        return list;
    }

    private void sortPackages(List<OPackage> list) {
        list.sort(new Comparator<OPackage>() {
            public int compare(OPackage o1, OPackage o2) {
                return o1.getLevel() < o2.getLevel() ? 1 : 0;
            }
        });
    }
}
