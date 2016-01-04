package uz.ishbor.DAO;

import java.util.List;

/**
 * Created by sherxon on 1/3/16.
 */
public interface BasicDao<T> {
    void create(T entity);
    long save(T entity);
    void delete(T entity);
    void delete(Long id);
    T find(Long id);
    List<T> list();
}
