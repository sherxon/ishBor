package uz.ishbor.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import uz.ishbor.Component.Util;
import uz.ishbor.DAO.VacancyDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
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
    public String getList(HttpServletRequest request, HttpServletResponse response) {
        List list = Collections.emptyList();
        try {
            Integer integer = Integer.parseInt(request.getParameter("categoryId"));
            list = vacancyDao.getList(integer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return util.toJson(list);
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getVacancyInfor(@PathVariable Integer id) {
        List list = vacancyDao.getList(id);
        return util.toJson(list);
    }

    @RequestMapping(value = "/search/{tag}", produces = "application/json; charset=utf-8")
    public String searchVacancy(@PathVariable String tag) {
        List list = vacancyDao.searchByTag(tag);
        return util.toJson(list);
    }

    @RequestMapping(value = "/latest", produces = "application/json; charset=utf-8")
    public String latestVacancy() {
        List list = vacancyDao.getLatest();
        return util.toJson(list);
    }
    @RequestMapping(value = "/uploaded", produces = "application/json; charset=utf-8")
    public String uploaded(HttpServletRequest request) {
        String userId=request.getParameter("userId");
        String filename=request.getParameter("filename");
        Map<String, String> map= new HashMap<>();
        map.putIfAbsent("userId", userId);
        map.putIfAbsent("filename", filename);
       return util.toJson(map);
    }

    @RequestMapping(value = "/upload", produces = "application/json; charset=utf-8")
    public String uploadNewsFile(HttpServletRequest request){

        MultipartHttpServletRequest filereq= (MultipartHttpServletRequest) request;
        MultipartFile file = filereq.getFile("file");
        Map<String, String> status= new HashMap<>();

        if(!file.isEmpty()){
            if(!file.getContentType().equals("image/jpeg")
                    || !file.getContentType().equals("image/jpg")
                    || !file.getContentType().equals("application/pdf")
                    || !file.getContentType().equals("image/png")){
                String rootPath = request.getServletContext().getRealPath("/upload/");
                File dir = new File(rootPath);

                if(!dir.exists()) {
                    Boolean mkdirs = dir.mkdirs();
                    if(!mkdirs){
                        status.put("status", "bad");
                        status.put("cause", "cannot create directory");
                        return util.toJson(status);
                    }
                }
                String ext="";
                if(file.getOriginalFilename().length()>1)
                    ext="."+file.getOriginalFilename().split("\\.")[1];

                String filename=System.nanoTime() + ext;
                File serverFile=new File(dir.getAbsoluteFile() +File.separator+ filename);
                try(BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                    stream.write(file.getBytes());
                    status.put("filename", filename);
                    status.put("status", "ok");
                    return util.toJson(status);
                } catch (IOException e) {
                    e.printStackTrace();
                    status.put("status", "cannot upload");
                    return util.toJson(status);
                }
            }else{
                status.put("status", "wrong format");
                return util.toJson(status);
            }
        }else{
            status.put("status", "empty file");
            return util.toJson(status);
        }
    }

    @RequestMapping(value = "/html/{id}")
    public ModelAndView searchVacancy(@PathVariable Integer id) {
        Map<String, Object> item = vacancyDao.find(id);
        ModelMap modelMap = new ModelMap();
        item.put("company_name", String.valueOf(item.get("company_name")).replace("\"", "'"));
        modelMap.put("item", util.toJson(item));
        return new ModelAndView("jobdesc", modelMap);
    }

}
