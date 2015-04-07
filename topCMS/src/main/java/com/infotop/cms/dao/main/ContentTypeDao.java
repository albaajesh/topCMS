package com.infotop.cms.dao.main;

import java.util.List;

import com.infotop.cms.entity.main.ContentType;
import com.infotop.common.hibernate3.Updater;

public interface ContentTypeDao {
	public List<ContentType> getList(boolean containDisabled);

	public ContentType getDef();

	public ContentType findById(Integer id);

	public ContentType save(ContentType bean);

	public ContentType updateByUpdater(Updater<ContentType> updater);

	public ContentType deleteById(Integer id);
}