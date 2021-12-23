import ujmstudentproject.sementicweb.models.Meteo;
import org.apache.jena.model.Model;
public interface  ServiceCity {
    /**
     * 
     * @param meteo
     * @param city
     * @return un object rdf 
     */
    public Model cityToRDF(Meteo meteo, String city);
}