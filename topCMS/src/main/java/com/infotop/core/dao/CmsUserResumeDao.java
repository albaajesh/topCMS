package com.infotop.core.dao;

import com.infotop.common.hibernate3.Updater;
import com.infotop.core.entity.CmsUserResume;

public interface CmsUserResumeDao {
	public CmsUserResume findById(Integer id);

	public CmsUserResume save(CmsUserResume bean);

	public CmsUserResume updateByUpdater(Updater<CmsUserResume> updater);
}