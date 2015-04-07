package com.infotop.cms.dao.main;

import com.infotop.cms.entity.main.ContentTxt;
import com.infotop.common.hibernate3.Updater;

public interface ContentTxtDao {
	public ContentTxt findById(Integer id);

	public ContentTxt save(ContentTxt bean);

	public ContentTxt updateByUpdater(Updater<ContentTxt> updater);
}