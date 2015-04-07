package com.infotop.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.infotop.common.hibernate3.HibernateBaseDao;
import com.infotop.core.dao.CmsConfigDao;
import com.infotop.core.entity.CmsConfig;

@Repository
public class CmsConfigDaoImpl extends HibernateBaseDao<CmsConfig, Integer>
		implements CmsConfigDao {
	public CmsConfig findById(Integer id) {
		CmsConfig entity = get(id);
		return entity;
	}

	@Override
	protected Class<CmsConfig> getEntityClass() {
		return CmsConfig.class;
	}
}