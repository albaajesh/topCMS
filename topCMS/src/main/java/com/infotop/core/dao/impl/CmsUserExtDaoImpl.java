package com.infotop.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.infotop.common.hibernate3.HibernateBaseDao;
import com.infotop.core.dao.CmsUserExtDao;
import com.infotop.core.entity.CmsUserExt;

@Repository
public class CmsUserExtDaoImpl extends HibernateBaseDao<CmsUserExt, Integer> implements CmsUserExtDao {
	public CmsUserExt findById(Integer id) {
		CmsUserExt entity = get(id);
		return entity;
	}

	public CmsUserExt save(CmsUserExt bean) {
		getSession().save(bean);
		return bean;
	}
	
	@Override
	protected Class<CmsUserExt> getEntityClass() {
		return CmsUserExt.class;
	}
}