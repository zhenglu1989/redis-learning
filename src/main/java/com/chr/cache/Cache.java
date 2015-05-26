package com.chr.cache;

import java.io.Serializable;
import java.util.Set;

/**
 * cache 接口定义
 * @author zhenglu
 * @since 15/5/22
 */
public interface Cache {

    /**
     * get
     * @param key
     * @return
     */
    public Serializable get(String key);

    /**
     * 设置 缓存
     * @param key
     * @param value
     * @return
     */

    public boolean set(String key,Serializable value);

    /**
     * 设置cache 设置失效时间，单位为s
     * @param key
     * @param value
     * @param timeout
     */

    public boolean set(String key,Serializable value,long timeout);

    /**
     * 移除key
     * @param key
     * @return
     */

    public boolean remove(String key);

    /**
     * 是否存在key
     * @param key
     * @return
     */
    public boolean exists(String key);

    /**
     * 清楚所有匹配pattern
     * 缓存信息
     */

    public void clear(String pattern);

    /**
     * 获取所有匹配pattern的key
     * @return
     */

    public Set<String> keys(String pattern);


}
