package uz.ishbor.DAO;

import java.util.List;
import java.util.Map;

/**
 * Created by sherxon on 1/3/16.
 */
public interface ParserDao{

    List<Map<String, Object>> parseFromSuperJob();
    void writeSJVacancyToDb();
    public List<Map<String, Object>> parseCategorySuperJob();
    public void writeSJCategoryToDb();
}
