package com.infotop.cms.dao.assist;

import com.infotop.common.hibernate3.Updater;
import com.infotop.common.page.Pagination;
import com.infotop.cms.entity.assist.CmsScoreItem;

public interface CmsScoreItemDao {
	public Pagination getPage(Integer groupId,int pageNo, int pageSize);

	public CmsScoreItem findById(Integer id);

	public CmsScoreItem save(CmsScoreItem bean);

	public CmsScoreItem updateByUpdater(Updater<CmsScoreItem> updater);

	public CmsScoreItem deleteById(Integer id);
}