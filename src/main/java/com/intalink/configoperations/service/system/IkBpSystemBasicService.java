package com.intalink.configoperations.service.system;

import com.intalink.configoperations.domain.system.IkBpSystemBasic;
import com.intalink.configoperations.domain.system.vo.IkBpSystemBasicVo;
import com.intalink.configoperations.web.domain.AjaxResult;

import java.util.List;

/**
 * @author whx
 */
public interface IkBpSystemBasicService {

    List<IkBpSystemBasicVo> selectSystemBasicLists(IkBpSystemBasicVo ikBpSystemBasicVo);

    IkBpSystemBasic selectSystemBasicById(Integer systemId);

    int deleteSystemBasicByIds(Integer[] systemIds);

    AjaxResult insertOrUpdate(IkBpSystemBasicVo ikBpSystemBasicVo);

    Integer count();
}
