package com.infotop.cms.dao.main;

import java.util.List;

import com.infotop.cms.entity.main.CmsModelItem;
import com.infotop.common.hibernate3.Updater;

public interface CmsModelItemDao {
	public List<CmsModelItem> getList(Integer modelId, boolean isChannel,
			boolean hasDisabled);

	public CmsModelItem findById(Integer id);

	public CmsModelItem save(CmsModelItem bean);

	public CmsModelItem updateByUpdater(Updater<CmsModelItem> updater);

	public CmsModelItem deleteById(Integer id);
}