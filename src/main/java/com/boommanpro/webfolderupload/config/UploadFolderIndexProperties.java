package com.boommanpro.webfolderupload.config;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * @author boommanpro
 * @date 2020/4/3 10:58
 */
@Data
@Validated
@ConfigurationProperties(value = "upload.folder")
public class UploadFolderIndexProperties {

    /**
     * 一般情况下是nginx server
     * e.g. http://localhost:80/
     */
    @NotBlank(message = "预览服务地址不能为空")
    private String previewServer = "http://localhost:80/";

    /**
     * 文件夹绝对路径
     * e.g. /usr/local/nginx/html/
     */
    @NotBlank(message = "项目绝对路径不能为空")
    private String dirAbsolutePath = "/usr/local/nginx/html/";
}
