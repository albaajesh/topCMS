package com.infotop.core.dao;

import java.util.List;

import com.infotop.common.hibernate3.Updater;
import com.infotop.core.entity.CmsGroup;

public interface CmsGroupDao {
	public List<CmsGroup> getList();

	public CmsGroup getRegDef();

	public CmsGroup findById(Integer id);

	public CmsGroup save(CmsGroup bean);

	public CmsGroup updateByUpdater(Updater<CmsGroup> updater);

	public CmsGroup deleteById(Integer id);
}