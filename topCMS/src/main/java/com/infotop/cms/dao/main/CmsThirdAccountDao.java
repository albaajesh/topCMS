package com.infotop.cms.dao.main;

import com.infotop.common.hibernate3.Updater;
import com.infotop.common.page.Pagination;
import com.infotop.cms.entity.main.CmsThirdAccount;

public interface CmsThirdAccountDao {
	public Pagination getPage(String username,String source,int pageNo, int pageSize);

	public CmsThirdAccount findById(Long id);
	
	public CmsThirdAccount findByKey(String key);

	public CmsThirdAccount save(CmsThirdAccount bean);

	public CmsThirdAccount updateByUpdater(Updater<CmsThirdAccount> updater);

	public CmsThirdAccount deleteById(Long id);
}