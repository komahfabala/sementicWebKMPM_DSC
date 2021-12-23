package ujmstudentproject.sementicweb.services.serviceimp;

import org.springframework.stereotype.Service;

import ujmstudentproject.sementicweb.models.Meteo;
import ujmstudentproject.sementicweb.services.ServiceMeteo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.opencsv.CSVWriter;

import org.apache.jena.rdf.model.Model;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Request;
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
        try {
            for(int i=1; i <= 31; i++){
                myUrl = url + i + mois + "10" + annee;
                System.out.println(myUrl);
                final Document doc = Jsoup.connect(myUrl).timeout(3000).get();
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
                        String valMax = row.select("td").get(0).text();
                        String valMin = row.select("td").get(1).text();
                        //System.out.println(max + "-->" + valMax);
                        //System.out.println(min + "-->" + valMin);
                        saveDataInCsv(valMax, valMin, date);
                    }
                }
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    @Override
    public void saveDataInCsv(String maxTemperature, String minTemperature, String date) {
            try {
                CSVWriter writeFile = new CSVWriter(new FileWriter("meteo.csv"),',');
                String set1[] = {"maxTp","minTp","date"};    
                ArrayList<String[]> data = new ArrayList<String[]>(100);
                data.add(set1);
                data.add(new String[] {maxTemperature,minTemperature,date});
                writeFile.writeAll(data);
		        writeFile.close();
                System.out.println("données enregistrées");
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        
    }

}

/*
    PrintWriter out = new PrintWriter(fileCSV);
            out.println(maxTemperature + ";" + minTemperature + ";" + date);
            out.flush();
            out.close();
            ----------------
            CSVWriter writeFile = new CSVWriter(new FileWriter("meteo.csv"),',');
                String set1[] = {"maxTp","minTp","date"};    
                ArrayList<String[]> data = new ArrayList<String[]>(100);
                data.add(set1);
                data.add(new String[] {maxTemperature,minTemperature,date});
                writeFile.writeAll(data);
		        writeFile.close();
                System.out.println("données enregistrées");
*/