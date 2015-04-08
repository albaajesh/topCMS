package com.infotop.common.hibernate3;

import java.io.IOException;
import java.util.Properties;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.ObjectExistsException;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.ConfigurationFactory;
import net.sf.ehcache.config.DiskStoreConfiguration;




import org.hibernate.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

/**
 * Ã¤Â¸ÂºWEBÃ¥Âºâ€�Ã§â€�Â¨Ã¦ï¿½ï¿½Ã¤Â¾â€ºÃ§Â¼â€œÃ¥Â­ËœÃ£â‚¬â€š
 * 
 * Ã¨Â§Â£Ã¥â€ Â³Ã©â€¦ï¿½Ã§Â½Â®Ã¦â€“â€¡Ã¤Â»Â¶Ã¥Å“Â°Ã¥ï¿½â‚¬Ã¥â€™Å’Ã§Â¼â€œÃ¥Â­ËœÃ¦â€“â€¡Ã¤Â»Â¶Ã¥Â­ËœÃ¦â€�Â¾Ã¥Å“Â°Ã¥ï¿½â‚¬Ã§Å¡â€žÃ©â€”Â®Ã©Â¢ËœÃ£â‚¬â€šÃ¦â€�Â¯Ã¦Å’ï¿½/WEB-INFÃ§Å¡â€žÃ¥Å“Â°Ã¥ï¿½â‚¬Ã¦Â Â¼Ã¥Â¼ï¿½Ã£â‚¬â€š
 */
@SuppressWarnings("deprecation")
public final class SpringEhCacheProvider implements CacheProvider {
	private static final Logger log = LoggerFactory
			.getLogger(SpringEhCacheProvider.class);

	private Resource configLocation;
	private Resource diskStoreLocation;
	private CacheManager manager;

	public void setConfigLocation(Resource configLocation) {
		this.configLocation = configLocation;
	}

	public void setDiskStoreLocation(Resource diskStoreLocation) {
		this.diskStoreLocation = diskStoreLocation;
	}

	public final Cache buildCache(String name, Properties properties)
			throws CacheException {
		try {
			net.sf.ehcache.Ehcache cache = manager.getEhcache(name);
			if (cache == null) {
				String s = "Could not find a specific ehcache configuration for cache named [{}]; using defaults.";
				log.warn(s, name);
				manager.addCache(name);
				cache = manager.getEhcache(name);
				log.debug("started EHCache region: " + name);
			}
			return new net.sf.ehcache.hibernate.EhCache(cache);
		} catch (net.sf.ehcache.CacheException e) {
			throw new CacheException(e);
		}
	}

	/**
	 * Returns the next timestamp.
	 */
	public final long nextTimestamp() {
		return Timestamper.next();
	}

	/**
	 * Callback to perform any necessary initialization of the underlying cache
	 * implementation during SessionFactory construction.
	 * <p/>
	 * 
	 * @param properties
	 *            current configuration settings.
	 */
	public final void start(Properties properties) throws CacheException {
		if (manager != null) {
			String s = "Attempt to restart an already started EhCacheProvider. Use sessionFactory.close() "
					+ " between repeated calls to buildSessionFactory. Using previously created EhCacheProvider."
					+ " If this behaviour is required, consider using SingletonEhCacheProvider.";
			log.warn(s);
			return;
		}
		Configuration config = null;
		try {
			if (configLocation != null) {
				config = ConfigurationFactory.parseConfiguration(configLocation
						.getInputStream());
				if (this.diskStoreLocation != null) {
					DiskStoreConfiguration dc = new DiskStoreConfiguration();
					dc.setPath(this.diskStoreLocation.getFile()
							.getAbsolutePath());
					try {
						config.addDiskStore(dc);
					} catch (ObjectExistsException e) {
						String s = "if you want to config distStore in spring,"
								+ " please remove diskStore in config file!";
						log.warn(s, e);
					}
				}
			}
		} catch (IOException e) {
			log.warn("create ehcache config failed!", e);
		}
		if (config != null) {
			manager = new CacheManager(config);
		} else {
			manager = new CacheManager();
		}
	}

	/**
	 * Callback to perform any necessary cleanup of the underlying cache
	 * implementation during SessionFactory.close().
	 */
	public final void stop() {
		if (manager != null) {
			manager.shutdown();
			manager = null;
		}
	}

	/**
	 * Not sure what this is supposed to do.
	 * 
	 * @return false to be safe
	 */
	public final boolean isMinimalPutsEnabledByDefault() {
		return false;
	}
}
