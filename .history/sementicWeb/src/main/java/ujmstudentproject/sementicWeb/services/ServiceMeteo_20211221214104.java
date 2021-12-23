package ujmstudentproject.sementicweb.services;
import org.apache.jena.rdf.model.Model;
import org.jsoup.nodes.Document;

import ujmstudentproject.sementicweb.models.Meteo;


public interface ServiceMeteo{
    /*
      cette fonction prend en paramètre l'école  et la ville
      pour renvoyer le rdf correspondant.
    */
    public Model meteoToRDF(Meteo meteo, String city);
    public Document parserWebString();
}