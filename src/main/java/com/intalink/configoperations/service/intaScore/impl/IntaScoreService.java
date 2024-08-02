package com.intalink.configoperations.service.intaScore.impl;

import com.intalink.configoperations.domain.evalucationMethodWeight.vo.IkFpEvaluationMethodWeightVo;
import com.intalink.configoperations.domain.intaScore.vo.EvaluationMethodScoreVo;
import com.intalink.configoperations.domain.intaScore.vo.IntaLinkScoreVo;
import com.intalink.configoperations.domain.intaScore.vo.MethodScore;
import com.intalink.configoperations.enums.ScoreStrategy;
import com.intalink.configoperations.mapper.evalucationMethodWeight.IkFpEvaluationMethodWeightMapper;
import com.intalink.configoperations.service.intaScore.IIntaScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import java.util.Collections;

@Service
public class IntaScoreService implements IIntaScoreService {

    @Autowired
    public IkFpEvaluationMethodWeightMapper ikFpEvaluationMethodWeightMapper;


    /**
     * 获取评分结果
     *
     * @param evaluationMethodScoreVoList 评分方法评分后的链路得分情况
     * @param scoreStrategy               评分策略
     * @return
     */
    @Override
    public List<IntaLinkScoreVo> getIntaLinkScore(List<EvaluationMethodScoreVo> evaluationMethodScoreVoList, Integer scoringSchemeId, ScoreStrategy scoreStrategy) {
        List<IntaLinkScoreVo> intalinkScoreVoList = new ArrayList<>();
        //获取评分方案下的评分方法权重
        List<IkFpEvaluationMethodWeightVo> ikFpEvaluationMethodWeightVoList = ikFpEvaluationMethodWeightMapper.selectScoringSchemeById(scoringSchemeId);
        //加权求和
        if (scoreStrategy.getCode().equals(ScoreStrategy.SUM.getCode())) {
            //遍历每条链路
            for (EvaluationMethodScoreVo evaluationMethodScoreVo : evaluationMethodScoreVoList) {
                IntaLinkScoreVo intalinkScoreVo = new IntaLinkScoreVo();
                intalinkScoreVo.setIntaLinkId(evaluationMethodScoreVo.getIntaLinkId());
                intalinkScoreVo.setScoringSchemeId(scoringSchemeId);
                BigDecimal score = new BigDecimal(0);
                for (IkFpEvaluationMethodWeightVo ikFpEvaluationMethodWeightVo : ikFpEvaluationMethodWeightVoList) {
                    //遍历每条链路的评分方法
                    for (MethodScore methodScore : evaluationMethodScoreVo.getMethodScoreList()) {
                        if (ikFpEvaluationMethodWeightVo.getEvaluationMethodId().equals(methodScore.getEvaluationMethodId())) {
                            //权重系数
                            BigDecimal weightCoefficient = new BigDecimal(ikFpEvaluationMethodWeightVo.getWeightCoefficient());
                            score = score.add(methodScore.getScore().multiply(weightCoefficient));
                        }
                    }
                }
                intalinkScoreVo.setScore(score);
                intalinkScoreVoList.add(intalinkScoreVo);
            }
        } else if (scoreStrategy.getCode().equals(ScoreStrategy.AVERAGE.getCode())) {//加权平均
            //获取方案总权重
            Double totalWeight = getTotalWeight(ikFpEvaluationMethodWeightVoList);
            //遍历每条链路
            for (EvaluationMethodScoreVo evaluationMethodScoreVo : evaluationMethodScoreVoList) {
                IntaLinkScoreVo intalinkScoreVo = new IntaLinkScoreVo();
                intalinkScoreVo.setIntaLinkId(evaluationMethodScoreVo.getIntaLinkId());
                intalinkScoreVo.setScoringSchemeId(scoringSchemeId);
                BigDecimal score = new BigDecimal(0);
                //参与评分方法的总权重
                BigDecimal methodScoreWeight = new BigDecimal(0);
                for (IkFpEvaluationMethodWeightVo ikFpEvaluationMethodWeightVo : ikFpEvaluationMethodWeightVoList) {
                    //遍历每条链路的评分方法
                    for (MethodScore methodScore : evaluationMethodScoreVo.getMethodScoreList()) {
                        if (ikFpEvaluationMethodWeightVo.getEvaluationMethodId().equals(methodScore.getEvaluationMethodId()) && methodScore.getScore().compareTo(BigDecimal.valueOf(0)) > 0) {
                            //权重系数
                            methodScoreWeight = methodScoreWeight.add(BigDecimal.valueOf(ikFpEvaluationMethodWeightVo.getWeightCoefficient()));

                        }
                    }
                }
                //计算每条链路的得分
                for (IkFpEvaluationMethodWeightVo ikFpEvaluationMethodWeightVo : ikFpEvaluationMethodWeightVoList) {
                    //遍历每条链路的评分方法
                    for (MethodScore methodScore : evaluationMethodScoreVo.getMethodScoreList()) {
                        if (ikFpEvaluationMethodWeightVo.getEvaluationMethodId().equals(methodScore.getEvaluationMethodId()) && methodScore.getScore().compareTo(BigDecimal.valueOf(0)) > 0) {
                            //权重系数
                            BigDecimal weightCoefficient = BigDecimal.valueOf(ikFpEvaluationMethodWeightVo.getWeightCoefficient());
                            //加权平均法计算逻辑 方法得分*权重系数/100*方案总权重/该链路得分方法总权重
                            score = score.add(methodScore.getScore().multiply(weightCoefficient).divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(totalWeight)).divide(methodScoreWeight));

                        }
                    }
                }
                intalinkScoreVo.setScore(score);
                intalinkScoreVoList.add(intalinkScoreVo);
            }
        } else if (scoreStrategy.getCode().equals(ScoreStrategy.EQUALRATIO.getCode())) {//等比法
            //先获取每条链路当中，参与评分的方法数量。
            Map<Object, Object> map = new HashMap<>(ikFpEvaluationMethodWeightVoList.size());
            for (IkFpEvaluationMethodWeightVo ikFpEvaluationMethodWeightVo : ikFpEvaluationMethodWeightVoList) {
                map.put(ikFpEvaluationMethodWeightVo.getEvaluationMethodId(), getMethodScoreCount(ikFpEvaluationMethodWeightVo.getEvaluationMethodId(), evaluationMethodScoreVoList));
            }
            //定义数组，将可以参与评分的方法找到
            ArrayList<Integer> evaluationMethodIdArray = new ArrayList<>();
            //遍历Map,将有可用于评分的方法加入到数组中
            map.forEach((key, value) -> {
                if (value.equals(evaluationMethodScoreVoList.size())) {
                    evaluationMethodIdArray.add((Integer) key);
                }
            });
            //如果evaluationMethodIdArray不为空，则证明有方法可用于评分
            if (evaluationMethodIdArray.size() > 0) {
                //遍历每个方法
                for (Integer evaluationMethodId : evaluationMethodIdArray) {
                    //遍历每条链路
                    for (EvaluationMethodScoreVo evaluationMethodScoreVo : evaluationMethodScoreVoList) {
                        if (evaluationMethodScoreVo.getMethodScoreList().get(0).getEvaluationMethodId().equals(evaluationMethodId)) {
                            IntaLinkScoreVo intalinkScoreVo = new IntaLinkScoreVo();
                            intalinkScoreVo.setIntaLinkId(evaluationMethodScoreVo.getIntaLinkId());
                            intalinkScoreVo.setScoringSchemeId(scoringSchemeId);
                            BigDecimal score = new BigDecimal(0);
                            for (MethodScore methodScore : evaluationMethodScoreVo.getMethodScoreList()) {
                                if (methodScore.getEvaluationMethodId().equals(evaluationMethodId)) {
                                    Double weightCoefficient = getMethodWeightCoefficient(evaluationMethodId, ikFpEvaluationMethodWeightVoList);
                                    //每条链路的分值，方法得分*权重系数/100
                                    score = score.add(methodScore.getScore().multiply(BigDecimal.valueOf(weightCoefficient)).divide(BigDecimal.valueOf(100)));
                                }
                            }
                            intalinkScoreVo.setScore(score);
                            intalinkScoreVoList.add(intalinkScoreVo);
                        }
                    }
                }
            }
        } else if (scoreStrategy.getCode().equals(ScoreStrategy.MEANVALUE.getCode())) {//均值法
            //按照方案计算方案当中每个评分方法的平均值
            ArrayList<Map<Object, Object>> mapList = new ArrayList<>();
            //遍历方案下所有的方法
            for (IkFpEvaluationMethodWeightVo ikFpEvaluationMethodWeightVo : ikFpEvaluationMethodWeightVoList) {
                Map<Object, Object> map = getMethodAverage(ikFpEvaluationMethodWeightVo.getEvaluationMethodId(), evaluationMethodScoreVoList);
                mapList.add(map);
            }
            //上述内容已经拿到各个方法的平均值
            //遍历每条链路
            for (EvaluationMethodScoreVo evaluationMethodScoreVo : evaluationMethodScoreVoList) {
                IntaLinkScoreVo intalinkScoreVo = new IntaLinkScoreVo();
                intalinkScoreVo.setIntaLinkId(evaluationMethodScoreVo.getIntaLinkId());
                intalinkScoreVo.setScoringSchemeId(scoringSchemeId);
                BigDecimal score = new BigDecimal(0);
                //遍历每条链路
                for (MethodScore methodScore : evaluationMethodScoreVo.getMethodScoreList()) {
                    for (IkFpEvaluationMethodWeightVo ikFpEvaluationMethodWeightVo : ikFpEvaluationMethodWeightVoList) {
                        if (ikFpEvaluationMethodWeightVo.getEvaluationMethodId().equals(methodScore.getEvaluationMethodId()) && methodScore.getScore().compareTo(BigDecimal.valueOf(0)) > 0) {
                            //计算方法权重
                            score = score.add(methodScore.getScore().multiply(BigDecimal.valueOf(methodScore.getWeightCoefficient())).divide(BigDecimal.valueOf(100)));
                        } else if (ikFpEvaluationMethodWeightVo.getEvaluationMethodId().equals(methodScore.getEvaluationMethodId()) && methodScore.getScore().compareTo(BigDecimal.valueOf(0)) > 0) {
                            //拿到方法的平均值
                            BigDecimal average = getMethodAverage(ikFpEvaluationMethodWeightVo.getEvaluationMethodId(), mapList);
                            score = score.add(average.multiply(BigDecimal.valueOf(methodScore.getWeightCoefficient())).divide(BigDecimal.valueOf(100)));
                        }
                    }

                }

                intalinkScoreVo.setScore(score);
            }

        }
        return intalinkScoreVoList;
    }

    /**
     * 获取评分方法评分数量
     *
     * @param evaluationMethodId
     * @param evaluationMethodScoreVoList
     * @return
     */
    public Integer getMethodScoreCount(Integer evaluationMethodId, List<EvaluationMethodScoreVo> evaluationMethodScoreVoList) {
        Integer count = 0;
        //遍历每条链路
        for (EvaluationMethodScoreVo evaluationMethodScoreVo : evaluationMethodScoreVoList) {
            //将本条链路提取成一个数组
            for (int i = 0; i < evaluationMethodScoreVo.getMethodScoreList().size(); i++) {
                if (evaluationMethodId.equals(evaluationMethodScoreVo.getMethodScoreList().get(i).getEvaluationMethodId()) && evaluationMethodScoreVo.getMethodScoreList().get(i).getScore() != null) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 获取指定方法的权重系数
     *
     * @param evaluationMethodId
     * @param ikFpEvaluationMethodWeightVoList
     * @return
     */
    public Double getMethodWeightCoefficient(Integer evaluationMethodId, List<IkFpEvaluationMethodWeightVo> ikFpEvaluationMethodWeightVoList) {
        Double weightCoefficient = 0.0;
        for (IkFpEvaluationMethodWeightVo ikFpEvaluationMethodWeightVo : ikFpEvaluationMethodWeightVoList) {
            if (ikFpEvaluationMethodWeightVo.getEvaluationMethodId().equals(evaluationMethodId)) {
                weightCoefficient = ikFpEvaluationMethodWeightVo.getWeightCoefficient();
            }
        }
        return weightCoefficient;
    }

    /**
     * 获取方案总权重
     *
     * @param ikFpEvaluationMethodWeightVoList
     * @return
     */
    public Double getTotalWeight(List<IkFpEvaluationMethodWeightVo> ikFpEvaluationMethodWeightVoList) {
        Double totalWeight = 0.0;
        for (IkFpEvaluationMethodWeightVo ikFpEvaluationMethodWeightVo : ikFpEvaluationMethodWeightVoList) {
            totalWeight = totalWeight + ikFpEvaluationMethodWeightVo.getWeightCoefficient();
        }
        return totalWeight;
    }

    /**
     * 获取每个方法平均分
     *
     * @return
     */
    public Map<Object, Object> getMethodAverage(Integer evaluationMethodId, List<EvaluationMethodScoreVo> evaluationMethodScoreVoList) {
        Map<Object, Object> map = new HashMap<>();

        int count = 0;
        BigDecimal allScore = new BigDecimal(0);
        for (EvaluationMethodScoreVo evaluationMethodScoreVo : evaluationMethodScoreVoList) {
            for (MethodScore methodScore : evaluationMethodScoreVo.getMethodScoreList()) {
                if (evaluationMethodId.equals(methodScore.getEvaluationMethodId()) && methodScore.getScore().compareTo(BigDecimal.ZERO) > 0) {
                    count++;
                    allScore = allScore.add(methodScore.getScore());
                }
            }
        }

        //对应的方法
        map.put("evaluationMethodId", evaluationMethodId);
        //方法的平均值
        map.put("average", allScore.divide(BigDecimal.valueOf(count)));

        return map;
    }

    /**
     * 根据方法获取平均值
     *
     * @param evaluationMethodId
     * @param mapList
     * @return
     */
    public BigDecimal getMethodAverage(Integer evaluationMethodId, ArrayList<Map<Object, Object>> mapList) {
        BigDecimal average = new BigDecimal(0);
        for (Map<Object, Object> map : mapList) {
            if (map.get("evaluationMethodId").equals(evaluationMethodId)) {
                average = (BigDecimal) map.get("average");
            }
        }
        return average;
    }


    /**
     * 对已经计算出得分的链路进行排序，并返回指定条数的链路数据
     *
     * @param intaLinkScoreVoList
     * @param sortNum
     * @return
     */
    public List<IntaLinkScoreVo> getIntaLinkSort(List<IntaLinkScoreVo> intaLinkScoreVoList, int sortNum) {
        // 使用Collections.sort()方法进行排序
        Collections.sort(intaLinkScoreVoList);

        List<IntaLinkScoreVo> firstElements = new ArrayList<>();
        // 确保列表至少有5个元素
        if (intaLinkScoreVoList.size() >= sortNum) {
            // 返回指定的前*条数据
            for (int i = 0; i < sortNum; i++) {
                firstElements.add(intaLinkScoreVoList.get(i));
            }
        } else {
            firstElements = intaLinkScoreVoList;
        }

        return firstElements;
    }


}
