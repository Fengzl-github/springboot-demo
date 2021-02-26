package com.cn.demo.config.resolver;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *@Author fengzhilong
 *@Date 2021/1/7 17:06
 *@Desc
 **/
public class MapBody {
    private Map data = new HashMap();

    public MapBody(){}
    public MapBody(Map data){
        this.data.putAll(data);
    }


    public BigDecimal getBigDecimal(Object key) {
        if(!containsKey(key)) return null;
        return new BigDecimal(getString(key));
    }
    public String getString(Object key) {
        if(!containsKey(key)||get(key)==null) return null;
        return get(key).toString();
    }

    public Long getLong(Object key) {
        return Long.valueOf(getString(key));
    }

    public Integer getInt(Object key) {
        return Integer.valueOf(getString(key));
    }

    public Double getDouble(Object key) {
        return Double.valueOf(getString(key));
    }

    public Float getFloat(Object key) {
        return Float.valueOf(getString(key));
    }

    public Object getObject(Object key) {
        return  get(key);
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public boolean containsKey(Object key) {
        return data.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return data.containsValue(value);
    }

    public Object get(Object key) {
        return data.get(key);
    }

    public Object put(Object key, Object value) {
        return data.put(key,value);
    }

    public Object remove(Object key) {
        return data.remove(key);
    }

    public void putAll(Map m) {
        data.putAll(m);
    }

    public void clear() {
        data.clear();
    }

    public Set keySet() {
        return data.keySet();
    }

    public Collection values() {
        return  data.values();
    }
}
