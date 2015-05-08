/**
 * 2014-7-2 
 * PubSubController.java 
 * author:Edwin Chen
 */
package com.chr.controller;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.chr.domain.MessageList;
import com.chr.service.impl.PubServiceImpl;
import com.chr.service.impl.SubServiceImpl;

/**
 * @author zhenglu
 * 
 */

@Controller
@RequestMapping(value = "/pubsub")
public class PubSubController {
	@Resource(name = "pubServiceImpl")
	private PubServiceImpl pubService;

	@Autowired
	private SubServiceImpl subService;

	@RequestMapping(value = "/sub")
	public String Subscriber(Model model) {
		MessageList messageList = subService.getMessageList();
		ArrayList<String> arrayList =  (ArrayList<String>) messageList.output();
		model.addAttribute("arrayList", arrayList);
		return "/WEB-INF/jsp/subResult.jsp";
	}

	@RequestMapping(value = "/pub", method = RequestMethod.GET)
	public String Subscriber() {
		return "/WEB-INF/jsp/pub.jsp";
	}

	@RequestMapping(value = "/pub", method = RequestMethod.POST)
	public String Publisher(
			@RequestParam(value = "message", required = true) String message) {
		pubService.publisher(message);
		return "/WEB-INF/jsp/pubResult.jsp";
	}
}
