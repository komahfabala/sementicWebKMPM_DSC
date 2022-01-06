package ujmstudentproject.sementicweb.dao;

import javax.management.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ujmstudentproject.sementicweb.models.Building;
import ujmstudentproject.sementicweb.services.ServiceQuery;
import ujmstudentproject.sementicweb.services.serviceimp.ServiceQueryImp;

@Repository
public class BuildDao {
    @Autowired
    ServiceQuery query ;
    
    Building build = new Building();

    public Building getBuild(){
        query.queryTempBuilding("4545", "timeQ", "room");
        return build;
    }
}
