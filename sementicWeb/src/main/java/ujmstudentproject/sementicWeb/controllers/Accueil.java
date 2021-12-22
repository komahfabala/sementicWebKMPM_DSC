package ujmstudentproject.sementicWeb.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdfconnection.RDFConnection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ujmstudentproject.sementicWeb.Connection.Fuseki;
import org.apache.commons.codec.Charsets;

@RestController
@RequestMapping("/")
public class Accueil{

    @PostMapping("/csv")
    public void readCSV(String filePath){
       String sep = ";";
       try{
           File csvFile = new File(filePath);
          
         FileReader fr = new FileReader(csvFile, Charsets.UTF_8);
         BufferedReader bReader = new BufferedReader(fr);
            String line = "";
           //0==Heure;1==Nb.;2==Visi;3==Temperature
           String[] arrayStrings;
           while((line = bReader.readLine()) !=null){
               arrayStrings = line.split(sep);
               System.out.println(arrayStrings[0] + " ");
               for(String lines : arrayStrings){  // ===> int i=0; i<arrayStrings.length;i++
                   System.out.println(lines + " ");
               }
               System.out.println();
           }
           
       }catch(IOException ex){
           ex.printStackTrace();
       }

    }
    
    @GetMapping
    public void createCon(){
        Fuseki test = Fuseki.builder().datasetURL("http://localhost:3030/station").build();
        
        String datasetURL = test.getDatasetURL();
        String sparqlEndpoint = datasetURL + "/sparql";
        String sparqlUpdate = datasetURL + "/update";
        String graphStore = datasetURL + "/data";
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
