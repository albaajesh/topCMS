package com.infotop.cms.dao.assist;

import com.infotop.cms.entity.assist.CmsVoteItem;
import com.infotop.common.hibernate3.Updater;
import com.infotop.common.page.Pagination;

public interface CmsVoteItemDao {
	public Pagination getPage(int pageNo, int pageSize);

	public CmsVoteItem findById(Integer id);

	public CmsVoteItem save(CmsVoteItem bean);

	public CmsVoteItem updateByUpdater(Updater<CmsVoteItem> updater);

	public CmsVoteItem deleteById(Integer id);
}