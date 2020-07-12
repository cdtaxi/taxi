/**
 * 
 */
package cn.slkj.taxi.mapper;

import java.util.List;

/**
 * @author maxuh
 *
 */
public interface BaseDao<T> {
	Integer insert(T entity);

	Integer delete(T entity);

	Integer update(T entity);

	List<T> select(T entity);

	Integer count(T entity);
}
