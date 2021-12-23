import ujmstudentproject.sementicweb.models.*;
import org.apache.jena.rdf.model.Model;

public interface ServiceMeteo{
    /*
      cette fonction prend en paramètre l'école  et la ville
      pour renvoyer le rdf correspondant.
    */
    public Model schoolToRDF(School school, String city);
}