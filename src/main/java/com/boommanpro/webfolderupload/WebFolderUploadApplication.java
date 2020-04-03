package com.boommanpro.webfolderupload;

import com.boommanpro.webfolderupload.bindenvironment.annotation.EnableBindEnvironmentProperties;
import com.boommanpro.webfolderupload.config.UploadFolderIndexProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author boommanpro
 * @date 2020/4/3 10:50
 */
@SpringBootApplication
@EnableConfigurationProperties(UploadFolderIndexProperties.class)
@EnableBindEnvironmentProperties(UploadFolderIndexProperties.class)
public class WebFolderUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFolderUploadApplication.class, args);
    }

}
