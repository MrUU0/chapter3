package org.smart4j.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 编码 与 解码 操作工具类
 * @author WZ
 * @Class CodeCUtil
 * @create 2017-08-12
 **/
public final class CodeCUtil {
    /**
     * 记录日志
     */
    private static final Logger logger = LoggerFactory.getLogger(CodeCUtil.class);

    /**
     * 将 URL 编码
     * @param source
     * @return
     */
    public static String encodeURL(String source){
        try {
            return URLEncoder.encode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("encode url failure, url : " + source, e);
            return null;
        }
    }

    /**
     * 将 URL 解码
     * @param source
     * @return
     */
    public static String decodeURL(String source){
        try {
            return URLDecoder.decode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("decode url failure, url:" + source, e);
            return null;
        }
    }

}
