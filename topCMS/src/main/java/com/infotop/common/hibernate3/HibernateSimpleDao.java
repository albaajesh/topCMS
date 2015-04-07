package com.infotop.common.hibernate3;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.infotop.common.page.Pagination;
import com.infotop.common.util.MyBeanUtils;

/**
 * hibernate DAOåŸºç±»
 * 
 * æ��ä¾›hqlåˆ†é¡µæŸ¥è¯¢ï¼Œä¸�å¸¦æ³›åž‹ï¼Œä¸Žå…·ä½“å®žä½“ç±»æ— å…³ã€‚
 */
public abstract class HibernateSimpleDao {
	/**
	 * æ—¥å¿—ï¼Œå�¯ç”¨äºŽå­�ç±»
	 */
	protected Logger log = LoggerFactory.getLogger(getClass());
	/**
	 * HIBERNATE çš„ order å±žæ€§
	 */
	protected static final String ORDER_ENTRIES = "orderEntries";

	/**
	 * é€šè¿‡HQLæŸ¥è¯¢å¯¹è±¡åˆ—è¡¨
	 * 
	 * @param hql
	 *            hqlè¯­å�¥
	 * @param values
	 *            æ•°é‡�å�¯å�˜çš„å�‚æ•°
	 */
	@SuppressWarnings("unchecked")
	protected List find(String hql, Object... values) {
		return createQuery(hql, values).list();
	}

	/**
	 * é€šè¿‡HQLæŸ¥è¯¢å”¯ä¸€å¯¹è±¡
	 */
	protected Object findUnique(String hql, Object... values) {
		return createQuery(hql, values).uniqueResult();
	}

	/**
	 * é€šè¿‡FinderèŽ·å¾—åˆ†é¡µæ•°æ�®
	 * 
	 * @param finder
	 *            Finderå¯¹è±¡
	 * @param pageNo
	 *            é¡µç �
	 * @param pageSize
	 *            æ¯�é¡µæ�¡æ•°
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Pagination find(Finder finder, int pageNo, int pageSize) {
		int totalCount = countQueryResult(finder);
		Pagination p = new Pagination(pageNo, pageSize, totalCount);
		if (totalCount < 1) {
			p.setList(new ArrayList());
			return p;
		}
		Query query = getSession().createQuery(finder.getOrigHql());
		finder.setParamsToQuery(query);
		query.setFirstResult(p.getFirstResult());
		query.setMaxResults(p.getPageSize());
//		if (finder.isCacheable()) {
//			query.setCacheable(true);
//		}
		List list = query.list();
		p.setList(list);
		return p;
	}
	
	protected Pagination findByGroup(Finder finder,String selectSql, int pageNo, int pageSize) {
		return findByTotalCount(finder, pageNo, pageSize,  countQueryResultByGroup(finder,selectSql));
	}

	/**
	 * é€šè¿‡FinderèŽ·å¾—åˆ—è¡¨æ•°æ�®
	 * 
	 * @param finder
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List find(Finder finder) {
		Query query = finder.createQuery(getSession());
		List list = query.list();
		return list;
	}

	/**
	 * æ ¹æ�®æŸ¥è¯¢å‡½æ•°ä¸Žå�‚æ•°åˆ—è¡¨åˆ›å»ºQueryå¯¹è±¡,å�Žç»­å�¯è¿›è¡Œæ›´å¤šå¤„ç�†,è¾…åŠ©å‡½æ•°.
	 */
	protected Query createQuery(String queryString, Object... values) {
		Assert.hasText(queryString);
		Query queryObject = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject;
	}

	/**
	 * é€šè¿‡CriteriaèŽ·å¾—åˆ†é¡µæ•°æ�®
	 * 
	 * @param crit
	 * @param pageNo
	 * @param pageSize
	 * @param projection
	 * @param orders
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Pagination findByCriteria(Criteria crit, int pageNo, int pageSize) {
		CriteriaImpl impl = (CriteriaImpl) crit;
		// å…ˆæŠŠProjectionã€�ResultTransformerã€�OrderByå�–å‡ºæ�¥,æ¸…ç©ºä¸‰è€…å�Žå†�æ‰§è¡ŒCountæ“�ä½œ
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();
		List<CriteriaImpl.OrderEntry> orderEntries;
		try {
			orderEntries = (List) MyBeanUtils
					.getFieldValue(impl, ORDER_ENTRIES);
			MyBeanUtils.setFieldValue(impl, ORDER_ENTRIES, new ArrayList());
		} catch (Exception e) {
			throw new RuntimeException(
					"cannot read/write 'orderEntries' from CriteriaImpl", e);
		}

		int totalCount = ((Number) crit.setProjection(Projections.rowCount())
				.uniqueResult()).intValue();
		Pagination p = new Pagination(pageNo, pageSize, totalCount);
		if (totalCount < 1) {
			p.setList(new ArrayList());
			return p;
		}

		// å°†ä¹‹å‰�çš„Projection,ResultTransformerå’ŒOrderByæ�¡ä»¶é‡�æ–°è®¾å›žåŽ»
		crit.setProjection(projection);
		if (projection == null) {
			crit.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			crit.setResultTransformer(transformer);
		}
		try {
			MyBeanUtils.setFieldValue(impl, ORDER_ENTRIES, orderEntries);
		} catch (Exception e) {
			throw new RuntimeException(
					"set 'orderEntries' to CriteriaImpl faild", e);
		}
		crit.setFirstResult(p.getFirstResult());
		crit.setMaxResults(p.getPageSize());
		p.setList(crit.list());
		return p;
	}

	/**
	 * èŽ·å¾—Finderçš„è®°å½•æ€»æ•°
	 * 
	 * @param finder
	 * @return
	 */
	protected int countQueryResult(Finder finder) {
		Query query = getSession().createQuery(finder.getRowCountHql());
		finder.setParamsToQuery(query);
//		if (finder.isCacheable()) {
//			query.setCacheable(true);
//		}
		return ((Number) query.iterate().next()).intValue();
	}
	
	protected int countQueryResultByGroup(Finder finder,String selectSql) {
		Query query = getSession().createQuery(finder.getRowCountTotalHql(selectSql));
		setParamsToQuery(finder, query);
		return ((Number) query.iterate().next()).intValue();
	}
	
	@SuppressWarnings("unchecked")
	private Pagination findByTotalCount(Finder finder, int pageNo, int pageSize,int totalCount) {
		Pagination p = new Pagination(pageNo, pageSize, totalCount);
		if (totalCount < 1) {
			p.setList(new ArrayList());
			return p;
		}
		Query query = getSession().createQuery(finder.getOrigHql());
		finder.setParamsToQuery(query);
		query.setFirstResult(p.getFirstResult());
		query.setMaxResults(p.getPageSize());
//		if (finder.isCacheable()) {
//			query.setCacheable(true);
//		}
		List list = query.list();
		p.setList(list);
		return p;
	}
	
	private Query setParamsToQuery(Finder finder,Query query){
		finder.setParamsToQuery(query);
//		if (finder.isCacheable()) {
//			query.setCacheable(true);
//		}
		return query;
	}

	protected SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
