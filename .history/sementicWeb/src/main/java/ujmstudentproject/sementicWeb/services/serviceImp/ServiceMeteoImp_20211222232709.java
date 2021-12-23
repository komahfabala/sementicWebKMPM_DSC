package ujmstudentproject.sementicweb.services.serviceimp;

import org.springframework.stereotype.Service;

import ujmstudentproject.sementicweb.models.Meteo;
import ujmstudentproject.sementicweb.services.ServiceMeteo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

    public void parserWebString() throws FileNotFoundException{
        String max = "" , min = "" ;
        String valMax = "", valMin= "";
        // le sraping du site meteo de saint-etienne
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
            // recuperation des valeurs des temperatures  
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
        String date = "12-12-22";
        saveDataInCsv(valMax, valMin, date);
    }

    @Override
    public void saveDataInCsv(String maxTemperature, String minTemperature, String date) {
        try {
            File fileCSV = new File("meteo.csv");
            PrintWriter out = new PrintWriter(fileCSV);
            out.println(maxTemperature + ";" + minTemperature + ";" + date);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}