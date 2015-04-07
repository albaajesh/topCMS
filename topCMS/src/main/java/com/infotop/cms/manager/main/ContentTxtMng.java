package com.infotop.cms.manager.main;

import com.infotop.cms.entity.main.Content;
import com.infotop.cms.entity.main.ContentTxt;

public interface ContentTxtMng {
	public ContentTxt save(ContentTxt txt, Content content);

	public ContentTxt update(ContentTxt txt, Content content);
}