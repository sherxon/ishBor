package uz.ishbor.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uz.ishbor.Component.Util;
import uz.ishbor.DAO.ParserDao;


@RestController
public class ParserController {

    @Autowired
    private Util util;

    @Autowired
    private ParserDao parserDao;

	@RequestMapping(method = RequestMethod.GET, value = "/",  produces = "application/json; charset=utf-8")
    public String parse() {
        return util.toJson(parserDao.parseFromSuperJob());
	}
    @RequestMapping(method = RequestMethod.GET, value = "/write")
    public void write() {
        parserDao.writeDataToDb();
	}


}