package com.infotop.core.manager;

import java.util.Date;

import com.infotop.core.entity.CmsConfig;
import com.infotop.core.entity.CmsConfigAttr;
import com.infotop.core.entity.MarkConfig;
import com.infotop.core.entity.MemberConfig;

public interface CmsConfigMng {
	public CmsConfig get();

	public void updateCountCopyTime(Date d);

	public void updateCountClearTime(Date d);

	public CmsConfig update(CmsConfig bean);

	public MarkConfig updateMarkConfig(MarkConfig mark);

	public void updateMemberConfig(MemberConfig memberConfig);
	
	public void updateConfigAttr(CmsConfigAttr configAttr);
}