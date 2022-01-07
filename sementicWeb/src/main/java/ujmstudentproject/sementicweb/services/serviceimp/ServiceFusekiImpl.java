package ujmstudentproject.sementicweb.services.serviceimp;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.codec.Charsets;
import org.apache.jena.datatypes.xsd.XSDDatatype;

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
        Property timeProperty = model.createProperty(temp_uri + "time");
        Property tempProperty = model.createProperty(temp_uri + "temp");
        Property dateProperty = model.createProperty(temp_uri + "date");

        for(int i=0;i<tempValueMap.get("time").size();i++){
            String time = tempValueMap.get("time").get(i).replace(" h", "").replace("\"", "");
            String temp = tempValueMap.get("temp").get(i).replace(" °C", "").replace("\"", "");
            String date = tempValueMap.get("date").get(i).replace("\"", "");
            String[] sp = date.split("-");
            date = sp[2]+"-"+sp[1]+"-"+sp[0];
            System.out.println(date);
            model.createResource(temp_uri+"observation_time_"+time)
                    .addProperty(codeProperty,code)
                    .addProperty(dateProperty,date, XSDDatatype.XSDdate)
                    .addProperty(timeProperty,time)
                    .addProperty(tempProperty,temp,XSDDatatype.XSDdecimal);
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
            // 0=="time",1=="temp",2=="date"

            // Creation d'un dictionnaire avec des keys Hour et Degre
            tempValueMap.put("time", new ArrayList<String>());
            tempValueMap.put("temp", new ArrayList<String>());
            tempValueMap.put("date", new ArrayList<String>());
            String[] arrayStrings;
            while((line = bReader.readLine()) !=null){

                arrayStrings = line.split(sep);
                tempValueMap.get("time").add(arrayStrings[0]);
                tempValueMap.get("temp").add(arrayStrings[1]);
                tempValueMap.get("date").add(arrayStrings[3]);
  }

            models = weather(tempValueMap,"7475");
            File outTTL = new File("src/main/java/ujmstudentproject/data/meteo-output-model.ttl");
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
                    if(filepath.contains(".ttl")){
                        conneg.load(filepath);
                        System.out.println("Everything done!!");
                    }
                    else if(filepath.contains(".txt")){
                        
                String line = "";
                    File ttl = new File(filepath);
                    try (FileReader fr = new FileReader(ttl, Charsets.UTF_8)) {
                        BufferedReader bReader = new BufferedReader(fr); // Lecture dans le fichier
                        while((line = bReader.readLine()) !=null){
                           System.out.println(line);
                        conneg.load(line); 
            
                        }
                        
                        
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
       
            conneg.commit();
            conneg.close() ;
        };
    }
   
    @Override
    public void readCSV_From_Territoire(String filePath) {
        String sep = ",";
        Model models = ModelFactory.createDefaultModel();
        Map<String, ArrayList<String>> tempValueMap = new HashMap<String, ArrayList<String>>();
        int i = 0;
        try{
            i++;
            File csvFile = new File(filePath);
            FileReader fr = new FileReader(csvFile, Charsets.UTF_8);
            BufferedReader bReader = new BufferedReader(fr); // Lecture dans le fichier
            String line = "";
            bReader.readLine(); // Remove header
            //System.out.println(bReader.readLine().split(",")[0] +" ");
            // name,time,HMDT,LUMI,SND,SNDF,SNDM,TEMP,id,location,type

            // Creation d'un dictionnaire avec des keys 
            tempValueMap.put("time", new ArrayList<String>());
            tempValueMap.put("HMDT", new ArrayList<String>());
            tempValueMap.put("LUMI", new ArrayList<String>());
            tempValueMap.put("SND", new ArrayList<String>());
            tempValueMap.put("SNDF", new ArrayList<String>());
            tempValueMap.put("SNDM", new ArrayList<String>());
            tempValueMap.put("TEMP", new ArrayList<String>());
            tempValueMap.put("ID", new ArrayList<String>());
            tempValueMap.put("Location", new ArrayList<String>());
            
            List<String> location = new ArrayList<String>();
            String[] arrayStrings;
            
            while((line = bReader.readLine()) !=null){
                i++;
                /*if (i==101){
                    break;
                }*/
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


                if(!location.contains(arrayStrings[9])){
                    System.out.println(arrayStrings[9]);
                    location.add(arrayStrings[9]);
                }
            }
        
        
            models = building(tempValueMap);
            File outTTL = new File("src/main/java/ujmstudentproject/data/output-building-model.ttl");
            FileWriter out= new FileWriter(outTTL, Charsets.UTF_8);
            models.write(out, "TURTLE");
            out.close();
            //models.write(System.out, "TURTLE");
            bReader.close();

        }catch(IOException ex){
            ex.printStackTrace();
        }
        
    }
    private Model building(Map<String, ArrayList<String>> tempValueMap) {
        System.out.println("\n*******************************************************\n");
        System.out.println("\n************Creation du model de builduing*************\n");
        System.out.println("\n*******************************************************\n");

        Model model = ModelFactory.createDefaultModel();
        String building_uri4ET = "https://territoire.emse.fr/kg/emse/fayol/4ET/";
        String seas_uri = "https://w3id.org/seas/";
        String ex = "http://exemple.org/";
        String sosa = "http://www.w3.org/ns/sosa/";
        String ssn = "http://www.w3.org/ns/ssn/";

        model.setNsPrefix("territoire", building_uri4ET)
                .setNsPrefix("rdfs", RDFS.getURI())
                .setNsPrefix("xsd", XSD.getURI())
                .setNsPrefix("seas", seas_uri)
                .setNsPrefix("ex", ex).setNsPrefix("ssn", ssn).setNsPrefix("sosa", sosa);

// name,time,HMDT,LUMI,SND,SNDF,SNDM,TEMP,id,location,type
        Property obsProperty = model.createProperty(sosa + "Observation");
        Property ssnPropertyDetection = model.createProperty(ssn + "detect");
        Property seas= model.createProperty(sosa + "Sensor");
        Property sosaResult = model.createProperty(sosa + "hasResult");
        Property buildingSensorId = model.createProperty(ex + "hasSensorId");
        Property buildingPropertyName = model.createProperty(ex + "hasName");
        Property buildingPropertyLocation = model.createProperty(sosa + "isHostedBy");
        Property locationProperty = model.createProperty(building_uri4ET);
     
        Property dateProperty = model.createProperty(ex + "date");
        Property timeProperty = model.createProperty(sosa + "resultTime");

        List<String> sensors = new ArrayList<String>(Arrays.asList("HMDT","LUMI","SND","SNDF","SNDM","TEMP"));
        List<String> sensorsType = new ArrayList<String>(Arrays.asList("humidity","luminosity","atmosphericPressure","atmosphericPressure","atmosphericPressure","temperature"));
        List<String> room_number = new ArrayList<String>(Arrays.asList("400","401D","401F","401H","403","404","405","406","407","408","409","410","411","412","414","416","418","420A","420B","421","422","423","424","425","426","428","429","430","431F","431H","432","434"));
        
        List<String> id_record = new ArrayList<String>();
        for(int i=0;i<tempValueMap.get("ID").size();i++){
            int ind = 0;
            String id = tempValueMap.get("ID").get(i);
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
            Double detectors = Double.parseDouble(detector);
            
            // Triplet de salle plus id
            if(!id_record.contains(id)){
                id_record.add(id);
              Resource dd = model.createResource(building_uri4ET+room.replace("e4_S","")).addProperty(buildingPropertyName, room);  
              model.add(dd, buildingSensorId, id);
            }

            if (room_number.contains(room.replace("e4_S",""))){
                room = room.replace("e4_S","");
            System.out.println(dateTime + "_"+tim+" "+ room.replace("e4_S","") + " "+detectors +"==>"+ i);

            }
            model.createResource(obsProperty+"_"+sensors.get(ind)+"_"+room.replace("e4_S","")+"_"+i)
            .addProperty(locationProperty, room.replace("e4_S",""))
            .addProperty(buildingPropertyLocation, tempValueMap.get("Location").get(i))
            .addProperty(ssnPropertyDetection,sensors.get(ind), XSDDatatype.XSDstring)
            .addProperty(seas, sensorsType.get(ind))
            .addProperty(sosaResult ,detectors.toString(),XSDDatatype.XSDdecimal)
            .addProperty(dateProperty,dateTime,XSDDatatype.XSDdate)
            .addProperty(timeProperty,tim);

            if(i==1038566){
                break;
            }
        }
        return model;
    }
}

