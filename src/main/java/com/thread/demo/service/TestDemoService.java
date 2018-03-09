package com.thread.demo.service;

import static com.commons.utils.CheckUtil.notNull;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.pool.vendor.SybaseExceptionSorter;
import com.sun.prism.impl.BaseContext;
import com.thread.demo.dao.BaseCountDao;
import com.thread.demo.entity.BaseCount;

@Service
public class TestDemoService {

	private static final Logger logger = LoggerFactory.getLogger(TestDemoService.class);
	private Lock lock = new ReentrantLock();
	@Autowired
	private BaseCountDao baseCountDao;
	private int count = 0;
	/**
	 * 没有加锁的情况
	 * @param id
	 * @return
	 */
	public BaseCount addCount(Long id){
		logger.info("对id为{}的数量自加一");
		notNull(id, "param.is.null");
		BaseCount baseCount = baseCountDao.getById(id);
		notNull(baseCount, "param.is.null");
		int count = baseCount.getCount();
		baseCount.setCount(++count);
		//模拟延迟100ms
		delay(100);
		baseCountDao.update(baseCount);
		logger.info("id为{}自加一完毕，数字：{}",count);
		return baseCount;
	}
	
	/**
	 * 加synchronized的情况
	 * @param id
	 * @return
	 */
	public synchronized BaseCount addCountSyn(Long id){
		logger.info("对id为{}的数量自加一",id);
		notNull(id, "param.is.null");
		BaseCount baseCount = baseCountDao.getById(id);
		notNull(baseCount, "param.is.null");
		int count = baseCount.getCount();
		baseCount.setCount(++count);
		//模拟延迟100ms
		delay(100);
		baseCountDao.update(baseCount);
		logger.info("id为{}自加一完毕，数字：{}",id,count);
		return baseCount;
	}
	
	/**
	 * 加lock的情况
	 * @param id
	 * @return
	 */
	public BaseCount addCountLock(Long id){
		logger.info("对id为{}的数量自加一",id);
		notNull(id, "param.is.null");
		BaseCount baseCount;
		lock.lock();
		long time = System.currentTimeMillis();
		try {
			baseCount = baseCountDao.getById(id);
			notNull(baseCount, "param.is.null");
			count = baseCount.getCount();
			baseCount.setCount(++count);
			//模拟延迟100ms
			delay(200);
			baseCountDao.update(baseCount);
			logger.info("id为{}自加一完毕，数字：{}",id,count);
			logger.info("开始时间：{}，结束时间：{}",time,System.currentTimeMillis());
		} finally {
			lock.unlock();
		}
		
		return baseCount;
	}
	
	public void delay(long ms){
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BaseCount get(Long id){
		return baseCountDao.getById(id);
	}
}
