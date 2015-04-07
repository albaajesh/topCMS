package com.infotop.core.manager;

import com.infotop.core.entity.CmsSite;
import com.infotop.core.entity.CmsSiteCompany;

public interface CmsSiteCompanyMng {
	public CmsSiteCompany save(CmsSite site,CmsSiteCompany bean);

	public CmsSiteCompany update(CmsSiteCompany bean);
}