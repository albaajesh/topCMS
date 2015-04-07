package com.jeecms.cms.service;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jeecms.cms.manager.assist.CmsSearchWordsMng;

@Service
public class SearchWordsCacheImpl implements SearchWordsCache, DisposableBean {
	private Logger log = LoggerFactory.getLogger(SearchWordsCacheImpl.class);

	public void cacheWord(String name) {
		Element e = cache.get(name);
		//æ�œç´¢æ¬¡æ•°
		Integer hits;
		if (e != null) {
			hits = (Integer) e.getValue() + 1;
		} else {
			hits = 1;
		}
		cache.put(new Element(name, hits));
		refreshToDB();
	}
	
	private void refreshToDB() {
		long time = System.currentTimeMillis();
		if (time > refreshTime + interval) {
			refreshTime = time;
			int count = manager.freshCacheToDB(cache);
			// æ¸…é™¤ç¼“å­˜
			cache.removeAll();
			log.info("refresh cache hits to DB: {}", count);
		}
	}

	/**
	 * é”€æ¯�BEANæ—¶ï¼Œç¼“å­˜å…¥åº“ã€‚
	 */
	public void destroy() throws Exception {
		int count = manager.freshCacheToDB(cache);
		log.info("Bean destroy.refresh cache flows to DB: {}", count);
	}
	
	// é—´éš”æ—¶é—´
	private int interval = 1 * 30 * 1000; // 30ç§’
	// æœ€å�Žåˆ·æ–°æ—¶é—´
	private long refreshTime = System.currentTimeMillis();
	
	/**
	 * åˆ·æ–°é—´éš”æ—¶é—´
	 * 
	 * @param interval
	 *            å�•ä½�ç§’
	 */
	public void setInterval(int interval) {
		this.interval = interval * 1000;
	}

	@Autowired
	private CmsSearchWordsMng manager;

	@Autowired 
	@Qualifier("com.jeecms.cms.front.CmsSearchWords")
	private Ehcache cache;

}
