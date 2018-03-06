package com.commons.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.jhlabs.composite.SaturationComposite;

/**
 * HibernateDao基类，所有的dao都要继承该类
 * @author csxx_wmw
 *
 * @param <T>
 * @param <ID>
 */
public abstract class HibernateBaseDao<T,ID extends Serializable>{

	public static final String ROW_COUNT = "select count(*) ";
	public static final String FROM = "from";
	public static final String DISTINCT = "distinct";
	public static final String HQL_FETCH = "fetch";
	public static final String ORDER_BY = "order ";
	public static final String GROUP_BY = "group";
	
	protected HibernateTemplate hibernateTemplate;  
    
    @Resource(name="hibernateTemplate")  
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {  
        this.hibernateTemplate = hibernateTemplate;  
    }  
  
    /**
     * 保存一个新的实例
     * @param u
     */
    public void save(T u) {  
        this.hibernateTemplate.save(u);  
    }  
    
    public void saveAll(Collection<T> list){
    	for (T t : list) {
			save(t);
		}
    }
  
    /**
     * 删除指定的持久化实例  
     * @param u
     */
    public void delete(T u) {  
        this.hibernateTemplate.delete(u);  
    }  
  
    /**
     * deleteAll(Collection entities)：删除集合内全部持久化类实例  
     * @param list
     */
    public void deleteAll(List<T> list) {
        this.hibernateTemplate.deleteAll(list);  
    }  
  
    /**
     * 查询回实例集合  
     * @return
     */
    @SuppressWarnings("unchecked")  
    public List<T> findAll() {  
        String hql = "from "+getEntityClassName();  
        return (List<T>) this.hibernateTemplate.find(hql);  
    }
    
    /**
     * 
     * @param hqlString
     * @param values
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<T> find(String hqlString, Object...values){
    	return (List<T>) this.hibernateTemplate.find(hqlString, values);
    }
  
    /**
     * get(Serializable id)：根据主键加载特定持久化类的实例  
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
	public T getById(Serializable id) {  
        return  (T) this.hibernateTemplate.get(getEntityClass(), id);  
    }  
  
    /**
     * update(T entity)：更新实例的状态，要求entity是持久状态  
     * @param u
     */
    public void update(T u) {
        this.hibernateTemplate.update(u);  
    }  
  
    /**
     * saveOrUpdate(T entity)：根据实例状态，选择保存或者更新  
     * @param u
     */
    public void saveOrUpdate(T u) {
        this.hibernateTemplate.saveOrUpdate(u);  
    }
    
    @SuppressWarnings("unchecked")
	public T findOne(String hqlString,Object...values){
    	List list = find(hqlString, values);
    	return list.isEmpty() ? null : (T)list.get(0);
    }
    /**
     * 分页查询
     * @param hql
     * @param page
     * @param values
     * @return
     */
    @Transactional
    public Page findByPage(String hql,Page page, Object...values){
    	//查询总数
    	int totalCount = countQueryResult(getRowCountBaseHql(hql, ORDER_BY),values);
    	
    	page.setTotalCount(totalCount);
    	Query query = getQuery(hql, values);
    	query.setFirstResult(page.firstResult());
    	query.setMaxResults(page.getPageSize());
    	page.setList(query.list());
		return page;
    	
    }
    
    /**
     * 获取Query
     * @param hql
     * @param values
     * @return
     */
    protected Query getQuery(String hql, Object...values){
    	Query query = hibernateTemplate.getSessionFactory()
				.getCurrentSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
    }
    
    protected int countQueryResult(String hql, Object...values) {
		Query query = getQuery(hql, values);
		return ((Number) query.iterate().next()).intValue();
	}
    
    /**
     * 根据hql获取查询数量的hql
     * @param hql
     * @param indexKey
     * @return
     */
    private String getRowCountBaseHql(String hql,String indexKey) {

		int fromIndex = hql.toLowerCase().indexOf(FROM);
		String projectionHql = hql.substring(0, fromIndex);

		hql = hql.substring(fromIndex);
		String rowCountHql = hql.replace(HQL_FETCH, "");

		int index = rowCountHql.indexOf(indexKey);
		if (index > 0) {
			rowCountHql = rowCountHql.substring(0, index);
		}
		return wrapProjection(hql,projectionHql) + rowCountHql;
	}
    
    private String wrapProjection(String hql,String projection) {
		if (projection.indexOf("select") == -1) {
			return ROW_COUNT;
		} else {
			return projection.replace("select", "select count(") + ") ";
		}
	}
    
    @SuppressWarnings("unchecked")
	public List<T> findLimit(Integer startIndex, int count, String hql, Object...values){
    	if(startIndex == null){
    		startIndex = 0;
    	}
    	Assert.isTrue(startIndex >= 0, "startIndex must than 0");
    	Assert.isTrue(count > 0, "count must than 0");
    	Query query = getQuery(hql, values);
    	query.setMaxResults(count);
    	query.setFirstResult(startIndex);
    	return query.list();
    }
  
    
    private String getEntityClassName(){
    	return getEntityClass().getName();
    }

    /**
	 * 获得Dao对于的实体类
	 * 
	 * @return
	 */
	protected abstract Class<T> getEntityClass();
}
