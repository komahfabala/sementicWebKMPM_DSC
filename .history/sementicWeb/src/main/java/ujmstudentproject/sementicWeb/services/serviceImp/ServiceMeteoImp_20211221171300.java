package ujmstudentproject.sementicweb.services.serviceimp

import org.springframework.stereotype.Service;

import ujmstudentproject.sementicweb.models.Meteo;
import ujmstudentproject.sementicweb.services.ServiceMeteo;
import org.apache.jena.rdf.model.Model;


@Service
public class ServiceMeteoImp implements ServiceMeteo{
    /**
     *  Dans cette classe on implemente toutes les fonction qui consernent 
     *  les données meterologiques (accès et modification).
     */

    public Model meteoToRDF(Meteo meteo, String city){
        return null;
    };

}