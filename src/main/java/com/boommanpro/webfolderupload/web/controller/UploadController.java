package com.boommanpro.webfolderupload.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

import com.boommanpro.webfolderupload.upload.service.UploadService;
import com.boommanpro.webfolderupload.upload.vo.FileDetailVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author boommanpro
 * @date 2020/4/3 11:13
 */
@Slf4j
@Controller
@RequestMapping(UploadController.UPLOAD_PATH)
public class UploadController {

    public static final String ROOT_PATH = "/";

    public static final String UPLOAD_PATH = "/upload";

    public static final String PREVIEW_PATH = "/preview";

    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @GetMapping("**")
    public String index(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        String path = request.getRequestURI().substring(UPLOAD_PATH.length());
        path = URLDecoder.decode(path, "UTF-8");
        //路径信息
        model.addAttribute("path", path);
        if (!path.endsWith(ROOT_PATH)) {
            return "error/pathError";
        }
        String parentPath = getParentPath(path);
        List<FileDetailVo> files = uploadService.list(path).stream()
                .map(fileDetail -> new FileDetailVo(fileDetail, UPLOAD_PATH + ROOT_PATH, PREVIEW_PATH + ROOT_PATH))
                .collect(Collectors.toList());
        //文件信息
        model.addAttribute("files", files);
        //父级目录
        model.addAttribute("parentPath", UPLOAD_PATH + parentPath);
        model.addAttribute("preViewPath", PREVIEW_PATH + path);
        return "upload/index";
    }

    private String getParentPath(String path) {
        if (ROOT_PATH.equals(path)) {
            return ROOT_PATH;
        }
        return getParent(path) + ROOT_PATH;
    }

    public String getParent(String path) {
        path = path.substring(0, path.length() - 1);
        int index = path.lastIndexOf(ROOT_PATH);
        return path.substring(0, index);
    }

}
