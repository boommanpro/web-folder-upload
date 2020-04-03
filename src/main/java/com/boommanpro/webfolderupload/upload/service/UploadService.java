package com.boommanpro.webfolderupload.upload.service;

import java.util.List;

import com.boommanpro.webfolderupload.upload.model.FileDetail;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author boommanpro
 * @date 2020/4/3 11:14
 */
public interface UploadService {

    /**
     * 文件信息列表
     * @param path 地址
     * @return list fileDetail
     */
    List<FileDetail> list(String path);

    /**
     * 上传文件
     * @param file 文件
     * @param path 地址
     */
    void uploadFile(MultipartFile file, String path);

    /**
     * 上传文件夹
     * @param folder 文件夹
     * @param path 地址
     */
    void uploadDirectory(List<MultipartFile> folder, String path);
}
