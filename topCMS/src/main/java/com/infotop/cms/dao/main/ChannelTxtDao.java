package com.infotop.cms.dao.main;

import com.infotop.cms.entity.main.ChannelTxt;
import com.infotop.common.hibernate3.Updater;

public interface ChannelTxtDao {
	public ChannelTxt findById(Integer id);

	public ChannelTxt save(ChannelTxt bean);

	public ChannelTxt updateByUpdater(Updater<ChannelTxt> updater);
}