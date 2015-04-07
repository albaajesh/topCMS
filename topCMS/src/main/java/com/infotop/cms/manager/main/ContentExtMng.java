package com.infotop.cms.manager.main;

import com.infotop.cms.entity.main.Content;
import com.infotop.cms.entity.main.ContentExt;

public interface ContentExtMng {
	public ContentExt save(ContentExt ext, Content content);

	public ContentExt update(ContentExt ext);
}