package com.infotop.common.web;

import java.io.IOException;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.ObjectExistsException;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.ConfigurationFactory;
import net.sf.ehcache.config.DiskStoreConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

/**
 * ç”¨äºŽWebçš„EhCacheManagerFacotryBeanã€‚å�¯ä»¥åŸºäºŽWebæ ¹ç›®å½•æŒ‡å®šdiskStoreåœ°å�€ã€‚
 */
public class WebEhCacheManagerFacotryBean  implements FactoryBean<CacheManager>,
		InitializingBean, DisposableBean {

	private final Logger log = LoggerFactory
			.getLogger(WebEhCacheManagerFacotryBean.class);

	private Resource configLocation;
	private Resource diskStoreLocation;

	private String cacheManagerName;

	private CacheManager cacheManager;

	/**
	 * Set the location of the EHCache config file. A typical value is
	 * "/WEB-INF/ehcache.xml".
	 * <p>
	 * Default is "ehcache.xml" in the root of the class path, or if not found,
	 * "ehcache-failsafe.xml" in the EHCache jar (default EHCache
	 * initialization).
	 * 
	 * @see net.sf.ehcache.CacheManager#create(java.io.InputStream)
	 * @see net.sf.ehcache.CacheManager#CacheManager(java.io.InputStream)
	 */
	public void setConfigLocation(Resource configLocation) {
		this.configLocation = configLocation;
	}

	/**
	 * è®¾ç½®ehcahceçš„diskStore pathï¼Œä¾‹å¦‚ï¼š/WEB-INF/cache
	 * 
	 * å¦‚è®¾ç½®äº†æ­¤é¡¹ï¼Œåˆ™åœ¨ehcacheé…�ç½®æ–‡ä»¶ä¸­ä¸�è¦�é…�ç½®<diskStore path=""/>ï¼Œå�¦åˆ™æœ¬è®¾ç½®æ— æ•ˆã€‚
	 * 
	 * @param diskStoreLocation
	 */
	public void setDiskStoreLocation(Resource diskStoreLocation) {
		this.diskStoreLocation = diskStoreLocation;
	}

	/**
	 * Set the name of the EHCache CacheManager (if a specific name is desired).
	 * 
	 * @see net.sf.ehcache.CacheManager#setName(String)
	 */
	public void setCacheManagerName(String cacheManagerName) {
		this.cacheManagerName = cacheManagerName;
	}

	public void afterPropertiesSet() throws IOException, CacheException {
//		log.info("Initializing EHCache CacheManager");
//		Configuration config = null;
//		if (this.configLocation != null) {
//			config = ConfigurationFactory
//					.parseConfiguration(this.configLocation.getInputStream());
//			if (this.diskStoreLocation != null) {
//				DiskStoreConfiguration dc = new DiskStoreConfiguration();
//				dc.setPath(this.diskStoreLocation.getFile().getAbsolutePath());
//				try {
//					config.addDiskStore(dc);
//				} catch (ObjectExistsException e) {
//					log.warn("if you want to config distStore in spring,"
//							+ " please remove diskStore in config file!", e);
//				}
//			}
//		}
//		if (config != null) {
//			this.cacheManager = new CacheManager(config);
//		} else {
//			this.cacheManager = new CacheManager();
//		}
//		if (this.cacheManagerName != null) {
//			this.cacheManager.setName(this.cacheManagerName);
//		}
	}

	public CacheManager getObject() {
		return this.cacheManager;
	}

	public Class<? extends CacheManager> getObjectType() {
		return (this.cacheManager != null ? this.cacheManager.getClass()
				: CacheManager.class);
	}

	public boolean isSingleton() {
		return true;
	}

	public void destroy() {
		log.info("Shutting down EHCache CacheManager");
		this.cacheManager.shutdown();
	}
}
