package com.intalink.configoperations.service.findTableAddress;

import com.alibaba.fastjson2.JSONArray;

public interface FindTableAddressService {
    JSONArray paths(String[] columns);
}
