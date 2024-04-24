package com.intalink.configoperations.service.evaluationMethod.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.intalink.configoperations.web.domain.AjaxResult;
import com.intalink.configoperations.domain.evaluationMethod.IkBpEvaluationMethodBasic;
import com.intalink.configoperations.domain.evaluationMethod.vo.IkBpEvaluationMethodBasicVo;
import com.intalink.configoperations.domain.evalucationMethodWeight.vo.IkFpEvaluationMethodWeightVo;
import com.intalink.configoperations.mapper.evaluationMethod.IkBpEvaluationMethodBasicMapper;
import com.intalink.configoperations.mapper.evalucationMethodWeight.IkFpEvaluationMethodWeightMapper;
import com.intalink.configoperations.service.evaluationMethod.IkBpEvaluationMethodBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author whx
 */
@Service
public class IkBpEvaluationMethodBasicServiceImpl implements IkBpEvaluationMethodBasicService {
    @Autowired
    private IkBpEvaluationMethodBasicMapper ikBpEvaluationMethodBasicMapper;
    @Autowired
    private IkFpEvaluationMethodWeightMapper ikFpEvaluationMethodWeightMapper;

    /**
     * 获取列表
     * @param ikBpEvaluationMethodBasicVo
     * @return
     */
    @Override
    public List<IkBpEvaluationMethodBasicVo> selectEvaluationMethodBasicLists(IkBpEvaluationMethodBasicVo ikBpEvaluationMethodBasicVo) {
        String evaluationMethodName = "%%";
        if (ikBpEvaluationMethodBasicVo.getEvaluationMethodName() != null) {
            evaluationMethodName = "%" + ikBpEvaluationMethodBasicVo.getEvaluationMethodName() + "%";
        }
        return ikBpEvaluationMethodBasicMapper.selectByEvaluationMethodName(evaluationMethodName);
    }

    /**
     * 新增或修改（true新增，false修改）
     * @param ikBpEvaluationMethodBasic
     * @param insertOrUpdate
     * @return
     */
    @Override
    public AjaxResult insertOrUpdate(IkBpEvaluationMethodBasic ikBpEvaluationMethodBasic, Boolean insertOrUpdate) {
        if (getOne(ikBpEvaluationMethodBasic)) {

            if (insertOrUpdate) {
                return AjaxResult.success(ikBpEvaluationMethodBasicMapper.insert(ikBpEvaluationMethodBasic));
            } else {
                return AjaxResult.success(ikBpEvaluationMethodBasicMapper.updateById(ikBpEvaluationMethodBasic));
            }
        } else {
            return AjaxResult.error("该方法已存在");
        }
    }

    /**
     * 根据参数Ids删除信息
     * @param evaluationMethodIds
     * @return
     */
    @Override
    public AjaxResult deleteEvaluationMethodBasicByIds(Integer[] evaluationMethodIds) {
        List<IkFpEvaluationMethodWeightVo> ikFpEvaluationMethodWeightVos = ikFpEvaluationMethodWeightMapper.selectByEMIds(evaluationMethodIds);
        if (ikFpEvaluationMethodWeightVos.size() > 0) {
            String data = "";
            for (int i = 0; i < ikFpEvaluationMethodWeightVos.size(); i++) {
                if (i == ikFpEvaluationMethodWeightVos.size() - 1) {
                    data = data + ikFpEvaluationMethodWeightVos.get(i).getScoringSchemeName();
                } else {
                    data = data + ikFpEvaluationMethodWeightVos.get(i).getScoringSchemeName() + "、";
                }
            }
            return AjaxResult.error(500, data + "与方案存在绑定关系,不允许删除");
        } else {
            ikBpEvaluationMethodBasicMapper.deleteByIds(evaluationMethodIds);
            return AjaxResult.success();
        }
    }

    /**
     * 查询全部
     * @return
     */
    @Override
    public List<IkBpEvaluationMethodBasicVo> selectAll() {
        return ikBpEvaluationMethodBasicMapper.selectAll();
    }

    /**
     * 根据评价方法获取对应数据
     * @param ikBpEvaluationMethodBasic
     * @return
     */
    private Boolean getOne(IkBpEvaluationMethodBasic ikBpEvaluationMethodBasic) {
        QueryWrapper<IkBpEvaluationMethodBasic> qw = new QueryWrapper<>();
        qw.eq("evaluation_method_name", ikBpEvaluationMethodBasic.getEvaluationMethodName());
        IkBpEvaluationMethodBasic ikBpEvaluationMethodBasicOne = ikBpEvaluationMethodBasicMapper.selectOne(qw);
        if (ikBpEvaluationMethodBasicOne != null)
            return false;
        else return true;
    }
}




