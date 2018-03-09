package com.thread.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commons.beans.ResultBean;
import com.thread.demo.entity.BaseCount;
import com.thread.demo.service.TestDemoService;

@Controller
@RequestMapping(value = "/test")
public class TestController {

	@Autowired
	private TestDemoService demoService;
	
	@RequestMapping(value = "/test1")
	@ResponseBody
	public ResultBean<BaseCount> addCount(Long id){
		return new ResultBean<>(demoService.addCount(id));
	}
	
	@RequestMapping(value = "/test2")
	@ResponseBody
	public ResultBean<BaseCount> addCountLock(Long id){
		return new ResultBean<>(demoService.addCountLock(id));
	}
	
	@RequestMapping(value = "/test3")
	@ResponseBody
	public ResultBean<BaseCount> addCountSyn(Long id){
		return new ResultBean<>(demoService.addCountSyn(id));
	}
}
