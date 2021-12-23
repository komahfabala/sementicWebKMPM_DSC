import ujmstudentproject.sementicWeb.models.Meteo;
import org.apache.jena.rdf.model.literal;
import org.apache.jena.rdf.model.Model;

public interface ServiceMeteo{
    /*
      cette fonction prend en paramètre l'école  et la ville
      pour renvoyer le rdf correspondant.
    */
    public Model schoolToRDF(Meteo school, String city);
}