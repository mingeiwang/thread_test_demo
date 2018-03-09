package com.thread.demo.dao;

import org.springframework.stereotype.Repository;

import com.commons.dao.HibernateBaseDao;
import com.thread.demo.entity.BaseCount;

@Repository
public class BaseCountDao extends HibernateBaseDao<BaseCount, Long> {

	@Override
	protected Class<BaseCount> getEntityClass() {
		// TODO Auto-generated method stub
		return BaseCount.class;
	}

}
