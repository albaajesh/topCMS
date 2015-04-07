package com.infotop.cms.dao.assist;

import java.util.List;

import com.infotop.common.hibernate3.Updater;
import com.infotop.common.page.Pagination;
import com.infotop.cms.entity.assist.CmsDirectiveTpl;

public interface CmsDirectiveTplDao {
	public Pagination getPage(int pageNo, int pageSize);
	
	public List<CmsDirectiveTpl> getList(int count);

	public CmsDirectiveTpl findById(Integer id);

	public CmsDirectiveTpl save(CmsDirectiveTpl bean);

	public CmsDirectiveTpl updateByUpdater(Updater<CmsDirectiveTpl> updater);

	public CmsDirectiveTpl deleteById(Integer id);
}