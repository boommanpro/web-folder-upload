package com.boommanpro.webfolderupload.upload.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author boommanpro
 * @date 2020/4/3 11:14
 */
public class UploadFileUtils {

    private static String absolutePath;

    private static  int absolutePathLength;

    public static void init(String absolutePath) {
        UploadFileUtils.absolutePath = absolutePath;
        UploadFileUtils.absolutePathLength = absolutePath.length();
    }

    private UploadFileUtils() {
    }

    public static List<File> list(String path) {
        File file = new File(absolutePath + path);
        File[] files = file.listFiles();
        if (files == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(files);
    }

    public static String transform2RelatePath(String absolutePath) {
        return absolutePath.substring(absolutePathLength);
    }
}
