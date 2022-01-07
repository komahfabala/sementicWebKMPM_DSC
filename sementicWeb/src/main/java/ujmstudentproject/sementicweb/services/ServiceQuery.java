package ujmstudentproject.sementicweb.services;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Service;

import ujmstudentproject.sementicweb.models.Building;

@Service
public interface ServiceQuery {
    public Building queryTempBuilding(String dateQ);
    public Map<String, ArrayList<String>> queryTempAllBuilding();
}
