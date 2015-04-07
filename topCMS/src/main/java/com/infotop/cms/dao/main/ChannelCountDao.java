package com.infotop.cms.dao.main;

import net.sf.ehcache.Ehcache;

import com.infotop.cms.entity.main.ChannelCount;
import com.infotop.common.hibernate3.Updater;

public interface ChannelCountDao {
	public int clearCount(boolean week, boolean month);

	public int freshCacheToDB(Ehcache cache);

	public ChannelCount findById(Integer id);

	public ChannelCount save(ChannelCount bean);

	public ChannelCount updateByUpdater(Updater<ChannelCount> updater);
}