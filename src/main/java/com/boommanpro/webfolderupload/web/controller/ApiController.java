package com.boommanpro.webfolderupload.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.boommanpro.webfolderupload.common.ResultVo;
import com.boommanpro.webfolderupload.upload.service.UploadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author boommanpro
 * @date 2020/4/3 18:03
 */
@Controller
@RequestMapping("api")
public class ApiController {

    private final UploadService uploadService;

    public ApiController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @ResponseBody
    @PostMapping("uploadFile")
    public ResultVo<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam  String path) {
        uploadService.uploadFile(file, path);
        return ResultVo.success();
    }

    @PostMapping("uploadDirectory")
    public String uploadDirectory(@RequestParam("folder") List<MultipartFile> folder, @RequestParam  String path,HttpServletRequest request){
        uploadService.uploadDirectory(folder, path);
        return "redirect:"+request.getHeader("Referer");
    }

}
