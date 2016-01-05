package uz.ishbor.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uz.ishbor.Component.Util;
import uz.ishbor.DAO.CategoryDao;

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

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getList(){
       return util.toJson(categoryDao.list());
    }
}
