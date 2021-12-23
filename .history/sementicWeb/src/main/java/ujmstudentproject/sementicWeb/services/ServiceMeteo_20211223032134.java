package ujmstudentproject.sementicweb.services;

import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.jena.rdf.model.Model;
import ujmstudentproject.sementicweb.models.Meteo;


public interface ServiceMeteo{
    /*
      cette fonction prend en paramètre l'école  et la ville
      pour renvoyer le rdf correspondant.
    */
    public Model meteoToRDF(Meteo meteo, String city);
    public void parserWebString();
    public void saveDataInCsv(/*ArrayList<String> tp_list*/ Hashtable<String, String[]> dt);
}