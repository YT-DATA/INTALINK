package com.intalink.configoperations.enums;

/**
 * 评分策略
 */
public enum ScoreStrategy {

    SUM("SUM", "加权求和"), AVERAGE("AVERAGE", "加权平均"), EQUALRATIO("EQUALRATIO","等比法"),MEANVALUE("MEANVALUE", "均值法");

    private String code;
    private String desc;

    ScoreStrategy(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
