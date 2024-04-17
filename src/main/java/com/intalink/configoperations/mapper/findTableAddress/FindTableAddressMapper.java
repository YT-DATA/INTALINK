package com.intalink.configoperations.mapper.findTableAddress;

import java.util.List;
import java.util.Map;

public interface FindTableAddressMapper {

     List<Map<String, Object>> findTableRelationList();
     String selectTableByColumnId(String columnId);

     int insertLinkResult(String toJSONString);
}
