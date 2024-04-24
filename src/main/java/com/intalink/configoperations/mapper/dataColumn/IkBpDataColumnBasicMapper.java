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
 * 数据项mapper
*/
public interface IkBpDataColumnBasicMapper extends BaseMapper<IkBpDataColumnBasic> {
    /**
     * 查询列表
     * @param ikBpDataColumnBasicVo
     * @return
     */
    List<IkBpDataColumnBasicVo> selectDataColumnList(IkBpDataColumnBasicVo ikBpDataColumnBasicVo);

    /**
     * 根据参数Id获取详细信息
     * @param dataColumnId
     * @return
     */
    IkBpDataColumnBasic selectDataColumnById(Integer dataColumnId);

    /**
     * 根据参数Ids删除信息
     * @param dataColumnIds
     */
    void deleteByDataColumnId(@Param("dataColumnIds") Integer[] dataColumnIds);

    /**
     * 保存全部数据项
     * @param list
     * @return
     */
    int saveColumns(@Param("list") List<OColumn> list);

    /**
     * 根据表id获取数据项数据
     * @param dataTableId
     * @return
     */
    List<IkBpDataColumnBasic> selectDataColumnBasicByTableId(Integer dataTableId);

    /**
     * 根据数据表id和数据项编码获取数据
     * @param dataTablelId
     * @param columnCode
     * @return
     */
    IkBpDataColumnBasic selectByTableIdAndColumnCode(@Param("dataTablelId") Integer dataTablelId,@Param("dataColumnCode") String columnCode);

    /**
     * 根据参数Ids删除信息
     * @param dataTableIds
     */
    void deleteByDataTableId(@Param("dataTableIds") Integer[] dataTableIds);

    /**
     * 根据表id获取绑定数据项
     * @param dataTableId
     * @return
     */
    List<IkBpDataColumnBasic> selectColumnBind(Integer dataTableId);

    /**
     * 根据表id获取绑定数据项
     * @param dataTableId
     * @return
     */
    List<IkBpDataColumnBasic> selectRColumnBind(Integer dataTableId);

}




