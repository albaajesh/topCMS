package com.infotop.core.manager;

import com.infotop.core.entity.CmsUser;
import com.infotop.core.entity.CmsUserExt;

public interface CmsUserExtMng {
	public CmsUserExt save(CmsUserExt ext, CmsUser user);

	public CmsUserExt update(CmsUserExt ext, CmsUser user);
}