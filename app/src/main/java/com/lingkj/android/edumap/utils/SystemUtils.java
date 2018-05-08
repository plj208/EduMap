package com.lingkj.android.edumap.utils;

import java.io.File;

public class SystemUtils  {


    /**
     * 获取文件的大小
     * @param file
     * @return
     */
    public static long getFolderSize(File file) {
        long result=0;
        if (file.isDirectory()) {
            File[] childer=file.listFiles();
            for (File singleFile:childer) {
                result+=getFolderSize(singleFile);
            }
        }else{
            result+=file.length();
        }
        return result;
    }

}
