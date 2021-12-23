import ujmstudentproject.sementicweb.models.Meteo;
import org.apache.jena.model.Model;
public interface  ServiceCity {
    public Model cityToRDF(Meteo meteo, String city);
}