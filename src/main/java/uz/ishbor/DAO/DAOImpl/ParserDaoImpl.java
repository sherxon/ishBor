package uz.ishbor.DAO.DAOImpl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.ishbor.DAO.ParserDao;

import java.io.IOException;
import java.util.*;

/**
 * Created by sherxon on 1/3/16.
 */
@Repository
class ParserDaoImpl implements ParserDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> parseFromSuperJob() {
        List<Map<String, Object>> mapList= new ArrayList<>();
        try {
            Document doc= Jsoup.connect("http://www.superjob.uz/vakansii/dizajn/").
                    timeout(20000).userAgent("Mozilla").
                    get();

            Elements jobDivs=doc.select(".LandingPageListElement");
            for (Element jobDiv : jobDivs) {
                Map<String, Object> map= new HashMap<>();
                map.put("vacancy_id", jobDiv.attributes().get("vac-id"));
                map.put("st_date", jobDiv.select(".LandingPageListElement_date").text());
                map.put("vac_position", jobDiv.select(".LandingPageListElement_position").text());
                map.put("st_price", jobDiv.select(".LandingPageListElement_price").text());
                map.put("company_name", jobDiv.select(".LandingPageListElement_company").text().replaceAll("\'", "\\'\\'"));
                map.put("company_url", jobDiv.select(".LandingPageListElement_company").select("a").attr("href"));
                map.put("descc" , jobDiv.select(".LandingPageListElement_desc").text());
                map.put("category_name" , "Дизайн");
                map.put("category_id" , 4);
                mapList.add(map);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapList;
    }

    @Override
    public void writeDataToDb() {
        List<Map<String, Object>> data= parseFromSuperJob();
        StringBuilder builder=new StringBuilder();
        builder.append("insert into raw_vacancy(vacancy_id, st_date,vac_position, st_price, company_name, company_url,descc,category_name,category_id ) values ");
        for (Map<String, Object> map : data) {
            builder.append("('"+map.get("vacancy_id")+"','");
            builder.append(map.get("st_date")+"','");
            builder.append(map.get("vac_position")+"','");
            builder.append(map.get("st_price")+"','");
            builder.append(map.get("company_name")+"','");
            builder.append(map.get("company_url")+"','");
            builder.append(map.get("descc")+"','");
            builder.append(map.get("category_name")+"',");
            builder.append(map.get("category_id")+"),");
        }
        jdbcTemplate.update(builder.substring(0, builder.length()-1));
    }
}
