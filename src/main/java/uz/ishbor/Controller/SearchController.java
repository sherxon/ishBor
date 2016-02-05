package uz.ishbor.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ishbor.Component.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by sherxon on 1/22/16.
 */
@RestController
@RequestMapping(value = "/search")
public class SearchController {

    @Autowired
    private Util util;

    @Autowired private JdbcTemplate jdbcTemplate;



    @RequestMapping(value = "/tag/{query}", produces = "application/json; charset=utf-8")
    public String searchTag(@PathVariable String query){
        String sql="select id, name as word from tag where name ilike '%"+query+"%' limit 5 ";
        List<Map<String, Object>> data= jdbcTemplate.queryForList(sql);
        //if(data!=null && !data.isEmpty())new TagAdder(query, jdbcTemplate);
       return util.toJson(data);
    }

    private static class TagAdder extends Thread{
       private String tag;
        private JdbcTemplate jdbcTemplate;

        public TagAdder(String tag, JdbcTemplate jdbcTemplate) {
            this.tag = tag;
            this.jdbcTemplate = jdbcTemplate;
        }

        @Override
        public void run() {
            jdbcTemplate.update("INSERT INTO tag(name) VALUES ("+tag+")");
        }
    }
}
