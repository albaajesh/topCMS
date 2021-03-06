package com.infotop.cms.dao.assist;

import java.util.List;

import com.infotop.common.hibernate3.Updater;
import com.infotop.common.page.Pagination;
import com.infotop.cms.entity.assist.CmsPlug;

public interface CmsPlugDao {
	public Pagination getPage(int pageNo, int pageSize);
	
	public List<CmsPlug> getList(String author,Boolean used);

	public CmsPlug findById(Integer id);
	
	public CmsPlug findByPath(String plugPath);

	public CmsPlug save(CmsPlug bean);

	public CmsPlug updateByUpdater(Updater<CmsPlug> updater);

	public CmsPlug deleteById(Integer id);
}