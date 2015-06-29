package com.chr.cache;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis 实现cache的具体实现类
 * @author zhenglu
 * @since 15/5/22
 */
public class RedisCacheServiceImpl implements Cache,InitializingBean{

    private static final Logger LOGGER = Logger.getLogger(RedisCacheServiceImpl.class);

    @Autowired
    private RedisTemplate redisTemplate;

    private  ValueOperations<String,Serializable> voPer;


    @Override
    public Serializable get(String key) {
       return voPer.get(key);
    }

    @Override
    public boolean set(String key, Serializable value) {
        try{

            ValueOperations<String,Serializable>  voPer =   redisTemplate.opsForValue();
            voPer.set(key,value);

        }catch (Exception e){
            LOGGER.error("when set key ::" + key + " has error ::" + e.getMessage());
            return false;
        }

      return true;
    }

    public boolean set(String key,Serializable value,long timeout){
        try{
            voPer.set(key,value,timeout, TimeUnit.SECONDS);

        }catch (Exception e){
            LOGGER.error("when set key ::" + key + " has error ::" + e.getMessage());
            return false;
        }

        return true;

    }

    @Override
    public boolean remove(String key) {
        try{
            if(exists(key)){
                redisTemplate.delete(key);
            }
        }catch (Exception e){
           LOGGER.error("remove key :: " +key +" has error :: "+ e.getMessage()) ;
            return false;
        }

        return true;
    }

    public boolean exists(String key){
       return redisTemplate.hasKey(key);
    }

    @Override
    public void clear(String pattern) {
        Set<String> sets =   keys(pattern);
        for(String key:sets){
            remove(key);
        }
    }

    @Override
    public Set keys(String pattern) {
        return redisTemplate.keys(pattern);

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        voPer = redisTemplate.opsForValue();
    }
}
