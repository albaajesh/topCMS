package com.infotop.cms.dao.main.impl;

import org.springframework.stereotype.Repository;

import com.infotop.cms.dao.main.ChannelTxtDao;
import com.infotop.cms.entity.main.ChannelTxt;
import com.infotop.common.hibernate3.HibernateBaseDao;

@Repository
public class ChannelTxtDaoImpl extends HibernateBaseDao<ChannelTxt, Integer>
		implements ChannelTxtDao {
	public ChannelTxt findById(Integer id) {
		ChannelTxt entity = get(id);
		return entity;
	}

	public ChannelTxt save(ChannelTxt bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	protected Class<ChannelTxt> getEntityClass() {
		return ChannelTxt.class;
	}
}