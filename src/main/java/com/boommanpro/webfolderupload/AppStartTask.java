package com.boommanpro.webfolderupload;

import com.boommanpro.webfolderupload.config.UploadFolderIndexProperties;
import com.boommanpro.webfolderupload.upload.util.UploadFileUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author boommanpro
 * @date 2020/4/3 11:15
 */
@Component
public class AppStartTask implements ApplicationRunner {

    private final UploadFolderIndexProperties folderIndexProperties;

    public AppStartTask(UploadFolderIndexProperties folderIndexProperties) {
        this.folderIndexProperties = folderIndexProperties;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        UploadFileUtils.init(folderIndexProperties.getDirAbsolutePath());
    }
}
