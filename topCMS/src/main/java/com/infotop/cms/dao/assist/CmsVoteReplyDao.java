package com.infotop.cms.dao.assist;

import com.infotop.cms.entity.assist.CmsVoteReply;
import com.infotop.common.hibernate3.Updater;
import com.infotop.common.page.Pagination;

public interface CmsVoteReplyDao {

	public Pagination getPage(Integer  subTopicId, int pageNo, int pageSize);
	
	public CmsVoteReply findById(Integer id);

	public CmsVoteReply save(CmsVoteReply bean);

	public CmsVoteReply updateByUpdater(Updater<CmsVoteReply> updater);

	public CmsVoteReply deleteById(Integer id);
}