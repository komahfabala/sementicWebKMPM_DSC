import org.apache.jena.rdf.model.Model;
import org.springframework.stereotype.Service;

@Service
public class ServiceMeteoImp implements ServiceMeteo{
    /**
     *  Dans cette classe on implemente toutes les fonction qui consernent 
     *  les données meterologiques (accès et modification).
     */
    public Model meteoToRDF(Meteo meteo, String city){
        return Model; // graph rdf 
    }

}