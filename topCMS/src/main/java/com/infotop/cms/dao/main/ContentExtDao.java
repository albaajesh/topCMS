package com.infotop.cms.dao.main;

import com.infotop.cms.entity.main.ContentExt;
import com.infotop.common.hibernate3.Updater;

public interface ContentExtDao {
	public ContentExt findById(Integer id);

	public ContentExt save(ContentExt bean);

	public ContentExt updateByUpdater(Updater<ContentExt> updater);
}