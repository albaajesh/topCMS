package com.infotop.cms.dao.assist;

import com.infotop.cms.entity.assist.CmsCommentExt;
import com.infotop.common.hibernate3.Updater;
import com.infotop.common.page.Pagination;

public interface CmsCommentExtDao {
	public Pagination getPage(int pageNo, int pageSize);

	public CmsCommentExt findById(Integer id);

	public CmsCommentExt save(CmsCommentExt bean);

	public CmsCommentExt updateByUpdater(Updater<CmsCommentExt> updater);

	public int deleteByContentId(Integer contentId);

	public CmsCommentExt deleteById(Integer id);
}