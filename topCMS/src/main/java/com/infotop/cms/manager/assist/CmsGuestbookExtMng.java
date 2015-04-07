package com.infotop.cms.manager.assist;

import com.infotop.cms.entity.assist.CmsGuestbook;
import com.infotop.cms.entity.assist.CmsGuestbookExt;

public interface CmsGuestbookExtMng {
	public CmsGuestbookExt save(CmsGuestbookExt ext, CmsGuestbook guestbook);

	public CmsGuestbookExt update(CmsGuestbookExt ext);
}