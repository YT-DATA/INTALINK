package com.intalink.configoperations.controller.eigenvalue;

import com.intalink.configoperations.domain.dataSource.IkBpDataSourceBasic;
import com.intalink.configoperations.domain.dataSource.SDsnaDsSettingNew;
import com.intalink.configoperations.mapper.dataSource.IkBpDataSourceBasicMapper;
import com.intalink.configoperations.service.eigenvalue.EigenvalueService;
import com.intalink.configoperations.utils.DataSourceUtil;
import com.intalink.configoperations.web.controller.BaseController;
import com.intalink.configoperations.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.io.InputStream;
import java.util.Map;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author whx
 * @date 2024/7/31
 */
@RestController
@RequestMapping("/eigenvalue")
public class EigenvalueController extends BaseController {
    @Autowired
    private EigenvalueService eigenvalueService;

    /**
     * 将指定数据源数据存入Redis
     */
    @PostMapping(value = "/putData")
    public AjaxResult putData(@RequestBody List<Integer> dataSourceId) {
        try {
            eigenvalueService.putData(dataSourceId);
        } catch (OutOfMemoryError error) {
            //自定义返回
            return new AjaxResult(500, "内存溢出导致的错误", null);
        }
        return success();
    }

}
