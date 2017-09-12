package com.xjtu.bos.dao.impl;

import java.io.Serializable;
import java.util.List;










import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.WildcardQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.impl.FullTextSessionImpl;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.xjtu.bos.dao.GenericDao;
import com.xjtu.bos.domain.bc.Standard;
import com.xjtu.bos.page.PageResponseBean;

/**
 * 通用DAO实现
 * 
 * @author hanmeina
 * 
 * @param <T>
 */
public class GenericDaoImpl<T> extends HibernateDaoSupport implements
		GenericDao<T> {

	private String className;

 	public GenericDaoImpl(String className) {
		this.className = className;
	}

	public void save(T obj) {
		// this.getSession().save(obj);//使用原始Hibernate编程方式
		// this.getHibernateTemplate().save(obj);//使用Spring提供的模板工具类
		this.getHibernateTemplate().save(obj);

	}

	public void delete(T obj) {
		this.getHibernateTemplate().delete(obj);
	}

	public void update(T obj) {
		this.getHibernateTemplate().update(obj);

	}

	public T findById(Serializable id) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	public List<T> findAll() {
		
		return this.getHibernateTemplate().find("from "+className);
	}

	public List<T> findByNamedQuery(String queryName, Object... values) {
		
		return this.getHibernateTemplate().findByNamedQuery(queryName, values);
		
	}

	public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
	
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}
     
	/**
	 * 根据当前条件查询总记录数
	 */
	public long findTotalCount(DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		//select count(*) from standard
		//采用投影
		detachedCriteria.setProjection(Projections.rowCount());
         List<Long> list = this.getHibernateTemplate().findByCriteria(detachedCriteria,0,1); //第一条开始，查一条       
		 return list.get(0);
	}
    /**
     * 根据条件查询每页显示几条记录
     */
	public List<T> pageQuery(DetachedCriteria detachedCriteria,
			int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);
	}
    /**
     * 保存或者修改
     */
	public void insertOrUpdate(T obj) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().saveOrUpdate(obj);
	}
   /**
    * 结合索引库查询
    */
	@Override
	@SuppressWarnings("all")
	public PageResponseBean queryByLucene(String conditionName,
			String conditionValue, int page, int rows) {
		//System.out.println("结合索引库查询!!!");
		// TODO Auto-generated method stub
	  //HibernateSearch的编程步骤
		//1.获得Session
		Session session = this.getSession();
		//2.获得全文检索实现
		FullTextSession fullTextSession = new FullTextSessionImpl(session);
		//3.编写Lucene的Query对象（词条模糊搜索）
          Query query  = new WildcardQuery(new Term(conditionName,"*"+conditionValue+"*"));	
		//4.获得全文检索的Query
        FullTextQuery fullTextQuery =  fullTextSession.createFullTextQuery(query);
         PageResponseBean pageResponseBean =  new  PageResponseBean();
         //查询总记录数
         pageResponseBean.setTotal(fullTextQuery.getResultSize());
         
         //当前页的数据
         int firstResult = (page-1)*rows;
         int maxResults = rows;
         List list =   fullTextQuery.setFirstResult(firstResult).setMaxResults(maxResults).list();
         pageResponseBean.setRows(list);
        return pageResponseBean;
	}

}
