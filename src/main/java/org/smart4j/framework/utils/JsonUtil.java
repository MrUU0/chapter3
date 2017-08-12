package org.smart4j.framework.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * JSon 工具类
 * @author WZ
 * @Class JsonUtil
 * @create 2017-08-13
 **/
public final class JsonUtil {
    /**
     * 记录日志
     */
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * object Mapper
     */
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 将 POJO 转为 JSON
     */
    public static <T> String toJson(T obj){
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("convert POJO to JSON failure", e);
            return null;
        }
    }

    /**
     * 将 JSON 转为 POJO
     */
    public static <T> T fromJson(String json, Class<T> type){
        try {
            return OBJECT_MAPPER.readValue(json, type);
        } catch (IOException e) {
            logger.error("convert JSON to POJO failure",e);
            return null;
        }
    }


}
