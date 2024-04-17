package com.intalink.configoperations.mapper.dataColumn;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.intalink.configoperations.domain.dataColumn.IkBpDataColumnBasic;
import com.intalink.configoperations.domain.dataColumn.vo.IkBpDataColumnBasicVo;
import com.intalink.configoperations.domain.pdm.OColumn;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author whx
* @createDate 2024-03-19 09:01:26
*/
public interface IkBpDataColumnBasicMapper extends BaseMapper<IkBpDataColumnBasic> {

    List<IkBpDataColumnBasicVo> selectDataColumnList(IkBpDataColumnBasicVo ikBpDataColumnBasicVo);

    IkBpDataColumnBasic selectDataColumnById(Integer dataColumnId);

    void deleteByDataColumnId(@Param("dataColumnIds") Integer[] dataColumnIds);

    int saveColumns(@Param("list") List<OColumn> list);

    List<IkBpDataColumnBasic> selectDataColumnBasicByTableId(Integer dataTableId);

    IkBpDataColumnBasic selectByTableIdAndColumnCode(@Param("dataTablelId") Integer dataTablelId,@Param("dataColumnCode") String columnCode);

    void deleteByDataTableId(@Param("dataTableIds") Integer[] dataTableIds);

    List<IkBpDataColumnBasic> selectColumnBind(Integer dataTableId);

    List<IkBpDataColumnBasic> selectRColumnBind(Integer dataTableId);

}




