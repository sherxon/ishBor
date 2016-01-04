package uz.ishbor.Component;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

/**
 * Created by sherxon on 1/3/16.
 */
@Component
public class Util {
    private Gson gson= new Gson();

    public String toJson(Object object){
        return gson.toJson(object);
    }
    public <T> Object fromJson(String rsr, Class<T> tClass){
       return gson.fromJson(rsr, tClass);
    }
}
