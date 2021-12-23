import ujmstudentproject.sementicweb.models.Meteo;
import org.apache.jena.rdf.model.Model;
public interface  ServiceCity {
    /**
     * 
     * @param meteo
     * @param city
     * @return un object rdf 
     */
    public Model  CityToRDF(Meteo meteo, String city);
}