package uz.ishbor.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import uz.ishbor.Component.Util;
import uz.ishbor.DAO.VacancyDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by sherxon on 1/10/16.
 */
@RestController
@RequestMapping(value = "/vacancy")
public class VacancyController {

    @Autowired
    private Util util;

    @Autowired
    private VacancyDao vacancyDao;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getList(HttpServletRequest request, HttpServletResponse response){
        List list= Collections.emptyList();
        try {
            Integer integer=Integer.parseInt(request.getParameter("categoryId"));
            list=vacancyDao.getList(integer);
        }catch (Exception e){
            e.printStackTrace();
        }
        return util.toJson(list);
    }
    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getVacancyInfor(@PathVariable Integer id){
        List list=vacancyDao.getList(id);
        return util.toJson(list);
    }

    @RequestMapping(value = "/search/{tag}", produces = "application/json; charset=utf-8")
    public String searchVacancy(@PathVariable String tag){
        List list=vacancyDao.searchByTag(tag);
        return util.toJson(list);
    }

    @RequestMapping(value = "/html/{id}")
    public ModelAndView searchVacancy(@PathVariable Integer id){
        Map<String, Object> item=vacancyDao.find(id);
        ModelMap modelMap=new ModelMap();
        modelMap.put("item", vacancyDao);
        return new ModelAndView("jobdesc", modelMap);
    }

}
