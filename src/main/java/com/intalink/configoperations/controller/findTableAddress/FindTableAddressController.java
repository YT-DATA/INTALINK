package com.intalink.configoperations.controller.findTableAddress;

import com.alibaba.fastjson2.JSONArray;
import com.intalink.configoperations.service.findTableAddress.FindTableAddressService;
import com.intalink.configoperations.web.controller.BaseController;
import com.intalink.configoperations.web.domain.AjaxResult;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/findTableAddress")
public class FindTableAddressController extends BaseController {

    @Autowired
    private FindTableAddressService findTableAddressService;

    /**
     * 查询路径
     *
     * @param
     * @return
     */
    @GetMapping("/getAddress")
    public AjaxResult getAddress(String columns ) {
        if(ObjectUtils.isEmpty(columns)){
            return error("参数错误");
        }
        JSONArray lists = findTableAddressService.paths(columns.split(","));
        return success(lists);
    }

}
