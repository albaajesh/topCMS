package com.infotop.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.infotop.common.hibernate3.HibernateBaseDao;
import com.infotop.core.dao.CmsUserResumeDao;
import com.infotop.core.entity.CmsUserResume;

@Repository
public class CmsUserResumeDaoImpl extends HibernateBaseDao<CmsUserResume, Integer> implements CmsUserResumeDao {
	public CmsUserResume findById(Integer id) {
		CmsUserResume entity = get(id);
		return entity;
	}

	public CmsUserResume save(CmsUserResume bean) {
		getSession().save(bean);
		return bean;
	}
	
	@Override
	protected Class<CmsUserResume> getEntityClass() {
		return CmsUserResume.class;
	}
}