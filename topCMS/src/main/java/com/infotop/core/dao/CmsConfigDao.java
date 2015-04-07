package com.infotop.core.dao;

import com.infotop.common.hibernate3.Updater;
import com.infotop.core.entity.CmsConfig;

public interface CmsConfigDao {
	public CmsConfig findById(Integer id);

	public CmsConfig updateByUpdater(Updater<CmsConfig> updater);
}