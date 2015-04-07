package com.infotop.common.hibernate3;

import static org.hibernate.EntityMode.POJO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.util.Assert;

import com.infotop.common.util.MyBeanUtils;

/**
 * hibernate DAOåŸºç±»
 * 
 * æ��ä¾›hqlåˆ†é¡µæŸ¥è¯¢ï¼Œæ‹·è´�æ›´æ–°ç­‰ä¸€äº›å¸¸ç”¨åŠŸèƒ½ã€‚
 * @param <T>
 *            entity class
 * @param <ID>
 *            entity id
 */
public abstract class HibernateBaseDao<T, ID extends Serializable> extends
		HibernateSimpleDao {
	/**
	 * @see Session.get(Class,Serializable)
	 * @param id
	 * @return æŒ�ä¹…åŒ–å¯¹è±¡
	 */
	protected T get(ID id) {
		return get(id, false);
	}

	/**
	 * @see Session.get(Class,Serializable,LockMode)
	 * @param id
	 *            å¯¹è±¡ID
	 * @param lock
	 *            æ˜¯å�¦é”�å®šï¼Œä½¿ç”¨LockMode.UPGRADE
	 * @return æŒ�ä¹…åŒ–å¯¹è±¡
	 */
	@SuppressWarnings("unchecked")
	protected T get(ID id, boolean lock) {
		T entity;
		if (lock) {
			entity = (T) getSession().get(getEntityClass(), id,
					LockMode.UPGRADE);
		} else {
			entity = (T) getSession().get(getEntityClass(), id);
		}
		return entity;
	}

	/**
	 * æŒ‰å±žæ€§æŸ¥æ‰¾å¯¹è±¡åˆ—è¡¨
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByProperty(String property, Object value) {
		Assert.hasText(property);
		return createCriteria(Restrictions.eq(property, value)).list();
	}

	/**
	 * æŒ‰å±žæ€§æŸ¥æ‰¾å”¯ä¸€å¯¹è±¡
	 */
	@SuppressWarnings("unchecked")
	protected T findUniqueByProperty(String property, Object value) {
		Assert.hasText(property);
		Assert.notNull(value);
		return (T) createCriteria(Restrictions.eq(property, value))
				.uniqueResult();
	}

	/**
	 * æŒ‰å±žæ€§ç»Ÿè®¡è®°å½•æ•°
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	protected int countByProperty(String property, Object value) {
		Assert.hasText(property);
		Assert.notNull(value);
		return ((Number) (createCriteria(Restrictions.eq(property, value))
				.setProjection(Projections.rowCount()).uniqueResult()))
				.intValue();
	}

	/**
	 * æŒ‰CriterionæŸ¥è¯¢åˆ—è¡¨æ•°æ�®.
	 * 
	 * @param criterion
	 *            æ•°é‡�å�¯å�˜çš„Criterion.
	 */
	@SuppressWarnings("unchecked")
	protected List findByCriteria(Criterion... criterion) {
		return createCriteria(criterion).list();
	}

	/**
	 * é€šè¿‡Updateræ›´æ–°å¯¹è±¡
	 * 
	 * @param updater
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T updateByUpdater(Updater<T> updater) {
		ClassMetadata cm = sessionFactory.getClassMetadata(getEntityClass());
		T bean = updater.getBean();
		T po = (T) getSession().get(getEntityClass(),
				cm.getIdentifier(bean));
		updaterCopyToPersistentObject(updater, po, cm);
		return po;
	}

	/**
	 * å°†æ›´æ–°å¯¹è±¡æ‹·è´�è‡³å®žä½“å¯¹è±¡ï¼Œå¹¶å¤„ç�†many-to-oneçš„æ›´æ–°ã€‚
	 * 
	 * @param updater
	 * @param po
	 */
	private void updaterCopyToPersistentObject(Updater<T> updater, T po,
			ClassMetadata cm) {
		String[] propNames = cm.getPropertyNames();
		String identifierName = cm.getIdentifierPropertyName();
		T bean = updater.getBean();
		Object value;
		for (String propName : propNames) {
			if (propName.equals(identifierName)) {
				continue;
			}
			try {
				value = MyBeanUtils.getSimpleProperty(bean, propName);
				if (!updater.isUpdate(propName, value)) {
					continue;
				}
				cm.setPropertyValue(po, propName, value);
			} catch (Exception e) {
				throw new RuntimeException(
						"copy property to persistent object failed: '"
								+ propName + "'", e);
			}
		}
	}

	/**
	 * æ ¹æ�®Criterionæ�¡ä»¶åˆ›å»ºCriteria,å�Žç»­å�¯è¿›è¡Œæ›´å¤šå¤„ç�†,è¾…åŠ©å‡½æ•°.
	 */
	protected Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(getEntityClass());
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/**
	 * èŽ·å¾—Daoå¯¹äºŽçš„å®žä½“ç±»
	 * 
	 * @return
	 */
	abstract protected Class<T> getEntityClass();
}
