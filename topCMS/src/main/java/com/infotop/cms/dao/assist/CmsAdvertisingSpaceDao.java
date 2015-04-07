package com.infotop.cms.dao.assist;

import java.util.List;

import com.infotop.cms.entity.assist.CmsAdvertisingSpace;
import com.infotop.common.hibernate3.Updater;

public interface CmsAdvertisingSpaceDao {
	public List<CmsAdvertisingSpace> getList(Integer siteId);

	public CmsAdvertisingSpace findById(Integer id);

	public CmsAdvertisingSpace save(CmsAdvertisingSpace bean);

	public CmsAdvertisingSpace updateByUpdater(
			Updater<CmsAdvertisingSpace> updater);

	public CmsAdvertisingSpace deleteById(Integer id);
}