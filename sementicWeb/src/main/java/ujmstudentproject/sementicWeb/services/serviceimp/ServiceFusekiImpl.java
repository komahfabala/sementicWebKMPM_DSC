
@Service
public class ServiceFusekiImp implements ServiceFuseki{
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
    public void readCSV(String filePath){
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
        String serviceURL = "http://localhost:3030/station";
        try(
                RDFConnection conneg = RDFConnection.connect(serviceURL)){

            conneg.put("sementicWeb/src/main/java/ujmstudentproject/data/output-model.ttl");
            QueryExecution q = conneg.query("Select * Where{?subject ?predicate ?object} LIMIT 10");
            ResultSet rs = q.execSelect();
            while(rs.hasNext()) {
                QuerySolution qs = rs.next() ;
                Resource subject = qs.getResource("predicate") ;
                var s = qs.getLiteral("object");
                //System.out.println("Subject: "+subject.getNameSpace()) ;
                System.out.println("Subject: "+s) ;
            }
            q.close() ;
            conneg.close() ;
        };
    }
    public void createCon(){
        String datasetURL = "http://localhost:3030/station";
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
}