package com.infotop.cms.manager.assist;

import java.util.Date;

import com.infotop.cms.entity.assist.CmsVoteRecord;
import com.infotop.cms.entity.assist.CmsVoteTopic;
import com.infotop.core.entity.CmsUser;

public interface CmsVoteRecordMng {
	public CmsVoteRecord save(CmsVoteTopic topic, CmsUser user, String ip,
			String cookie);

	public int deleteByTopic(Integer topicId);

	public Date lastVoteTimeByUserId(Integer userId, Integer topicId);

	public Date lastVoteTimeByIp(String ip, Integer topicId);

	public Date lastVoteTimeByCookie(String cookie, Integer topicId);
}