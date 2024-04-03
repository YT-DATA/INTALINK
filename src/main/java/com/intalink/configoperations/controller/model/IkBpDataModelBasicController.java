package com.intalink.configoperations.controller.model;

import com.intalink.configoperations.domain.model.IkBpDataModelBasic;
import com.intalink.configoperations.domain.model.vo.IkBpDataModelBasicVo;
import com.intalink.configoperations.service.model.IkBpDataModelBasicService;
import com.intalink.configoperations.utils.EasyExcelUtil;
import com.intalink.configoperations.utils.poi.ExcelUtil;
import com.intalink.configoperations.web.controller.BaseController;
import com.intalink.configoperations.web.domain.AjaxResult;
import com.intalink.configoperations.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import static com.intalink.configoperations.controller.dataColumn.IkBpDataColumnBasicController.mySetPage;

/**
 * @author whx
 * @date 2024/3/19
 */
@RestController
@RequestMapping("/dataModel")
public class IkBpDataModelBasicController extends BaseController {

    @Autowired
    private IkBpDataModelBasicService ikBpDataModelBasicService;

    @Autowired
    private EasyExcelUtil easyExcelUtil;
    /**
     * 查询列表
     *
     * @param ikBpDataModelBasicVo
     * @return
     */
    @GetMapping("/lists")
    public TableDataInfo list(IkBpDataModelBasicVo ikBpDataModelBasicVo) {
        startPage();
        List<IkBpDataModelBasicVo> lists = ikBpDataModelBasicService.selectIkBpDataTableBasicLists(ikBpDataModelBasicVo);
        return mySetPage(lists);
    }

    @GetMapping(value = "/selectAll")
    public AjaxResult selectAll() {
        return success(ikBpDataModelBasicService.selectAll());
    }

    /**
     * 根据模型Id获取书记表和项的信息
     */
    @GetMapping(value = "/selectDataModelById/{dataModelIds}")
    public AjaxResult selectDataModelById(@PathVariable Integer[] dataModelIds) {
        return success(ikBpDataModelBasicService.selectDataModelById(dataModelIds));
    }

    /**
     * 根据参数Ids删除信息
     */
    @GetMapping(value = "/count")
    public AjaxResult count() {
        return success(ikBpDataModelBasicService.count());
    }

    /**
     * 根据参数Ids删除信息
     */
    @DeleteMapping(value = "/{dataModelIds}")
    public AjaxResult remove(@PathVariable Integer[] dataModelIds) {
        ikBpDataModelBasicService.deleteDataTableBasicByIds(dataModelIds);
        return success();
    }


    /**
     * 插入/修改数据
     */
    @PostMapping("/insertOrUpdate")
    public AjaxResult insertOrUpdate(@Validated @RequestBody IkBpDataModelBasic ikBpDataModelBasic) {
        return toAjax(ikBpDataModelBasicService.insertOrUpdate(ikBpDataModelBasic));
    }

    /**
     * 导入模型信息数据
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/modelImport")
    public AjaxResult modelImport(@RequestParam("file") MultipartFile file) throws Exception {
        // 这里的excel文件可以 为xls或xlsx结尾
        InputStream inputStream = file.getInputStream();
        List<List<String>> result = easyExcelUtil.writeWithoutHead(inputStream);
        Map<String, Object> stringObjectMap = ikBpDataModelBasicService.modelImport(result);
        if (stringObjectMap.size() == 0)
            return AjaxResult.success(1);
        else
            return new AjaxResult(300, "部分模型已经存在", stringObjectMap);
    }

    /**
     * 导出模板
     * @param response
     * @throws IOException
     */
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) throws IOException {
        ExcelUtil<IkBpDataModelBasicVo> util = new ExcelUtil<IkBpDataModelBasicVo>(IkBpDataModelBasicVo.class);
        util.importTemplateExcel(response, "数据表数据");
    }
}
