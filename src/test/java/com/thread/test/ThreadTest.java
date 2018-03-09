package com.thread.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thread.demo.entity.BaseCount;
import com.thread.demo.service.TestDemoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml","classpath*:spring-mvc.xml"})
public class ThreadTest {

	@Autowired
	private TestDemoService demoService;
	
	@Test
	public void test(){
		BaseCount baseCount = demoService.addCountLock(1L);
		System.out.println("更新后查询前："+baseCount);
		BaseCount baseCount2 = demoService.addCountLock(1L);
		System.out.println("更新后查询后:"+baseCount2);
	}
}
