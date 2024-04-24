package com.intalink.configoperations.controller.dataColumn;

import com.github.pagehelper.PageInfo;
import com.intalink.configoperations.domain.dataColumn.vo.IkBpDataColumnBasicVo;
import com.intalink.configoperations.service.dataColumn.IkBpDataColumnBasicService;
import com.intalink.configoperations.utils.EasyExcelUtil;
import com.intalink.configoperations.utils.poi.ExcelUtil;
import com.intalink.configoperations.web.controller.BaseController;
import com.intalink.configoperations.web.domain.AjaxResult;
import com.intalink.configoperations.web.page.PageDomain;
import com.intalink.configoperations.web.page.TableDataInfo;
import com.intalink.configoperations.web.page.TableSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author whx
 * @date 2024/3/19
 */
@RestController
@RequestMapping("/dataColumn")
public class IkBpDataColumnBasicController extends BaseController {

    @Autowired
    private IkBpDataColumnBasicService ikBpDataColumnBasicService;

    @Autowired
    private EasyExcelUtil easyExcelUtil;

    /**
     * 查询列表
     *
     * @param ikBpDataColumnBasicVo
     * @return
     */
    @GetMapping("/lists")
    public TableDataInfo list(IkBpDataColumnBasicVo ikBpDataColumnBasicVo) {
        startPage();
        List<IkBpDataColumnBasicVo> lists = ikBpDataColumnBasicService.selectIkBpDataColumnBasicLists(ikBpDataColumnBasicVo);
        return mySetPage(lists);
    }

    /**
     * 根据参数Id获取详细信息
     */
    @GetMapping(value = "/{dataColumnId}")
    public AjaxResult getInfo(@PathVariable Integer dataColumnId) {
        return success(ikBpDataColumnBasicService.selectDataColumnBasicById(dataColumnId));
    }

    /**
     * 根据参数Ids删除信息
     */
    @DeleteMapping(value = "/{dataColumnIds}")
    public AjaxResult remove(@PathVariable Integer[] dataColumnIds) {
        ikBpDataColumnBasicService.deleteDataColumnBasicByIds(dataColumnIds);
        return success();
    }


    /**
     * 插入/修改数据
     */
    @PostMapping("/insertOrUpdate")
    public AjaxResult insertOrUpdate(@Validated @RequestBody IkBpDataColumnBasicVo ikBpDataColumnBasic) {
        return toAjax(ikBpDataColumnBasicService.insertOrUpdate(ikBpDataColumnBasic));
    }
    /**
     * 导入数据项信息数据
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/columnImport")
    public AjaxResult columnImport(@RequestParam("file") MultipartFile file , @RequestParam("dataModelId") Integer dataModelId, @RequestParam("dataTableId") Integer dataTableId) throws Exception {
        // 这里的excel文件可以 为xls或xlsx结尾
        InputStream inputStream = file.getInputStream();
        List<List<String>> result = easyExcelUtil.writeWithoutHead(inputStream);
        Map<String, Object> stringObjectMap = ikBpDataColumnBasicService.columnImport(result,dataModelId,dataTableId);
        if (stringObjectMap.size() == 0)
            return AjaxResult.success(1);
        else
            return new AjaxResult(300, "部分数据已经存在", stringObjectMap);
    }

    /**
     * 导入数据项
     * @param response
     * @throws IOException
     */
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) throws IOException {
        ExcelUtil<IkBpDataColumnBasicVo> util = new ExcelUtil<IkBpDataColumnBasicVo>(IkBpDataColumnBasicVo.class);
        util.importTemplateExcel(response, "数据项数据");
    }

    /**
     * 初始化分页
     * @param list
     * @param pageNum
     * @param pageSize
     * @return
     */
    public static List myStartPage(List list, Integer pageNum, Integer pageSize){
        if(list ==null){
            return null;
        }
        if(list.size()==0){
            return null;
        }
        Integer count = list.size();//
        Integer pageCount =0;//
        if(count % pageSize ==0){
            pageCount = count / pageSize;
        }else{
            pageCount = count / pageSize +1;
        }
        int fromIndex =0;//
        int toIndex =0;//
        if(!pageNum.equals(pageCount)){
            fromIndex =(pageNum -1)* pageSize;
            toIndex = fromIndex + pageSize;
        }else{
            fromIndex =(pageNum -1)* pageSize;
            toIndex = count;
        }
        List pageList = list.subList(fromIndex,toIndex);
        return pageList;
    }

    /**
     * 分页
     * @param list
     * @return
     */
    public static TableDataInfo mySetPage(List list){
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        TableDataInfo rspData =new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(myStartPage(list, pageNum, pageSize));
        rspData.setTotal(new PageInfo<>(list).getTotal());
        return rspData;

    }
}
