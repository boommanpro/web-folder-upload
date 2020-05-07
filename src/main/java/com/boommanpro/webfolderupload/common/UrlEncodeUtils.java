package com.boommanpro.webfolderupload.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author wangqimeng
 * @date 2020/5/7 11:21
 */
@Slf4j
@Component
public class UrlEncodeUtils {

    private UrlEncodeUtils() {
    }

    public static String encode(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(String.format("UrlEncode编码错误,原url:[%s]", url), e);
            throw new RuntimeException(String.format("UrlEncode编码错误,原url:[%s]", url));
        }
    }
}
