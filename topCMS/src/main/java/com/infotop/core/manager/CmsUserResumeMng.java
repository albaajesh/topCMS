package com.infotop.core.manager;

import com.infotop.core.entity.CmsUser;
import com.infotop.core.entity.CmsUserResume;

public interface CmsUserResumeMng {
	public CmsUserResume save(CmsUserResume ext, CmsUser user);

	public CmsUserResume update(CmsUserResume ext, CmsUser user);
}