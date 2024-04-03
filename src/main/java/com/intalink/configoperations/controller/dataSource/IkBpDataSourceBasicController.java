package com.intalink.configoperations.controller.dataSource;

import com.intalink.configoperations.domain.dataSource.IkBpDataSourceBasic;
import com.intalink.configoperations.domain.dataSource.vo.IkBpDataSourceBasicVo;
import com.intalink.configoperations.service.dataSource.IkBpDataSourceBasicService;
import com.intalink.configoperations.web.controller.BaseController;
import com.intalink.configoperations.web.domain.AjaxResult;
import com.intalink.configoperations.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.intalink.configoperations.controller.dataColumn.IkBpDataColumnBasicController.mySetPage;

/**
 * @author whx
 * @date 2024/3/17
 */
@RestController
@RequestMapping("/dataSourceBasic")
public class IkBpDataSourceBasicController extends BaseController {
    @Autowired
    private IkBpDataSourceBasicService ikBpDataSourceBasicService;

    /**
     * 查询列表
     *
     * @param ikBpDataSourceBasic
     * @return
     */
    @GetMapping("/lists")
    public TableDataInfo list(IkBpDataSourceBasicVo ikBpDataSourceBasic) {
        startPage();
        List<IkBpDataSourceBasic> lists = ikBpDataSourceBasicService.selectIkBpDataSourceBasicLists(ikBpDataSourceBasic);
        return mySetPage(lists);
    }

    /**
     * 获取数据源数量
     */
    @GetMapping(value = "/count")
    public AjaxResult count() {
        return success(ikBpDataSourceBasicService.count());
    }

    /**
     * 根据参数Id获取详细信息
     */
    @GetMapping(value = "/{dataSourceId}")
    public AjaxResult getInfo(@PathVariable Integer dataSourceId) {
        return success(ikBpDataSourceBasicService.selectDataSourceBasicById(dataSourceId));
    }

    /**
     * 查询全部
     */
    @GetMapping(value = "/selectAll")
    public AjaxResult selectAll() {
        return success(ikBpDataSourceBasicService.selectAll());
    }

    /**
     * 根据参数Ids删除信息
     */
    @DeleteMapping(value = "/{dataSourceIds}")
    public AjaxResult remove(@PathVariable Integer[] dataSourceIds) {
        ikBpDataSourceBasicService.deleteDataSourceBasicByIds(dataSourceIds);
        return success();
    }


    /**
     * 插入/修改数据
     */
    @PostMapping("/insertOrUpdate")
    public AjaxResult insertOrUpdate(@Validated @RequestBody IkBpDataSourceBasic ikBpDataSourceBasic) {
        return toAjax(ikBpDataSourceBasicService.insertOrUpdate(ikBpDataSourceBasic));
    }
}
