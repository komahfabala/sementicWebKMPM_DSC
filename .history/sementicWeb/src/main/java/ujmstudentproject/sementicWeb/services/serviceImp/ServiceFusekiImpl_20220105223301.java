package ujmstudentproject.sementicweb.services.serviceimp;

import java.io.*;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.codec.Charsets;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.rdfconnection.*;

import org.apache.jena.vocabulary.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import org.springframework.stereotype.Service;

import ujmstudentproject.sementicweb.services.ServiceFuseki;

@Service
public class ServiceFusekiImpl implements ServiceFuseki{

    public Model weather(Map<String, ArrayList<String>> tempValueMap, String code){
        //www.meteociel.fr/temps-reel/obs_villes.php?code2=7475&jour2=22&mois2=11&annee2=2021
        String temp_uri = "https://www.meteociel.fr/temps-reel/obs_villes.php/";
        Model model = ModelFactory.createDefaultModel();

        model.setNsPrefix("meteo", temp_uri)
                .setNsPrefix("rdfs", RDFS.getURI())
                .setNsPrefix("xsd", XSD.getURI());

        Property codeProperty = model.createProperty(temp_uri + "code");
        Property maxTpProperty = model.createProperty(temp_uri + "maxTp");
        Property minTpProperty = model.createProperty(temp_uri + "minTp");
        Property dateProperty = model.createProperty(temp_uri + "date");

        for(int i=0;i<tempValueMap.get("maxTp").size();i++){
            String maxTp = tempValueMap.get("maxTp").get(i).replace(" °C", "").replace("\"", "");
            String minTp = tempValueMap.get("minTp").get(i).replace(" °C", "").replace("\"", "");
            String date = tempValueMap.get("date").get(i).replace("\"", "");

            model.createResource()
                    .addProperty(codeProperty,code)
                    .addProperty(dateProperty,date, XSDDatatype.XSDdate)
                    .addProperty(maxTpProperty,maxTp,XSDDatatype.XSDdecimal)
                    .addProperty(minTpProperty,minTp,XSDDatatype.XSDdecimal);
        }
        return model;
    }
    public void readCSV_From_Meteo(String filePath){
        String sep = ",";
        Model models = ModelFactory.createDefaultModel();
        Map<String, ArrayList<String>> tempValueMap = new HashMap<String, ArrayList<String>>();

        try{
            File csvFile = new File(filePath);
            FileReader fr = new FileReader(csvFile, Charsets.UTF_8);
            BufferedReader bReader = new BufferedReader(fr); // Lecture dans le fichier
            String line = "";
            bReader.readLine();
            bReader.readLine();
            //System.out.println(bReader.readLine().split(",")[0] +" ");
            // 0=="maxTp",1=="minTp",2=="date"

            // Creation d'un dictionnaire avec des keys Hour et Degre
            tempValueMap.put("maxTp", new ArrayList<String>());
            tempValueMap.put("minTp", new ArrayList<String>());
            tempValueMap.put("date", new ArrayList<String>());
            String[] arrayStrings;
            while((line = bReader.readLine()) !=null){

                arrayStrings = line.split(sep);
                tempValueMap.get("maxTp").add(arrayStrings[0]);
                tempValueMap.get("minTp").add(arrayStrings[1]);
                tempValueMap.get("date").add(arrayStrings[2]);

            }

            models = weather(tempValueMap,"7475");
            File outTTL = new File("sementicWeb/src/main/java/ujmstudentproject/data/output-model.ttl");
            FileWriter out= new FileWriter(outTTL, Charsets.UTF_8);
            models.write(out, "TURTLE");
            out.close();
            //models.write(System.out, "TURTLE");
            bReader.close();
            System.out.println("Everything done!!");

        }catch(IOException ex){
            ex.printStackTrace();
        }

    }

