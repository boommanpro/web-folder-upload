package com.boommanpro.webfolderupload.upload.vo;

import com.boommanpro.webfolderupload.upload.model.FileDetail;
import com.boommanpro.webfolderupload.upload.type.FileType;
import com.boommanpro.webfolderupload.util.LocalDateTimeFormatUtil;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author boommanpro
 * @date 2020/4/3 15:31
 */
@Data
public class FileDetailVo {

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
    private String size;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 最后修改时间
     */
    private String lastModifyTime;

    public FileDetailVo(FileDetail fileDetail, String parentPath,String previewPath) {
        BeanUtils.copyProperties(fileDetail, this);
        if (fileDetail.getFileType() == FileType.DIRECTORY) {
            this.relativePath = parentPath + relativePath.replace('\\', '/') + "/";
        }else {
            this.relativePath = previewPath + relativePath.replace('\\', '/');
        }
        this.createTime = LocalDateTimeFormatUtil.normalFormat(fileDetail.getCreateTime());
        this.lastModifyTime = LocalDateTimeFormatUtil.normalFormat(fileDetail.getLastModifyTime());
        this.size = fileDetail.getFileType() == FileType.DIRECTORY ? "-" : fileDetail.getSize().toString();
    }
}
