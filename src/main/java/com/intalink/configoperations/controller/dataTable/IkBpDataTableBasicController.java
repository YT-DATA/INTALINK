package com.intalink.configoperations.controller.dataTable;

import com.intalink.configoperations.domain.dataTable.vo.IkBpDataTableBasicVo;
import com.intalink.configoperations.service.dataTable.IkBpDataTableBasicService;
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
 * @date 2024/3/18
 */
@RestController
@RequestMapping("/dataTable")
public class IkBpDataTableBasicController extends BaseController {

    @Autowired
    private IkBpDataTableBasicService ikBpDataTableBasicService;

    @Autowired
    private EasyExcelUtil easyExcelUtil;

    /**
     * 查询列表
     *
     * @param ikBpDataTableBasicVo
     * @return
     */
    @GetMapping("/lists")
    public TableDataInfo list(IkBpDataTableBasicVo ikBpDataTableBasicVo) {
        startPage();
        List<IkBpDataTableBasicVo> lists = ikBpDataTableBasicService.selectIkBpDataTableBasicLists(ikBpDataTableBasicVo);
        return mySetPage(lists);
    }

    /**
     * 根据模型Id获取对应的数据表
     */
    @GetMapping(value = "/count")
    public AjaxResult count() {
        return success(ikBpDataTableBasicService.count());
    }


    /**
     * 根据模型Id获取对应的数据表
     */
    @GetMapping(value = "/selectAllByModelId/{dataModelId}")
    public AjaxResult selectAllByModelId(@PathVariable("dataModelId") Integer dataModelId) {
        return success(ikBpDataTableBasicService.selectDataTableBasicByModelId(dataModelId));
    }


    /**
     * 根据参数Ids删除信息
     */
    @DeleteMapping(value = "/{dataTableIds}")
    public AjaxResult remove(@PathVariable Integer[] dataTableIds) {
        ikBpDataTableBasicService.deleteDataTableBasicByIds(dataTableIds);
        return success();
    }


    /**
     * 插入/修改数据
     */
    @PostMapping("/insertOrUpdate")
    public AjaxResult insertOrUpdate(@Validated @RequestBody IkBpDataTableBasicVo ikBpDataTableBasic) {
        return ikBpDataTableBasicService.insertOrUpdate(ikBpDataTableBasic);
    }

    /**
     * 导入数据表信息数据
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/tableImport")
    public AjaxResult tableImport(@RequestParam("file") MultipartFile file,@RequestParam("dataModelId") Integer dataModelId) throws Exception {
        // 这里的excel文件可以 为xls或xlsx结尾
        InputStream inputStream = file.getInputStream();
        List<List<String>> result = easyExcelUtil.writeWithoutHead(inputStream);
        Map<String, Object> stringObjectMap = ikBpDataTableBasicService.tableImport(result,dataModelId);
        if (stringObjectMap.size() == 0)
            return AjaxResult.success(1);
        else
            return new AjaxResult(300, "部分数据已经存在", stringObjectMap);
    }


    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) throws IOException {
        ExcelUtil<IkBpDataTableBasicVo> util = new ExcelUtil<IkBpDataTableBasicVo>(IkBpDataTableBasicVo.class);
        util.importTemplateExcel(response, "数据表数据");
    }
}
