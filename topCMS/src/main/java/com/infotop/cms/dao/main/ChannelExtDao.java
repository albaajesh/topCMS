package com.infotop.cms.dao.main;

import com.infotop.cms.entity.main.ChannelExt;
import com.infotop.common.hibernate3.Updater;

public interface ChannelExtDao {
	public ChannelExt save(ChannelExt bean);

	public ChannelExt updateByUpdater(Updater<ChannelExt> updater);
}