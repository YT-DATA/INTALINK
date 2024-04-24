package com.intalink.configoperations.controller.pdm;

import com.intalink.configoperations.service.pdm.IPdmHandleService;
import com.intalink.configoperations.web.controller.BaseController;
import com.intalink.configoperations.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;

@Controller
@RequestMapping({"/pdm"})
public class PdmController extends BaseController {
    @Autowired
    private IPdmHandleService iPdmHandleService;

    public PdmController() {
    }

    /**
     * pdm导入
     * @param file
     * @param modelId
     * @return
     * @throws Exception
     */
    @PostMapping({"upload"})
    @ResponseBody
    public AjaxResult upload(@RequestParam MultipartFile file, @RequestParam String modelId) throws Exception {
        try{
            InputStream inputStream = file.getInputStream();
            iPdmHandleService.autoSaveFromPdm(inputStream, modelId);
        }
        catch (OutOfMemoryError error)
        {
            //自定义返回
            return new AjaxResult(500, "内存溢出导致的错误",null);
        }
        return success();
    }
}
