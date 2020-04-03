package com.boommanpro.webfolderupload.upload.service;

import java.util.List;

import com.boommanpro.webfolderupload.upload.model.FileDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wangqimeng
 * @date 2020/4/3 11:42
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UploadServiceTest {

    @Autowired
    private UploadService uploadService;

    @Test
    void list() {
        List<FileDetail> list = uploadService.list("123");
        log.info("文件列表{}", list);
    }
}