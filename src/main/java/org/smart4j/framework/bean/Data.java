package org.smart4j.framework.bean;

/**
 * 返回数据对象
 * @author WZ
 * @Class Data
 * @create 2017-08-12
 **/
public class Data {
    /**
     * 模型数据
     */
    private Object model;

    public Data(Object model){
        this.model = model;
    }
    public Object getModel(){
        return this.model;
    }
}