    public void loadTTL(String filepath){
        String serviceURL = "http://localhost:3030/territoire";
        try(
                RDFConnection conneg = RDFConnection.connect(serviceURL)){
String line = "";
                    File ttl = new File(filepath);
                    try (FileReader fr = new FileReader(ttl, Charsets.UTF_8)) {
                        BufferedReader bReader = new BufferedReader(fr); // Lecture dans le fichier
                        while((line = bReader.readLine()) !=null){
                           System.out.println(line);
                        conneg.load(line); 
            
                        }
                        
                        
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    

            /*QueryExecution q = conneg.query("Select * Where{?subject ?predicate ?object} LIMIT 10");
            ResultSet rs = q.execSelect();
            while(rs.hasNext()) {
                QuerySolution qs = rs.next() ;
                Resource subject = qs.getResource("predicate") ;
                var s = qs.getLiteral("object");
                //System.out.println("Subject: "+subject.getNameSpace()) ;
                System.out.println("Subject: "+s) ;
            }
            q.close() ;*/
            conneg.commit();
            conneg.close() ;
        };
    }
    public void createCon(){
        String datasetURL = "http://localhost:3030/territoire";
        String sparqlEndpoint = datasetURL + "/sparql";
        //String sparqlUpdate = datasetURL + "/update";
        //String graphStore = datasetURL + "/data";
        try(
                RDFConnection conneg = RDFConnection.connect(sparqlEndpoint)){
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

    @Override
    public void readCSV_From_Territoire(String filePath) {
        String sep = ",";
        Model models = ModelFactory.createDefaultModel();
        Map<String, ArrayList<String>> tempValueMap = new HashMap<String, ArrayList<String>>();

        try{
            File csvFile = new File(filePath);
            FileReader fr = new FileReader(csvFile, Charsets.UTF_8);
            BufferedReader bReader = new BufferedReader(fr); // Lecture dans le fichier
            String line = "";
            bReader.readLine(); // Remove header
            //System.out.println(bReader.readLine().split(",")[0] +" ");
            // name,time,HMDT,LUMI,SND,SNDF,SNDM,TEMP,id,location,type

            // Creation d'un dictionnaire avec des keys Hour et Degre
            tempValueMap.put("time", new ArrayList<String>());
            tempValueMap.put("HMDT", new ArrayList<String>());
            tempValueMap.put("LUMI", new ArrayList<String>());
            tempValueMap.put("SND", new ArrayList<String>());
            tempValueMap.put("SNDF", new ArrayList<String>());
            tempValueMap.put("SNDM", new ArrayList<String>());
            tempValueMap.put("TEMP", new ArrayList<String>());
            tempValueMap.put("ID", new ArrayList<String>());
            tempValueMap.put("Location", new ArrayList<String>());
            
            String[] arrayStrings;
            while((line = bReader.readLine()) !=null){
                arrayStrings = line.split(sep);
                tempValueMap.get("time").add(arrayStrings[1]);
                tempValueMap.get("HMDT").add(arrayStrings[2]);
                tempValueMap.get("LUMI").add(arrayStrings[3]);
                tempValueMap.get("SND").add(arrayStrings[4]);
                tempValueMap.get("SNDF").add(arrayStrings[5]);
                tempValueMap.get("SNDM").add(arrayStrings[6]);
                tempValueMap.get("TEMP").add(arrayStrings[7]);
                tempValueMap.get("ID").add(arrayStrings[8]);
                tempValueMap.get("Location").add(arrayStrings[9]);
            }
        
        
            models = building(tempValueMap);
            File outTTL = new File("src/main/java/ujmstudentproject/data/output-building-model.ttl");
            FileWriter out= new FileWriter(outTTL, Charsets.UTF_8);
            models.write(out, "TURTLE");
            out.close();
           // models.write(System.out, "TURTLE");
            bReader.close();
            System.out.println("Everything done!!");

        }catch(IOException ex){
            ex.printStackTrace();
        }
        
    }
    private Model building(Map<String, ArrayList<String>> tempValueMap) {
        Model model = ModelFactory.createDefaultModel();
        String building_uri = "https://territoire.emse.fr/kg/emse/fayol/";
        String sosa_uri = "http://www.w3.org/ns/sosa/";
        String schema = "http://schema.org/";

        model.setNsPrefix("territoire", building_uri)
                .setNsPrefix("rdfs", RDFS.getURI())
                .setNsPrefix("xsd", XSD.getURI())
                .setNsPrefix("sosa", sosa_uri)
                .setNsPrefix("shma", schema);
// name,time,HMDT,LUMI,SND,SNDF,SNDM,TEMP,id,location,type
        Property obsProperty = model.createProperty(schema + "observation");
        Property sosaProperty = model.createProperty(sosa_uri + "detector");
        Property sosaValueProperty = model.createProperty(sosa_uri + "hasValue");
        Property locationProperty = model.createProperty(building_uri + "room");
        Property dateProperty = model.createProperty(schema + "date");
        Property timeProperty = model.createProperty(schema + "time");

        List<String> sensors = new ArrayList<String>(Arrays.asList("HMDT","LUMI","SND","SNDF","SNDM","TEMP"));
        for(int i=0;i<tempValueMap.get("ID").size();i++){
            
            int ind = 0;
            String timestamp = tempValueMap.get("time").get(i);
            Long ts = Long.parseLong(timestamp);
            Date mydate = new Date(ts/1000000);
            SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",Locale.ENGLISH);
            sfd.setTimeZone(TimeZone.getTimeZone("UTC"));
            String date = sfd.format(mydate);
            String dateTime = date.split("T")[0]; // Get date format yyyy-MM-dd
            
            String tim = date.split("T")[1]; //Get time format HH:mm:ss
            
              String room = tempValueMap.get("Location").get(i).toString().replace("emse/fayol/", "").replace("/","_");
            // We have only one detector per line so 
            String detector = "";
            if(!tempValueMap.get("HMDT").get(i).isEmpty()){
                ind = 0;
                detector = tempValueMap.get("HMDT").get(i);
            }
            else if(!tempValueMap.get("LUMI").get(i).isEmpty()){
                ind = 1;
                detector = tempValueMap.get("LUMI").get(i);
            }else if(!tempValueMap.get("SND").get(i).isEmpty()){
                ind = 2;
                detector = tempValueMap.get("SND").get(i);
            }else if(!tempValueMap.get("SNDF").get(i).isEmpty()){
                ind = 3;
                detector = tempValueMap.get("SNDF").get(i);
            }else if(!tempValueMap.get("SNDM").get(i).isEmpty()){
                ind = 4;
                detector = tempValueMap.get("SNDM").get(i);
            }else if(!tempValueMap.get("TEMP").get(i).isEmpty()){
                ind = 5;
                detector = tempValueMap.get("TEMP").get(i);
            }
            System.out.println(dateTime + "_"+tim+" "+ room + " "+detector);

            model.createResource(obsProperty+"_"+sensors.get(ind)+"_"+room+"_"+i)
            .addProperty(locationProperty, room)
            .addProperty(sosaProperty,sensors.get(ind), XSDDatatype.XSD)
            .addProperty(sosaValueProperty ,detector,XSDDatatype.XSDdecimal)
            .addProperty(dateProperty,dateTime,XSDDatatype.XSDdate)
            .addProperty(timeProperty,tim,XSDDatatype.XSDtime);
    
        }
        return model;
    }
}

/*
@prefix core: https://w3id.org/rec/core/ .
@prefix observation: http://localhost:3030/observation/ .
@prefix room: https://territoire.emse.fr/kg/emse/fayol/4ET/ .
@prefix sensor: http://localhost:3030/sensor/ .
@prefix sosa: http://www.w3.org/ns/sosa/ .
@prefix ssn: http://www.w3.org/ns/ssn/ .
@prefix xsd: http://www.w3.org/2001/XMLSchema# .
room:405 core:isLocationOf sensor:6bd134b6_339c_4168_9aeb_ae7d0f236851 .
observation:03f5ca58_aa70_47b3_980c_c8f486cac9ee_hmdt_id_1 a sosa:Observation ;
    sosa:hasSimpleResult "54.7"^^xsd:float ;
    sosa:observedProperty https://territoire.emse.fr/kg/emse/fayol/4ET/431H#humidity ;
    sosa:resultTime "2021-11-15T05:31:28"^^xsd:dateTime .
sensor:6bd134b6_339c_4168_9aeb_ae7d0f236851 a sosa:Sensor ;
    ssn:detects observation:6bd134b6_339c_4168_9aeb_ae7d0f236851_lumi_id_1000009,
        observation:6bd134b6_339c_4168_9aeb_ae7d0f236851_lumi_id_1000010,
*/