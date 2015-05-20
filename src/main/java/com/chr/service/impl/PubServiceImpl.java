package com.chr.service.impl;



import javax.annotation.Resource;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.chr.service.PubService;

/**
 * @author zhenglu
 *
 */

@Service
public class PubServiceImpl implements PubService {

	@Resource(name="stringRedisTemplate")
	private  StringRedisTemplate stringRedisTemplate;
	
	private String channelTopic = "user:topic";
	
	/*发布消息到Channel*/
	public void publisher(String message) {
		stringRedisTemplate.convertAndSend(channelTopic, message);
	}
}
