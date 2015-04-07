package com.infotop.cms.dao.assist;

import java.util.List;

import com.infotop.common.hibernate3.Updater;
import com.infotop.common.page.Pagination;
import com.infotop.cms.entity.assist.CmsTask;

public interface CmsTaskDao {
	public Pagination getPage(Integer siteId,int pageNo, int pageSize);
	
	public List<CmsTask> getList();

	public CmsTask findById(Integer id);

	public CmsTask save(CmsTask bean);

	public CmsTask updateByUpdater(Updater<CmsTask> updater);

	public CmsTask deleteById(Integer id);
}