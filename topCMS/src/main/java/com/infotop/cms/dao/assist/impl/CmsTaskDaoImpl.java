package com.infotop.cms.dao.assist.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.infotop.common.hibernate3.Finder;
import com.infotop.common.hibernate3.HibernateBaseDao;
import com.infotop.common.page.Pagination;
import com.infotop.cms.dao.assist.CmsTaskDao;
import com.infotop.cms.entity.assist.CmsTask;

@Repository
public class CmsTaskDaoImpl extends HibernateBaseDao<CmsTask, Integer> implements CmsTaskDao {
	public Pagination getPage(Integer siteId,int pageNo, int pageSize) {
		Finder f=Finder.create("from CmsTask task where task.site.id=:siteId").setParam("siteId", siteId);
		Pagination page = find(f, pageNo, pageSize);
		return page;
	}
	
	@SuppressWarnings("unchecked")
	public List<CmsTask> getList(){
		Finder f=Finder.create("from CmsTask");
		return find(f);
	}

	public CmsTask findById(Integer id) {
		CmsTask entity = get(id);
		return entity;
	}

	public CmsTask save(CmsTask bean) {
		getSession().save(bean);
		return bean;
	}

	public CmsTask deleteById(Integer id) {
		CmsTask entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<CmsTask> getEntityClass() {
		return CmsTask.class;
	}
}