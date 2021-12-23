package ujmstudentproject.sementicweb.services;
import org.apache.jena.rdf.model.Model;

import ujmstudentproject.sementicweb.models.Meteo;

public interface ServiceMeteo{
    /*
      cette fonction prend en paramètre l'école  et la ville
      pour renvoyer le rdf correspondant.
    */
    public Model buildingToRDF(Meteo meteo, String city);
}