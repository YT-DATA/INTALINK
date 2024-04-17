package com.intalink.configoperations.controller.dataTableRelationBasic;

import com.intalink.configoperations.service.relationshipInput.IkRpDataTableRelationService;
import com.intalink.configoperations.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author whx
 * @date 2024/3/19
 */
@RestController
@RequestMapping("/dataTableRelationBasic")
public class IkBpDataTableRelationBasicController extends BaseController {

    @Autowired
    private IkRpDataTableRelationService ikRpDataTableRelationService;

    // todo 缺少CRUD

}
