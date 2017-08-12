package org.smart4j.framework.bean;

import org.smart4j.framework.utils.CastUtil;

import java.util.Map;

/**
 * 请求参数对象
 * @author WZ
 * @Class Param
 * @create 2017-08-12
 **/
public class Param {

    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap){
        this.paramMap = paramMap;
    }

    /**
     * 根据参数名 获取 long 类型参数值
     */
    public long getLong(String name){
        return CastUtil.castLong(paramMap.get(name));
    }

    /**
     * 获取所有字段信息
     * @return
     */
    public Map<String, Object> getMap(){
        return paramMap;
    }
}
