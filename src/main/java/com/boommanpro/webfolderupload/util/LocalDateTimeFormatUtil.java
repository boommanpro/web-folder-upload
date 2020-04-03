package com.boommanpro.webfolderupload.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author boommanpro
 * @date 2020/4/3 15:27
 */

public class LocalDateTimeFormatUtil {

    private static final DateTimeFormatter NORMAL_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private LocalDateTimeFormatUtil() {
    }

    public static String normalFormat(LocalDateTime now) {
        return now.format(NORMAL_FORMAT);
    }
}
