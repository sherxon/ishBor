package uz.ishbor.DAO;

import java.util.List;
import java.util.Map;
/**
 * Created by sherxon on 1/4/16.
 */
public interface CategoryDao{

    List<Map<String, Object>> list();
    Map<String, Object> find(Long id);
    void delete(Long id);
    Map<String, Object> create(Map<String, Object> data);

    Map<String, Object> list(Integer id);
}
