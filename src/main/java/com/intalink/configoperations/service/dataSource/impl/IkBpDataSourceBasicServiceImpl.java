package com.intalink.configoperations.service.dataSource.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.intalink.configoperations.domain.dataSource.IkBpDataSourceBasic;
import com.intalink.configoperations.domain.dataSource.vo.IkBpDataSourceBasicVo;
import com.intalink.configoperations.mapper.dataSource.IkBpDataSourceBasicMapper;
import com.intalink.configoperations.service.dataSource.IkBpDataSourceBasicService;
import com.intalink.configoperations.utils.DESUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author whx
 */
@Service
public class IkBpDataSourceBasicServiceImpl implements IkBpDataSourceBasicService {

    @Autowired
    private IkBpDataSourceBasicMapper ikBpDataSourceBasicMapper;

    /**
     * 查询列表
     * @param ikBpDataSourceBasic
     * @return
     */
    @Override
    public List<IkBpDataSourceBasic> selectIkBpDataSourceBasicLists(IkBpDataSourceBasicVo ikBpDataSourceBasic) {
        String dataSourceName;
        if (ikBpDataSourceBasic.getDataSourceName() == null){
            dataSourceName = "%%";
        }else {
            dataSourceName = "%"+ikBpDataSourceBasic.getDataSourceName()+"%";
        }
        ikBpDataSourceBasic.setDataSourceName(dataSourceName);
        List<IkBpDataSourceBasic> ikBpDataSourceBasicVos = ikBpDataSourceBasicMapper.selectDataSourceList(ikBpDataSourceBasic);
        for (IkBpDataSourceBasic ikBpDataSourceBasicVo : ikBpDataSourceBasicVos) {
            String datasourcePassWord = ikBpDataSourceBasicVo.getPassword();
            String password = DESUtils.decrypt(datasourcePassWord);
            ikBpDataSourceBasicVo.setPassword(password);
        }
        return ikBpDataSourceBasicVos;
    }

    /**
     * 根据参数Id获取详细信息
     * @param dataSourceId
     * @return
     */
    @Override
    public IkBpDataSourceBasic selectDataSourceBasicById(Integer dataSourceId) {
        IkBpDataSourceBasic ikBpDataSourceBasic = ikBpDataSourceBasicMapper.selectDataSourceById(dataSourceId);
        String datasourcePassWord = ikBpDataSourceBasic.getPassword();
        String password = DESUtils.decrypt(datasourcePassWord);
        ikBpDataSourceBasic.setPassword(password);
        return ikBpDataSourceBasic;
    }

    /**
     * 根据参数Ids删除信息
     * @param dataSourceIds
     */
    @Override
    public void deleteDataSourceBasicByIds(Integer[] dataSourceIds) {
        ikBpDataSourceBasicMapper.deleteByDataSourceId(dataSourceIds);
    }

    /**
     * 插入/修改数据
     * @param ikBpDataSourceBasic
     * @return
     */
    @Override
    public int insertOrUpdate(IkBpDataSourceBasic ikBpDataSourceBasic) {
        String password = DESUtils.encrypt(ikBpDataSourceBasic.getPassword());
        ikBpDataSourceBasic.setPassword(password);
        if (ikBpDataSourceBasic.getDataSourceId() == null ){
            ikBpDataSourceBasic.setIsDel(0);
            return ikBpDataSourceBasicMapper.insert(ikBpDataSourceBasic);
        } else {
            //修改
            ikBpDataSourceBasic.setIsDel(0);
            return ikBpDataSourceBasicMapper.updateById(ikBpDataSourceBasic);
        }
    }

    /**
     * 查询全部
     * @return
     */
    @Override
    public List<IkBpDataSourceBasic> selectAll() {
        return ikBpDataSourceBasicMapper.selectAll();
    }

    /**
     * 获取数据源数量
     * @return
     */
    @Override
    public Integer count() {
        QueryWrapper<IkBpDataSourceBasic> qw = new QueryWrapper<>();
        qw.eq("is_del",0);
        return ikBpDataSourceBasicMapper.selectCount(qw);
    }

}




