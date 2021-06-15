package com.behere.dynamic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.behere.dynamic.service.UserService1;

@Controller
@RequestMapping("/answer1")
public class UserController1 {

	@Autowired
	private UserService1 userService;

	@ResponseBody
	@RequestMapping("/qryAnswerAskPages")
	public List qryAnswerAskPages() {
		List selectAll = userService.selectAll();
		return selectAll;
	}

	@ResponseBody
	@RequestMapping("/selectByCondition")
	public List selectByCondition() {
		List selectAll = userService.selectByCondition();
		return selectAll;
	}

}
