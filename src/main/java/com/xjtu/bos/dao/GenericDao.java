package com.xjtu.bos.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.xjtu.bos.domain.bc.Standard;
import com.xjtu.bos.page.PageResponseBean;


/**
 * 通用DAO
 * @author hanmeina
 *
 * @param <T>
 */
public interface GenericDao<T>{
    /**
     * 保存
     * @param obj
     */
	public void save(T obj);
	 /**
     * 删除
     * @param obj
     */
	public void delete(T obj);
	/**
     * 保存或者修改
     * @param obj
     */
	public void insertOrUpdate(T obj);
	/**
     * 修改
     * @param obj
     */
	public void update(T obj);
	 /**
     *根据id查询
     * @param obj
     * @return
     */
	public T findById (Serializable id);
	/**
	 * 查询所有数据
	 * @return
	 */
	public List<T> findAll();
	/**
	 * 条件查询
	
	 */
	public List<T> findByNamedQuery(String queryName,Object...values);//根据hql查询
	public List<T> findByCriteria(DetachedCriteria detachedCriteria);//面向对象条件查询
	/**
	 * 根据当前条件查询总记录数
	 * @param detachedCriteria
	 * @return long
	 */
	public long findTotalCount(DetachedCriteria detachedCriteria);
	
	/**
	 * 根据条件查询每页显示几条记录
	 * @param detachedCriteria
	 * @param firstResult
	 * @param maxResults
	 * @return List<Standard>
	 */
	public List<T> pageQuery(DetachedCriteria detachedCriteria,
			int firstResult, int maxResults);
	
	/**
	 * 结合lucene索引库进行分页查询
	 * @param conditionName
	 * @param conditionValue
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageResponseBean queryByLucene(String conditionName,
			String conditionValue, int page, int rows);
	
 	
}
