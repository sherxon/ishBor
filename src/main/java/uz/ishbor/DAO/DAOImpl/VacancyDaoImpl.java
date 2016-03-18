package uz.ishbor.DAO.DAOImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.ishbor.DAO.VacancyDao;

import java.util.List;
import java.util.Map;

/**
 * Created by sherxon on 1/10/16.
 */
@Repository
public class VacancyDaoImpl implements VacancyDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Map<String, Object>> getList() {
        String sql="SELECT * FROM raw_vacancy ";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getList(Integer categoryId) {
        String sql="SELECT id,  st_price as price, company_name as \"companyName\", vac_position  as position, descc, st_date as \"stDate\", category_id as \"categoryId\" FROM raw_vacancy WHERE category_id="+categoryId;
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public String upsert(Map<String, Object> vacancy) {
        return null;
    }

    @Override
    public String delete(Integer id) {
        return null;
    }

    @Override
    public Map<String, Object> find(Integer vid) {
        String sql="SELECT * from raw_vacancy where id="+vid;
       return jdbcTemplate.queryForMap(sql);
    }

    @Override
    public List searchByTag(String tag) {
        String sql="SELECT id, st_price as price, company_name as \"companyName\", vac_position  as position, descc, st_date as \"stDate\" FROM raw_vacancy WHERE vac_position like '%"+tag+"%' or company_name like '%"+tag+"%' " +
                " group by company_name, vac_position, st_price, descc, st_date";
        List<Map<String, Object>> data= jdbcTemplate.queryForList(sql);
        return data;
    }

    @Override
    public List getLatest() {
        String sql="SELECT id, st_price as price, company_name as \"companyName\", vac_position  as position, descc, st_date as \"stDate\" FROM raw_vacancy order by st_date desc limit 20";
        return jdbcTemplate.queryForList(sql);
    }
}
