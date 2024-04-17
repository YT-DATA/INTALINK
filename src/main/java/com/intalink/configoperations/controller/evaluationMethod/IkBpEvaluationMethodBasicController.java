package com.intalink.configoperations.controller.evaluationMethod;

import com.intalink.configoperations.domain.evaluationMethod.IkBpEvaluationMethodBasic;
import com.intalink.configoperations.domain.evaluationMethod.vo.IkBpEvaluationMethodBasicVo;
import com.intalink.configoperations.service.evaluationMethod.IkBpEvaluationMethodBasicService;
import com.intalink.configoperations.web.controller.BaseController;
import com.intalink.configoperations.web.domain.AjaxResult;
import com.intalink.configoperations.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author whx
 * @date 2024/4/12
 */
@RestController
@RequestMapping("/evaluationMethod")
public class IkBpEvaluationMethodBasicController extends BaseController {

    @Autowired
    private IkBpEvaluationMethodBasicService ikBpEvaluationMethodBasicService;

    @GetMapping("/lists")
    public TableDataInfo list(IkBpEvaluationMethodBasicVo ikBpEvaluationMethodBasicVo) {
        startPage();
        List<IkBpEvaluationMethodBasicVo> lists = ikBpEvaluationMethodBasicService.selectEvaluationMethodBasicLists(ikBpEvaluationMethodBasicVo);
        return getDataTable(lists);
    }

    /**
     * 新增或修改（true新增，false修改）
     *
     * @param ikBpEvaluationMethodBasic
     * @param insertOrUpdate
     * @return
     */
    @PostMapping("/insertOrUpdate")
    public AjaxResult insertOrUpdate(@Validated @RequestBody IkBpEvaluationMethodBasic ikBpEvaluationMethodBasic,@RequestParam("insertOrUpdate") Boolean insertOrUpdate) {
        return ikBpEvaluationMethodBasicService.insertOrUpdate(ikBpEvaluationMethodBasic,insertOrUpdate);
    }

    @GetMapping("/selectAll")
    public AjaxResult selectAll() {
        List<IkBpEvaluationMethodBasicVo> lists = ikBpEvaluationMethodBasicService.selectAll();
        return success(lists);
    }

    /**
     * 根据参数Ids删除信息
     */
    @DeleteMapping(value = "/{evaluationMethodIds}")
    public AjaxResult remove(@PathVariable Integer[] evaluationMethodIds) {
        return ikBpEvaluationMethodBasicService.deleteEvaluationMethodBasicByIds(evaluationMethodIds);
    }

}
