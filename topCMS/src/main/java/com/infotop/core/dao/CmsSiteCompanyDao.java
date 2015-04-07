package com.infotop.core.dao;

import com.infotop.common.hibernate3.Updater;
import com.infotop.core.entity.CmsSiteCompany;

public interface CmsSiteCompanyDao {

	public CmsSiteCompany save(CmsSiteCompany bean);

	public CmsSiteCompany updateByUpdater(Updater<CmsSiteCompany> updater);
}