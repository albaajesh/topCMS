package com.infotop.cms.manager.assist;

import com.infotop.cms.entity.assist.CmsComment;
import com.infotop.cms.entity.assist.CmsCommentExt;

public interface CmsCommentExtMng {
	public CmsCommentExt save(String ip, String text, CmsComment comment);

	public CmsCommentExt update(CmsCommentExt bean);

	public int deleteByContentId(Integer contentId);
}