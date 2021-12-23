package ujmstudentproject.sementicweb.services;
import java.io.FileNotFoundException;

import org.apache.jena.rdf.model.Model;
import ujmstudentproject.sementicweb.models.Meteo;


public interface ServiceMeteo{
    /*
      cette fonction prend en paramètre l'école  et la ville
      pour renvoyer le rdf correspondant.
    */
    public Model meteoToRDF(Meteo meteo, String city);
    public void parserWebString() throws FileNotFoundException;
    public void saveDataInCsv(String maxTemperature, String minTemperature, String date) throws FileNotFoundException;
}