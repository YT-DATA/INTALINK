package com.intalink.configoperations.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.intalink.configoperations.domain.system.IkBpSystemBasic;
import com.intalink.configoperations.domain.system.vo.IkBpSystemBasicVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author whx
*/
public interface IkBpSystemBasicMapper extends BaseMapper<IkBpSystemBasic> {

    List<IkBpSystemBasicVo> selectSystemBasicLists(IkBpSystemBasicVo ikBpSystemBasicVo);

    IkBpSystemBasic selectSystemBasicById(Integer systemId);

    int deleteBysystemId(@Param("systemIds") Integer[] systemIds);

    IkBpSystemBasic selectOneBySystemName(String systemName);

    void insertBatch( @Param("list") List<IkBpSystemBasic> listPage);
}




