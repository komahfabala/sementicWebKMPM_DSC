package ujmstudentproject.sementicweb.dao;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ujmstudentproject.sementicweb.models.Building;
import ujmstudentproject.sementicweb.services.ServiceQuery;


@Repository
public class BuildDao {

    Map<String, ArrayList<String>> data;
    @Autowired
    static
    ServiceQuery query ;
    
    Building build = new Building();

    static Map<String, ArrayList<String>> getAllTemp(){
        Map<String, ArrayList<String>> data = query.queryTempAllBuilding();

        System.out.println(data.get("time").get(1));
        return data;
    }
}
