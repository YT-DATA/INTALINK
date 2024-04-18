package com.intalink.configoperations.service.system;

import com.intalink.configoperations.domain.system.IkBpSystemBasic;
import com.intalink.configoperations.domain.system.vo.IkBpSystemBasicVo;
import com.intalink.configoperations.web.domain.AjaxResult;

import java.util.List;

/**
 * @author whx
 */
public interface IkBpSystemBasicService {
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
    int deleteSystemBasicByIds(Integer[] systemIds);

    /**
     * 插入/修改数据
     * @param ikBpSystemBasicVo
     * @return
     */
    AjaxResult insertOrUpdate(IkBpSystemBasicVo ikBpSystemBasicVo);

    /**
     * 获取系统数量
     * @return
     */
    Integer count();
}
