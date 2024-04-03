package com.intalink.configoperations.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.*;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SystemOrTableTreatmentMethodEnum implements EnumStringKey {
    //REPLACE("1", "替换"),
    UPDATE("1", "修改"),
    SKIP("2", "跳过");


    @EnumValue
    private final String code;


    private final String description;
    SystemOrTableTreatmentMethodEnum(String val, String description) {
        this.code = val;
        this.description = description;
    }

    @JsonCreator
    public static SystemOrTableTreatmentMethodEnum getByCode(String code) {
        for (SystemOrTableTreatmentMethodEnum value : SystemOrTableTreatmentMethodEnum.values()) {
            if (Objects.equals(code, value.getValue())) {
                return value;
            }
        }
        return null;
    }

    public static List<Map<String,Object>> getList() {
        List<Map<String,Object>> list=new ArrayList<>();
        Map<String,Object> tmp=null;
        for (SystemOrTableTreatmentMethodEnum value : SystemOrTableTreatmentMethodEnum.values()) {
            tmp=new HashMap<>();
            tmp.put("id",value.code);
            tmp.put("name",value.description);
            list.add(tmp);
        }
        return list;
    }

    @Override
    public String getValue() {
        return code;
    }

    @Override
    public String getDesc() {
        return description;
    }
}
