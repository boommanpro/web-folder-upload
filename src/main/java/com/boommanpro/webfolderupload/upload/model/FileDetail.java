package com.boommanpro.webfolderupload.upload.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.boommanpro.webfolderupload.upload.type.FileType;
import com.boommanpro.webfolderupload.upload.util.UploadFileUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author boommanpro
 * @date 2020/4/3 11:23
 */
@Data
@Slf4j
public class FileDetail {

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件类型
     */
    private FileType fileType;

    /**
     * 路径
     */
    private String showPath;

    /**
     * 相对路径
     */
    private String relativePath;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后修改时间
     */
    private LocalDateTime lastModifyTime;

    public FileDetail(File file, String path) {
        this.fileName = file.getName();
        this.fileType = file.isDirectory() ? FileType.DIRECTORY : FileType.FILE;
        this.relativePath = UploadFileUtils.transform2RelatePath(file.getPath());
        this.showPath = relativePath.substring(path.length()-1);
        this.size = file.length();
        try {
            BasicFileAttributes att = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            this.createTime = LocalDateTime.ofInstant(att.creationTime().toInstant(), ZoneId.systemDefault());
            this.lastModifyTime = LocalDateTime.ofInstant(att.lastModifiedTime().toInstant(), ZoneId.systemDefault());
        } catch (IOException e) {
            log.error("error:", e);
        }
    }
}
