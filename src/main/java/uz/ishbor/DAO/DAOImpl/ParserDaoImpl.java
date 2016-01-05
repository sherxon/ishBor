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
        String sql="select * from raw_category";
        List<Map<String, Object>> cats= jdbcTemplate.queryForList(sql);
        List<Map<String, Object>> mapList= new ArrayList<>();
        for (Map<String, Object> cat : cats) {
          mapList.addAll(getAllVacancyofCategory(String.valueOf(cat.get("cat_link")), Integer.parseInt(String.valueOf(cat.get("id")))));
        }

        return mapList;
    }

    private Collection<? extends Map<String, Object>> getAllVacancyofCategory(String cat_link,  Integer catId) {
        List<Map<String, Object>> mapList= new ArrayList<>();
        try {
            Document doc= Jsoup.connect("http://www.superjob.uz"+cat_link).
                    timeout(20000).userAgent("Mozilla").
                    get();

            Elements jobDivs=doc.select(".LandingPageListElement");
            for (Element jobDiv : jobDivs) {
                try {
                    Map<String, Object> map= new HashMap<>();
                    map.put("vacancy_id", jobDiv.attributes().get("vac-id"));
                    map.put("st_date", jobDiv.select(".LandingPageListElement_date").text());
                    map.put("vac_position", jobDiv.select(".LandingPageListElement_position").text());
                    map.put("st_price", jobDiv.select(".LandingPageListElement_price").text());
                    map.put("company_name", jobDiv.select(".LandingPageListElement_company").text().replaceAll("\'", "\\'\\'"));
                    map.put("company_url", jobDiv.select(".LandingPageListElement_company").select("a").attr("href"));
                    map.put("descc" , jobDiv.select(".LandingPageListElement_desc").text());
                    map.put("category_name" , "Маркетинг, реклама, PR");
                    map.put("category_id" , catId);
                    map.put("category_link" , cat_link);
                    mapList.add(map);
                }catch (Exception e){
                    System.out.println("error in parsing " + e.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapList;
    }


    @Override
    public List<Map<String, Object>> parseCategorySuperJob() {
        List<Map<String, Object>> mapList= new ArrayList<>();
        try {
            Document doc= Jsoup.connect("http://www.superjob.uz/vakansii/").
                    timeout(20000).userAgent("Mozilla").
                    get();

            Elements catLinks=doc.select(".ProfessionsListing_link");
            for (Element catLink : catLinks) {
                try {
                    Map<String, Object> map= new HashMap<>();
                    map.put("cat_link", catLink.attr("href"));
                    map.put("cat_name", catLink.text());
                    mapList.add(map);
                }catch (Exception e){
                    System.out.println("error in parsing " + e.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapList;
    }

    @Override
    public void writeSJVacancyToDb() {
        List<Map<String, Object>> data= parseFromSuperJob();
        StringBuilder builder=new StringBuilder();
        builder.append("insert into raw_vacancy(vacancy_id, st_date,vac_position, st_price, company_name, company_url,descc,category_name,category_id, created, source ) values ");
        for (Map<String, Object> map : data) {
            builder.append("('"+map.get("vacancy_id")+"','");
            builder.append(map.get("st_date")+"','");
            builder.append(map.get("vac_position")+"','");
            builder.append(map.get("st_price")+"','");
            builder.append(map.get("company_name")+"','");
            builder.append(map.get("company_url")+"','");
            builder.append(map.get("descc")+"','");
            builder.append(map.get("category_name")+"',");
            builder.append(map.get("category_id")+",'");
            builder.append(new Date().toString()+"','");
            builder.append("http://www.superjob.uz/'),");
        }
        jdbcTemplate.update(builder.substring(0, builder.length()-1));
    }
    @Override
    public void writeSJCategoryToDb() {
        List<Map<String, Object>> data= parseCategorySuperJob();
        StringBuilder builder=new StringBuilder();
        builder.append("insert into raw_category(cat_name, cat_link, created, source ) values ");
        for (Map<String, Object> map : data) {
            builder.append("('"+map.get("cat_name")+"','");
            builder.append(map.get("cat_link")+"','");
            builder.append(new Date().toString()+"','");
            builder.append("http://www.superjob.uz/'),");
        }
        jdbcTemplate.update(builder.substring(0, builder.length()-1));
    }
}
