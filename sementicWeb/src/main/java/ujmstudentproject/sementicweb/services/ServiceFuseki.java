package ujmstudentproject.sementicweb.services;

import java.util.*;

import org.apache.jena.rdf.model.Model;
import org.springframework.stereotype.Service;

@Service
public interface ServiceFuseki{
    public void readCSV_From_Meteo(String filePath);
    public void readCSV_From_Territoire(String filePath);
    public void loadTTL(String filepath);
    public Model weather(Map<String, ArrayList<String>> tempValueMap, String code);
    //public void createCon();

}