package com.xyxy.appcenter.tool;

import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2015/10/9.
 */
public class FileUtils {
    public static final String CACHE="cache";
    public static final String ROOT="appcenter";
    public static final String ICON="icon";
    public static final String DOWNLOAD_DIR = "download";

    public static File getIconDir(){
        return getDir(ICON);
    }
    public static File getCacheDir() {
        return getDir(CACHE);
    }
    /** 获取下载目录 */
    public static File getDownloadDir() {

        return getDir(DOWNLOAD_DIR);
    }

    public static File getDir(String str){
        StringBuilder path=new StringBuilder();
        if(isSDAvaliable()) {
            path.append(Environment.getExternalStorageDirectory().getAbsolutePath());
            path.append(File.separator);
            path.append(ROOT);
            path.append(File.separator);
            path.append(str);
        }else{
            File fileDir=UiUtils.getContext().getCacheDir();
            path.append(fileDir.getAbsolutePath());
            path.append(File.separator);
            path.append(str);
        }
        File file=new File(path.toString());
        if(!file.exists() || !file.isDirectory()){
            file.mkdirs();
        }
        return file;
    }

    private static boolean isSDAvaliable() {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            return true;
        }else {
            return false;
        }
    }
}
