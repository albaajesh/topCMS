package com.infotop.core.dao;

import com.infotop.common.hibernate3.Updater;
import com.infotop.core.entity.CmsUserExt;

public interface CmsUserExtDao {
	public CmsUserExt findById(Integer id);

	public CmsUserExt save(CmsUserExt bean);

	public CmsUserExt updateByUpdater(Updater<CmsUserExt> updater);
}