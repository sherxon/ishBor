package uz.ishbor.DAO;

import java.util.Map;
import java.util.List;
/**
 * Created by sherxon on 1/10/16.
 */
public interface VacancyDao {
    List<Map<String, Object>> getList();
    List<Map<String, Object>> getList(Integer categoryId);
    String upsert(Map<String, Object> vacancy);
    String delete(Integer id);

    List<Map<String, Object>> find(Integer vid);
}
