package com.intalink.configoperations.service.scoringScheme.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.intalink.configoperations.web.domain.AjaxResult;
import com.intalink.configoperations.domain.evalucationMethodWeight.IkFpEvaluationMethodWeight;
import com.intalink.configoperations.domain.evalucationMethodWeight.vo.IkFpEvaluationMethodWeightVo;
import com.intalink.configoperations.domain.scoringScheme.IkFpScoringSchemeBasic;
import com.intalink.configoperations.domain.scoringScheme.vo.IkFpScoringSchemeBasicVo;
import com.intalink.configoperations.mapper.evalucationMethodWeight.IkFpEvaluationMethodWeightMapper;
import com.intalink.configoperations.mapper.scoringScheme.IkFpScoringSchemeBasicMapper;
import com.intalink.configoperations.service.scoringScheme.IkFpScoringSchemeBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author whx
 */
@Service
public class IkFpScoringSchemeBasicServiceImpl implements IkFpScoringSchemeBasicService {

    @Autowired
    private IkFpScoringSchemeBasicMapper ikFpScoringSchemeBasicMapper;

    @Autowired
    private IkFpEvaluationMethodWeightMapper ikFpEvaluationMethodWeightMapper;

    @Override
    public List<IkFpScoringSchemeBasicVo> selectScoringSchemeLists(IkFpScoringSchemeBasicVo ikFpScoringSchemeBasicVo) {
        String scoringSchemeName = "%%";
        if (ikFpScoringSchemeBasicVo.getScoringSchemeName() != null) {
            scoringSchemeName = "%" + ikFpScoringSchemeBasicVo.getScoringSchemeName() + "%";
        }
        return ikFpScoringSchemeBasicMapper.selectByScoringSchemeName(scoringSchemeName);
    }

    @Override
    public AjaxResult insertOrUpdate(IkFpScoringSchemeBasic ikFpScoringSchemeBasic, Boolean insertOrUpdate) {
        if (getOne(ikFpScoringSchemeBasic)) {
            if (insertOrUpdate) {
                return AjaxResult.success(ikFpScoringSchemeBasicMapper.insert(ikFpScoringSchemeBasic));
            } else {
                return AjaxResult.success(ikFpScoringSchemeBasicMapper.updateById(ikFpScoringSchemeBasic));
            }
        } else {
            return AjaxResult.error("该方案已存在");
        }
    }

    @Override
    public AjaxResult deleteScoringSchemeBasicByIds(Integer[] scoringSchemeIds, Boolean sureOrNot) {
        if (sureOrNot) {
            ikFpScoringSchemeBasicMapper.deleteBySSIds(scoringSchemeIds);
            ikFpEvaluationMethodWeightMapper.deleteByIds(scoringSchemeIds);
            return AjaxResult.success();
        }
        List<IkFpEvaluationMethodWeightVo> ikFpEvaluationMethodWeightVos = ikFpEvaluationMethodWeightMapper.selectBySSIds(scoringSchemeIds);
        if (ikFpEvaluationMethodWeightVos.size() > 0) {
            String data = "";
            for (int i = 0; i < ikFpEvaluationMethodWeightVos.size(); i++) {
                if (i == ikFpEvaluationMethodWeightVos.size() - 1) {
                    data = data + ikFpEvaluationMethodWeightVos.get(i).getScoringSchemeName();
                } else {
                    data = data + ikFpEvaluationMethodWeightVos.get(i).getScoringSchemeName() + "、";
                }
            }
            return AjaxResult.success(data + "与方法存在绑定关系,是否确认删除？");
        } else {
            ikFpScoringSchemeBasicMapper.deleteBySSIds(scoringSchemeIds);
//            ikFpEvaluationMethodWeightMapper.deleteByIds(scoringSchemeIds);
            return AjaxResult.success();
        }
    }

    @Override
    public List<IkFpEvaluationMethodWeightVo> selectScoringSchemeById(Integer scoringSchemeId) {
        List<IkFpEvaluationMethodWeightVo> ikFpScoringSchemeBasicVoList =  ikFpEvaluationMethodWeightMapper.selectScoringSchemeById(scoringSchemeId);
        return ikFpScoringSchemeBasicVoList;
    }

    @Override
    public AjaxResult insert(List<IkFpEvaluationMethodWeight> ikFpEvaluationMethodWeight, Integer scoringSchemeId) {
        QueryWrapper<IkFpEvaluationMethodWeight> qw = new QueryWrapper<>();
        qw.eq("scoring_scheme_id",scoringSchemeId);
        ikFpEvaluationMethodWeightMapper.delete(qw);
        for (IkFpEvaluationMethodWeight fpEvaluationMethodWeight : ikFpEvaluationMethodWeight) {
            fpEvaluationMethodWeight.setScoringSchemeId(scoringSchemeId);
            ikFpEvaluationMethodWeightMapper.insert(fpEvaluationMethodWeight);
        }
        return AjaxResult.success();
    }

    private Boolean getOne(IkFpScoringSchemeBasic ikFpScoringSchemeBasic) {
        QueryWrapper<IkFpScoringSchemeBasic> qw = new QueryWrapper<>();
        qw.eq("scoring_scheme_name", ikFpScoringSchemeBasic.getScoringSchemeName());
        IkFpScoringSchemeBasic ikFpScoringSchemeBasicOne = ikFpScoringSchemeBasicMapper.selectOne(qw);
        if (ikFpScoringSchemeBasicOne != null)
            return false;
        else return true;
    }
}




