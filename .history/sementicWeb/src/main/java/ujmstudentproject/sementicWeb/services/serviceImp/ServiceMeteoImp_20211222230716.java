package ujmstudentproject.sementicweb.services.serviceimp;

import org.springframework.stereotype.Service;

import ujmstudentproject.sementicweb.models.Meteo;
import ujmstudentproject.sementicweb.services.ServiceMeteo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Hashtable;

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
        String max = "" , min = "" ;
        String valMax = "", valMin= "";
        // un dictionaire de données meteo qui prend key value
        Hashtable<String, Hashtable<String, String>> my_dict = new Hashtable<String, Hashtable<String, String>>();
        Hashtable<String,String> max_min_dict = new Hashtable<String, String>();


        try {
            final Document doc = Jsoup.connect(url).get();
            String html = String.valueOf(doc.select("body"));
            Document doc1 = Jsoup.parse(html);
            Elements tab = doc1.select(" center > table > tbody > tr");
            Elements em = tab.get(4).select("tr");
            Elements em1 = tab.get(5).select("tr");
            for(Element row: em){
               
                if(row.select("td").size() == 5){
                    
                    max = row.select("td").get(0).text();
                    min = row.select("td").get(1).text();
                 
                }
            }
            // recuperation des valeurs des temperatires  
            for(Element row: em1){
                if(row.select("td").size() == 5){
                    valMax = row.select("td").get(0).text();
                    valMin = row.select("td").get(1).text();
                }
            }
        } catch (Exception e) {
           e.printStackTrace();
        }

        System.out.println(max + "-->" + valMax);
        System.out.println(min + "-->" + valMin);
        max_min_dict.put(max, valMax);
        max_min_dict.put(min, valMin);
        my_dict.put("12-02-22", max_min_dict);
    }

    @Override
    public void saveDataInCsv(String maxTemperature, String minTemperature, String date) throws FileNotFoundException{
        File fileCSV = new File("meteo.csv");
        PrintWriter out = new PrintWriter(fileCSV);
        out.println(maxTemperature + ";" + minTemperature + ";" + date);
    }

}