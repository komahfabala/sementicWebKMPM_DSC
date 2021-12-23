package ujmstudentproject.sementicweb.services;
import org.apache.jena.rdf.model.Model;

public interface ServiceMeteo{
    /*
      cette fonction prend en paramètre l'école  et la ville
      pour renvoyer le rdf correspondant.
    */
    public Model buildingToRDF(Building Bding, String city);
}