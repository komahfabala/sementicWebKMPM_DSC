package ujmstudentproject.sementicWeb.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.Charsets;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.XSD;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ujmstudentproject.sementicWeb.Connection.Fuseki;

@RestController
@RequestMapping("/")
public class Accueil{

    @GetMapping("/rdf")
    public Model weather(Map<String, ArrayList<String>> tempValueMap, String code){
        //www.meteociel.fr/temps-reel/obs_villes.php?code2=7475&jour2=22&mois2=11&annee2=2021
        String temp_uri = "https://www.meteociel.fr/temps-reel/obs_villes.php";
        Model model = ModelFactory.createDefaultModel();
        
        model.setNsPrefix("meteo", temp_uri)
                .setNsPrefix("rdfs", RDFS.getURI())
                .setNsPrefix("xsd", XSD.getURI());
        
        Property codeProperty = model.createProperty(temp_uri + "code");
        Property hourProperty = model.createProperty(temp_uri + "hour");
        Property degreProperty = model.createProperty(temp_uri + "degre");

        for(int i=0;i<tempValueMap.get("Hour").size();i++){
            String hour = tempValueMap.get("Hour").get(i);
            String degre = tempValueMap.get("Degre").get(i);
            if(hour.equals("Heure")){
                continue;
            }
            model.createResource()
            .addProperty(hourProperty,hour.replace(" h", ""),XSDDatatype.XSDinteger)
            .addProperty(codeProperty,code)
            .addProperty(degreProperty,degre,XSDDatatype.XSDdecimal);
        }
        return model;
    }

    @PostMapping("/csv")
    public void readCSV(String filePath){
       String sep = ";";
       Model models = ModelFactory.createDefaultModel();  
       Map<String, ArrayList<String>> tempValueMap = new HashMap<String, ArrayList<String>>();

       try{
           File csvFile = new File(filePath);
            FileReader fr = new FileReader(csvFile, Charsets.UTF_8);
            BufferedReader bReader = new BufferedReader(fr);
            String line = "";
           //0==Heure;1==Nb.;2==Visi;3==Temperature
           tempValueMap.put("Hour", new ArrayList<String>());
           tempValueMap.put("Degre", new ArrayList<String>());
           String[] arrayStrings;
           while((line = bReader.readLine()) !=null){
               arrayStrings = line.split(sep);
               tempValueMap.get("Hour").add(arrayStrings[0]);
               tempValueMap.get("Degre").add(arrayStrings[3]);
               //models.add(weather("7475", arrayStrings[3], arrayStrings[0]));
               
               /*
               System.out.println(arrayStrings[0] + " ");
               for(String lines : arrayStrings){  // ===> int i=0; i<arrayStrings.length;i++
                   System.out.println(lines + " ");
               }
               */
               System.out.println();
           }

           models = weather(tempValueMap,"7475");
           //System.out.println(tempValueMap.get("Hour").get(3));

           models.write(System.out, "TURTLE");
           bReader.close();
           
       }catch(IOException ex){
           ex.printStackTrace();
       }

    }
    
    @GetMapping
    public void createCon(){
        Fuseki test = Fuseki.builder().datasetURL("http://localhost:3030/station").build();
        
        String datasetURL = test.getDatasetURL();
        String sparqlEndpoint = datasetURL + "/sparql";
        //String sparqlUpdate = datasetURL + "/update";
        //String graphStore = datasetURL + "/data";
        try(  RDFConnection conneg = RDFConnection.connect(sparqlEndpoint)){
           
            QueryExecution q = conneg.query("Select * Where{?subject ?predicate ?object}");
            System.out.println(q.getQuery());
       ResultSet rs = q.execSelect();
       System.out.println(rs.getRowNumber());
       while(rs.hasNext()) {
           QuerySolution qs = rs.next() ;
           Resource subject = qs.getResource("subject") ;
           System.out.println("Subject: "+subject.getLocalName()) ;
       }
       q.close() ;
       conneg.close() ;
       }; 
    }

}
