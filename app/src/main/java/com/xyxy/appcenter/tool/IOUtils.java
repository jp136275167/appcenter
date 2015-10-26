package com.xyxy.appcenter.tool;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by Administrator on 2015/10/9.
 */
public class IOUtils {
    /** 关闭流 */
    public static boolean close(Closeable io) {
        if (io != null) {
            try {
                io.close();
            } catch (IOException e) {
                LogUtils.e(e);
            }
        }
        return true;
    }
}
