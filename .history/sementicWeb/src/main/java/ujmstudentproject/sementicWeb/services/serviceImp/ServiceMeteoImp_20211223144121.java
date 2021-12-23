package ujmstudentproject.sementicweb.services.serviceimp;


import org.springframework.stereotype.Service;


import ujmstudentproject.sementicweb.models.Meteo;
import ujmstudentproject.sementicweb.services.ServiceMeteo;


import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import com.opencsv.CSVWriter;

import org.apache.jena.rdf.model.Model;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


@Service
public class ServiceMeteoImp implements ServiceMeteo{
    public String url = "https://www.meteociel.fr/temps-reel/obs_villes.php?code2=7475&jour2=";
    public String mois = "&mois2=";
    public String annee = "&annee2=2021";
    
    /**
     *  Dans cette classe on implemente toutes les fonction qui consernent 
     *  les données meterologiques (accès et modification).
     */

    public Model meteoToRDF(Meteo meteo, String city){
        return null;
    }

    public void parserWebString(){
        String max = "" , min = "" ;
        
        String myUrl = "";
        String date = "12-12-22";
        // le sraping du site meteo de saint-etienne
        Hashtable<String, String[]> dt = new Hashtable<String, String[]>();
        try {
            int month = 0;
            while(month <= 10){ // recuperation de toutes les données de l'annee sur saint-etienne
                for(int i=1; i <= 30; i++){ // recuperation par mois 
                    myUrl = url + i + mois + String.valueOf(month) + annee; // url pour la pagination à revoir 
                
                    final Document doc = Jsoup.connect(myUrl).userAgent("mozilla").timeout(3000).get();
                    String html = String.valueOf(doc.select("body"));
                    Document doc1 = Jsoup.parse(html);
                    
                    Elements ft = doc1.select("font > center"); // recuperation de la date 
                    for (Element element : ft) {
                        date = element.text();
                    }
                    Elements tab = doc1.select(" center > table > tbody > tr");
                    Elements em = tab.get(4).select("tr");
                    Elements em1 = tab.get(5).select("tr");
            
                    for(Element row: em){
                        if(row.select("td").size() == 5){
                            max = row.select("td").get(0).text();
                            min = row.select("td").get(1).text();
                        }
                    }
                    
                    for(Element row: em1){ // recuperation des valeurs des temperatures  
                        if(row.select("td").size() == 5){
                            String valMax = row.select("td").get(0).text();
                            String valMin = row.select("td").get(1).text();
                            double c = Math.random()*(1000-1); // à remplacer par la datae
                            dt.put(String.valueOf(c), new String []{valMax,valMin,date});
                            saveDataInCsv(dt);
                        }
                    }
                }
                month++;
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    @Override
    public void saveDataInCsv( Hashtable<String, String[]> dict_temp) {
        /**
         *  cette fonction enregistre les données meteorologique dans un fichier csv.
         */
            try {
                // creation du fichier
                CSVWriter writeFile = new CSVWriter(new FileWriter("meteo.csv"),',');
                String set1[] = {"maxTp","minTp","date"}; // en tete du fichier
                ArrayList<String[]> data = new ArrayList<String[]>(1000); // la lsite de 
                data.add(set1);
                Enumeration names = dict_temp.keys();
                while( names.hasMoreElements()){ // parcours du dictionaiore
                    String key = (String) names.nextElement();
                    data.add(dict_temp.get(key));
                }
                writeFile.writeAll(data); // enregistrement de data
		        writeFile.close();
                System.out.println("données enregistrées");
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        
    }

}
