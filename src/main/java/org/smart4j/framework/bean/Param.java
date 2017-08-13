package org.smart4j.framework.bean;

import org.smart4j.framework.utils.CastUtil;

import java.util.Date;
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
     * 根据参数名 获取 String 类型数值
     */
    public String getString(String name){
        return CastUtil.castString(paramMap.get(name));
    }

    /**
     * 获取 date 类型数据
     */
    public Date getDate(String name){
        Object obj = paramMap.get(name);
        if(obj instanceof Date)
            return (Date) obj;
        return null;
    }

    /**
     * 获取 int 数值
     */
    public int getInt(String name) {
        return CastUtil.castInt(paramMap.get(name));
    }

    /**
     * 获取 double 类型参数
     */
    public double getDouble(String name){
        return CastUtil.castDouble(paramMap.get(name));
    }

    /**
     * 获取所有字段信息
     * @return
     */
    public Map<String, Object> getMap(){
        return paramMap;
    }

    /**
     * 返回参数是否为空
     * @return
     */
    public boolean isEmpty(){
        return paramMap == null || paramMap.size() == 0;
    }
}
