import java.util.Date;
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
    public void saveDataInCsv(Hashtable<String, String[]> dt); 
    public void loaderRDF(Model model); // charge ttl sur fuseki
    public Model rdfModel(); // retourne un model rdf
    public Date myDateFormDate(String d_date);
}