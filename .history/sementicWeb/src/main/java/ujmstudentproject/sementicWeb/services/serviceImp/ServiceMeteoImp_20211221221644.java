package ujmstudentproject.sementicweb.services.serviceimp;

import org.springframework.stereotype.Service;

import ujmstudentproject.sementicweb.models.Meteo;
import ujmstudentproject.sementicweb.services.ServiceMeteo;
import org.apache.jena.rdf.model.Model;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


@Service
public class ServiceMeteoImp implements ServiceMeteo{
    public final String url = "https://www.meteociel.fr/temps-reel/obs_villes.php?code2=7475&jour2=13&mois2=10&annee2=2021";
    
    /**
     *  Dans cette classe on implemente toutes les fonction qui consernent 
     *  les données meterologiques (accès et modification).
     */

    public Model meteoToRDF(Meteo meteo, String city){
        return null;
    }

    public void parserWebString(){
        
        try {
            final Document doc = Jsoup.connect(url).get();
            System.out.println(doc.outerHtml());
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

}