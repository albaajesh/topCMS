package com.infotop.cms.manager.main;

import com.infotop.cms.entity.main.Channel;
import com.infotop.cms.entity.main.ChannelExt;

public interface ChannelExtMng {
	public ChannelExt save(ChannelExt ext, Channel channel);

	public ChannelExt update(ChannelExt ext);
}