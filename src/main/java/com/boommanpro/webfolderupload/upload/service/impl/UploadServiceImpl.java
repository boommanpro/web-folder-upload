package com.boommanpro.webfolderupload.upload.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.boommanpro.webfolderupload.config.UploadFolderIndexProperties;
import com.boommanpro.webfolderupload.upload.model.FileDetail;
import com.boommanpro.webfolderupload.upload.service.UploadService;
import com.boommanpro.webfolderupload.upload.util.UploadFileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author boommanpro
 * @date 2020/4/3 11:31
 */
@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadFolderIndexProperties uploadFolderIndexProperties;

    @Override
    public List<FileDetail> list(String path) {
        List<File> fileList = UploadFileUtils.list(path);
        return transform2FileDetail(fileList, path);
    }

    private List<FileDetail> transform2FileDetail(List<File> fileList, String path) {
        return fileList.stream()
                .map(file -> new FileDetail(file, path))
                .collect(Collectors.toList());
    }

    @Override
    public void uploadFile(MultipartFile file, String path) {
        upload(file,path);
    }

    @Override
    public void uploadDirectory(List<MultipartFile> folder, String path) {
        for (MultipartFile file : folder) {
           upload(file,path);
        }
    }

    private void upload(MultipartFile file, String path) {
        File to = new File(uploadFolderIndexProperties.getDirAbsolutePath() + path + file.getOriginalFilename());
        try {
            if (!to.exists()) {
                FileUtils.forceMkdir(to);
            }
            file.transferTo(to);
        } catch (IOException e) {
            log.error("生成文件失败", e);
        }
    }
}
