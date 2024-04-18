package com.intalink.configoperations.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.intalink.configoperations.domain.system.IkBpSystemBasic;
import com.intalink.configoperations.domain.system.vo.IkBpSystemBasicVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author whx
 * 数据系统mapper
*/
public interface IkBpSystemBasicMapper extends BaseMapper<IkBpSystemBasic> {
    /**
     * 获取列表
     * @param ikBpSystemBasicVo
     * @return
     */
    List<IkBpSystemBasicVo> selectSystemBasicLists(IkBpSystemBasicVo ikBpSystemBasicVo);

    /**
     * 根据参数Id获取详细信息
     * @param systemId
     * @return
     */
    IkBpSystemBasic selectSystemBasicById(Integer systemId);

    /**
     * 根据参数Ids删除信息
     * @param systemIds
     * @return
     */
    int deleteBysystemId(@Param("systemIds") Integer[] systemIds);

    /**
     * 根据系统名称获取数据
     * @param systemName
     * @return
     */
    IkBpSystemBasic selectOneBySystemName(String systemName);

    /**
     * 批量插入系统信息
     * @param listPage
     */
    void insertBatch( @Param("list") List<IkBpSystemBasic> listPage);
}




