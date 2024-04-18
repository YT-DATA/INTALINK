package com.intalink.configoperations.controller.relationshipInput;

import com.intalink.configoperations.constant.HttpStatus;
import com.intalink.configoperations.domain.relationshipInput.vo.IkRpDataTableRelationVo;
import com.intalink.configoperations.service.relationshipInput.IkRpDataTableRelationService;
import com.intalink.configoperations.web.controller.BaseController;
import com.intalink.configoperations.web.domain.AjaxResult;
import com.intalink.configoperations.web.page.PageDomain;
import com.intalink.configoperations.web.page.TableDataInfo;
import com.intalink.configoperations.web.page.TableSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author whx
 * @date 2024/3/19
 */
@RestController
@RequestMapping("/dataTableRelation")
public class IkRpDataTableRelationController extends BaseController {

    @Autowired
    private IkRpDataTableRelationService ikRpDataTableRelationService;

    /**
     * 查询列表
     * @param ikRpDataTableRelationVo
     * @return
     */
    @PostMapping("/lists")
    public TableDataInfo list(@RequestBody IkRpDataTableRelationVo ikRpDataTableRelationVo) {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        List<IkRpDataTableRelationVo> lists = ikRpDataTableRelationService.selectListByData(ikRpDataTableRelationVo);
        return mySetPage(lists, pageNum, pageSize);
    }

    /**
     * 获取关联关系列表
     *
     * @return
     */
    @GetMapping(value = "/selectTableRelation")
    public AjaxResult selectTableRelation() {
        return success(ikRpDataTableRelationService.selectTableRelation());
    }

    /**
     * 获取模型列表
     *
     * @return
     */
    @GetMapping(value = "/selectModel")
    public AjaxResult selectModel() {
        return success(ikRpDataTableRelationService.selectModel());
    }

    /**
     * 根据模型获取表
     *
     * @return
     */
    @GetMapping(value = "/selectTable")
    public AjaxResult selectTable(@RequestParam("dataModelId") Integer dataModelId) {
        return success(ikRpDataTableRelationService.selectTable(dataModelId));
    }

    /**
     * 根据模型和表获取数据项
     *
     * @return
     */
    @GetMapping(value = "/selectColumn")
    public AjaxResult selectColumn(@RequestParam("dataTableId") Integer dataTableId) {
        return success(ikRpDataTableRelationService.selectColumn(dataTableId));
    }

//    /**
//     * 获取绑定关系主模型列表
//     *
//     * @return
//     */
//    @GetMapping(value = "/selectModelBind")
//    public AjaxResult selectModelBind() {
//        return success(ikRpDataTableRelationService.selectModelBind());
//    }
//
//    /**
//     * 根据模型获取绑定关系主表
//     *
//     * @return
//     */
//    @GetMapping(value = "/selectTableBind")
//    public AjaxResult selectTableBind(@RequestParam("dataModelId") Integer dataModelId) {
//        return success(ikRpDataTableRelationService.selectTableBind(dataModelId));
//    }
//
//    /**
//     * 根据模型和表获取绑定关系主数据项
//     *
//     * @return
//     */
//    @GetMapping(value = "/selectColumnBind")
//    public AjaxResult selectColumnBind(@RequestParam("dataTableId") Integer dataTableId) {
//        return success(ikRpDataTableRelationService.selectColumnBind(dataTableId));
//    }
//
//    /**
//     * 获取绑定关系主模型列表
//     *
//     * @return
//     */
//    @GetMapping(value = "/selectRModelBind")
//    public AjaxResult selectRModelBind() {
//        return success(ikRpDataTableRelationService.selectRModelBind());
//    }
//
//    /**
//     * 根据模型获取绑定关系主表
//     *
//     * @return
//     */
//    @GetMapping(value = "/selectRTableBind")
//    public AjaxResult selectRTableBind(@RequestParam("dataModelId") Integer dataModelId) {
//        return success(ikRpDataTableRelationService.selectRTableBind(dataModelId));
//    }
//
//    /**
//     * 根据模型和表获取绑定关系主数据项
//     *
//     * @return
//     */
//    @GetMapping(value = "/selectRColumnBind")
//    public AjaxResult selectRColumnBind(@RequestParam("dataTableId") Integer dataTableId) {
//        return success(ikRpDataTableRelationService.selectRColumnBind(dataTableId));
//    }

    /**
     * 根据参数Ids删除信息
     */
    @DeleteMapping(value = "/{dataTableRelationIds}")
    public AjaxResult remove(@PathVariable Integer[] dataTableRelationIds) {
        return success(ikRpDataTableRelationService.deleteRelationBasicByIds(dataTableRelationIds));
    }


    /**
     * 新增或修改（true新增，false修改）
     *
     * @param ikRpDataTableRelationVos
     * @param insertOrUpdate
     * @return
     */
    @PostMapping("/insertOrUpdate")
    public AjaxResult insertOrUpdate(@Validated @RequestBody List<IkRpDataTableRelationVo> ikRpDataTableRelationVos, @RequestParam("insertOrUpdate") Boolean insertOrUpdate) {
        return ikRpDataTableRelationService.insertOrUpdate(ikRpDataTableRelationVos, insertOrUpdate);
    }

    /**
     * 分页
     *
     * @param list
     * @param pageNum
     * @param pageSize
     * @return
     */
    public static List myStartPage(List list, Integer pageNum, Integer pageSize) {
        if (list == null) {
            return null;
        }
        if (list.size() == 0) {
            return null;
        }
        Integer count = list.size();//
        Integer pageCount = 0;//
        if (count % pageSize == 0) {
            pageCount = count / pageSize;
        } else {
            pageCount = count / pageSize + 1;
        }
        int fromIndex = 0;//
        int toIndex = 0;//
        if (!pageNum.equals(pageCount)) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = count;
        }
        List pageList = list.subList(fromIndex, toIndex);
        return pageList;
    }

    /**
     * 分页
     * @param list
     * @param pageNum
     * @param pageSize
     * @return
     */
    public static TableDataInfo mySetPage(List list, Integer pageNum, Integer pageSize) {
        int num = list.size();
        list = (List) list.stream().skip((pageNum - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setRows(list);
        rspData.setTotal(num);
        return rspData;
    }
}
