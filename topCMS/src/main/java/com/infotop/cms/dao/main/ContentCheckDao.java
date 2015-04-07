package com.infotop.cms.dao.main;

import com.infotop.cms.entity.main.ContentCheck;
import com.infotop.common.hibernate3.Updater;

public interface ContentCheckDao {
	public ContentCheck findById(Long id);

	public ContentCheck save(ContentCheck bean);

	public ContentCheck updateByUpdater(Updater<ContentCheck> updater);
}