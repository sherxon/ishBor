package uz.ishbor.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uz.ishbor.Component.Util;
import uz.ishbor.DAO.CategoryDao;
import uz.ishbor.DAO.VacancyDao;

/**
 * Created by sherxon on 1/4/16.
 */
@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private Util util;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private VacancyDao vacancyDao;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getList(){
       return util.toJson(categoryDao.list());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getListById(@PathVariable Integer id){
       return util.toJson(categoryDao.list(id));
    }

    @RequestMapping(value = "/{id}/vacancy", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getVacancy(@PathVariable Integer id){
       return util.toJson(vacancyDao.getList(id));
    }

    @RequestMapping(value = "/{cid}/vacancy/{vid}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getVacancy(@PathVariable Integer cid, @PathVariable Integer vid){
       return util.toJson(vacancyDao.find(vid));
    }
}
