package ujmstudentproject.sementicweb.services;
import ujmstudentproject.sementicweb.models.Building;
import org.apache.jena.rdf.model.Model;

public interface ServiceMeteo{
    /*
      cette fonction prend en paramètre l'école  et la ville
      pour renvoyer le rdf correspondant.
    */
    public Model schoolToRDF(Building school, String city);
}