package org.smart4j.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 流操作工具类
 * @author WZ
 * @Class StreamUtil
 * @create 2017-08-12
 **/
public final class StreamUtil {

    /**
     * 记录日志
     */
    private static final Logger logger = LoggerFactory.getLogger(StreamUtil.class);

    /**
     * 从输入六中获取字符串
     */
    public static String getString(InputStream is){
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = reader.readLine()) != null){
                sb.append(line);
            }
        } catch (Exception e) {
            logger.error("[InputStream] get string failure",e);
        }
        return sb.toString();
    }

}
