package com.intalink.configoperations.controller.system;

import com.intalink.configoperations.domain.system.vo.IkBpSystemBasicVo;
import com.intalink.configoperations.service.system.IkBpSystemBasicService;
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
@RequestMapping("/systemBasic")
public class IkBpSystemBasicController extends BaseController {
    @Autowired
    private IkBpSystemBasicService ikBpSystemBasicService;


    @GetMapping("/lists")
    public TableDataInfo list(IkBpSystemBasicVo ikBpSystemBasicVo) {
        startPage();
        List<IkBpSystemBasicVo> lists = ikBpSystemBasicService.selectSystemBasicLists(ikBpSystemBasicVo);
        return mySetPage(lists);
    }

    /**
     * 根据参数Id获取详细信息
     */
    @GetMapping(value = "/{systemId}")
    public AjaxResult getInfo(@PathVariable Integer systemId) {
        return success(ikBpSystemBasicService.selectSystemBasicById(systemId));
    }

    /**
     * 获取系统数量
     */
    @GetMapping(value = "/count")
    public AjaxResult count() {
        return success(ikBpSystemBasicService.count());
    }


    /**
     * 根据参数Ids删除信息
     */
    @DeleteMapping(value = "/{systemIds}")
    public AjaxResult remove(@PathVariable Integer[] systemIds) {
        return success(ikBpSystemBasicService.deleteSystemBasicByIds(systemIds));
    }


    /**
     * 插入/修改数据
     */
    @PostMapping("/insertOrUpdate")
    public AjaxResult insertOrUpdate(@Validated @RequestBody IkBpSystemBasicVo ikBpSystemBasicVo) {
        return ikBpSystemBasicService.insertOrUpdate(ikBpSystemBasicVo);
    }

}
