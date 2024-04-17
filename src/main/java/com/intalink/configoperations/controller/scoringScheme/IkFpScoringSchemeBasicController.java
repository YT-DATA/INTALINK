package com.intalink.configoperations.controller.scoringScheme;

import com.intalink.configoperations.domain.evalucationMethodWeight.IkFpEvaluationMethodWeight;
import com.intalink.configoperations.domain.evalucationMethodWeight.vo.IkFpEvaluationMethodWeightVo;
import com.intalink.configoperations.domain.scoringScheme.IkFpScoringSchemeBasic;
import com.intalink.configoperations.domain.scoringScheme.vo.IkFpScoringSchemeBasicVo;
import com.intalink.configoperations.service.scoringScheme.IkFpScoringSchemeBasicService;
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
@RequestMapping("/scoringScheme")
public class IkFpScoringSchemeBasicController extends BaseController {

    @Autowired
    private IkFpScoringSchemeBasicService ikFpScoringSchemeBasicService;

    @GetMapping("/lists")
    public TableDataInfo list(IkFpScoringSchemeBasicVo ikFpScoringSchemeBasicVo) {
        startPage();
        List<IkFpScoringSchemeBasicVo> lists = ikFpScoringSchemeBasicService.selectScoringSchemeLists(ikFpScoringSchemeBasicVo);
        return getDataTable(lists);
    }

    /**
     * 新增或修改（true新增，false修改）
     *
     * @param ikFpScoringSchemeBasic
     * @param insertOrUpdate
     * @return
     */
    @PostMapping("/insertOrUpdate")
    public AjaxResult insertOrUpdate(@Validated @RequestBody IkFpScoringSchemeBasic ikFpScoringSchemeBasic, @RequestParam("insertOrUpdate") Boolean insertOrUpdate) {
        return ikFpScoringSchemeBasicService.insertOrUpdate(ikFpScoringSchemeBasic,insertOrUpdate);
    }


    @GetMapping("/scoringSchemeById")
    public AjaxResult scoringSchemeById(@RequestParam("scoringSchemeId") Integer scoringSchemeId) {
        List<IkFpEvaluationMethodWeightVo> lists = ikFpScoringSchemeBasicService.selectScoringSchemeById(scoringSchemeId);
        return success(lists);
    }

    /**
     * 新增或修改（true新增，false修改）
     *
     * @param ikFpEvaluationMethodWeight
     * @param scoringSchemeId
     * @return
     */
    @PostMapping("/insert")
        public AjaxResult insert(@Validated @RequestBody List<IkFpEvaluationMethodWeight> ikFpEvaluationMethodWeight, @RequestParam("scoringSchemeId") Integer scoringSchemeId) {
        return ikFpScoringSchemeBasicService.insert(ikFpEvaluationMethodWeight,scoringSchemeId);
    }

    /**
     * 根据参数Ids删除信息
     * sureOrNot true确认删除，false不删除
     */
    @DeleteMapping(value = "/{scoringSchemeIds}")
    public AjaxResult remove(@PathVariable Integer[] scoringSchemeIds,@RequestParam("sureOrNot") Boolean sureOrNot) {
        return ikFpScoringSchemeBasicService.deleteScoringSchemeBasicByIds(scoringSchemeIds,sureOrNot);
    }


}
