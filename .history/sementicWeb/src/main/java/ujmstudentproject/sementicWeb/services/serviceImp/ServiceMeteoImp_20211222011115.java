package ujmstudentproject.sementicweb.services.serviceimp;

import org.springframework.stereotype.Service;

import ujmstudentproject.sementicweb.models.Meteo;
import ujmstudentproject.sementicweb.services.ServiceMeteo;
import org.apache.jena.rdf.model.Model;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


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
            String html = String.valueOf(doc.select("body"));
            Document doc1 = Jsoup.parse(html);
            Element tab = doc1.select("tbody").first();
            Elements tb = tab.getElementsByTag("tr");
            for(Element row : tb){
                System.out.println(row.select("td > b").get(0).text());
                //System.out.println("\n");
                //System.out.println(row.select("td"));
                //String m = row.select("td > b").get(0).text();
                //System.out.println(m);
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

}