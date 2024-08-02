package com.intalink.configoperations.domain.intaScore.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 最终评分（评分方案评分后）实体
 */
@Data
public class IntaLinkScoreVo implements Comparable<IntaLinkScoreVo> {


    /**
     * 链路Id
     */
    public String intaLinkId;

    /**
     * 方案ID
     */
    public Integer scoringSchemeId;

    /**
     * 链路得分
     */
    public BigDecimal score;

    // 实现Comparable接口的compareTo方法，用于比较两个对象的score字段
    @Override
    public int compareTo(IntaLinkScoreVo other) {
        // 使用BigDecimal的compareTo方法进行比较
        //this.score.compareTo(other.score) 为升序排序
        //other.score.compareTo(this.score) 为降序排序
        return other.score.compareTo(this.score);
    }

}
