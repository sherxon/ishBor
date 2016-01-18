package uz.ishbor.DAO.DAOImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.ishbor.DAO.CategoryDao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by sherxon on 1/4/16.
 */
@Repository
public class CategoryDaoImpl implements CategoryDao{
    @Autowired private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> list() {
        List<Map<String, Object>> list=jdbcTemplate.queryForList("SELECT * FROM raw_category");
        if(list.isEmpty())list= Collections.emptyList();
        return list;
    }

    @Override
    public Map<String, Object> find(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Map<String, Object> create(Map<String, Object> data) {
        return null;
    }

    @Override
    public Map<String, Object> list(Integer id) {
        return null;
    }
}
