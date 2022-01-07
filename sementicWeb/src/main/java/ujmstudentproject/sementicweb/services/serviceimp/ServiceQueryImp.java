package ujmstudentproject.sementicweb.services.serviceimp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.jsonldjava.core.RDFDataset.Literal;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.sparql.function.library.date;
import org.apache.jena.sparql.sse.builders.BuilderOp.Build;
import org.apache.jena.vocabulary.XSD;
import org.springframework.beans.factory.annotation.Autowired;

import ujmstudentproject.sementicweb.models.Building;
import ujmstudentproject.sementicweb.services.ServiceQuery;

public class ServiceQueryImp implements ServiceQuery {
    @Autowired
    Building build;

    String datasetURL = "http://localhost:3030/territoire";
    String sparqlEndpoint = datasetURL + "/sparql";

    Map<String, ArrayList<String>> buildValueMap = new HashMap<String, ArrayList<String>>();

    String prefix = "PREFIX sosa: <http://www.w3.org/ns/sosa/>\n" +
            "PREFIX ex:         <http://exemple.org/> \n" +
            "PREFIX rdfs:       <http://www.w3.org/2000/01/rdf-schema#> \n" +
            "PREFIX ssn: <http://www.w3.org/ns/ssn/> \n" +
            "PREFIX territoire: <https://territoire.emse.fr/kg/emse/fayol/4ET/> \n" +
            "PREFIX xsd:        <http://www.w3.org/2001/XMLSchema#> \n" +
            "PREFIX meteo:      <https://www.meteociel.fr/temps-reel/obs_villes.php/> ";

    @Override
    public Building queryTempBuilding(String dateQ) {
        try (
                RDFConnection conneg = RDFConnection.connect(sparqlEndpoint)) {
            System.out.println("Query start!!");
            String qq = prefix +
                    "SELECT Distinct ?hasvalue1 ?hasvalue2 ?time2 ?time1 ?room  {\n" +
                    "?subject meteo:date  '" + dateQ + "'^^xsd:date;\n" +
                    "meteo:time ?time2;\n" +
                    "meteo:temp  ?hasvalue1.\n" +

                    "?territoire ex:date '" + dateQ + "'^^xsd:date;\n" +
                    "ex:time ?time;\n" +
                    "ex:hasValue ?hasvalue2;\n" +
                    "e4:          ?room ;\n" +
                    "seas:'temperature'.\n" +
                    // "FILTER regex(str(?time1),'^'+?time2,'i')"+
                    "}\n" + "Order by(?room)";

            QueryExecution q = conneg.query(qq);

            ResultSet rs = q.execSelect();
            QuerySolution qs = rs.next();
            System.out.println("Query passe!!");
            // var temp1 = qs.getLiteral("hasvalue1");
            var temp2 = qs.getLiteral("hasvalue2");
            var room = qs.getLiteral("room");
            // Double t1 =
            // Double.parseDouble(temp1.toString().replace("^^http://www.w3.org/2001/XMLSchema#decimal",
            // ""));
            Double t2 = Double.parseDouble(temp2.toString().replace("^^http://www.w3.org/2001/XMLSchema#decimal", ""));
            /*
             * System.out.println(room + "<==>\nRoom temp"+Math.round(t1)+
             * "\nOutSide temp: "+Math.round(t2)) ;
             * newBuild.getMaxTemp((float) temp)
             * newBuild.setDate(new Date(dateQ));
             * newBuild.setNameBuilding(room);
             */
            q.close();
            conneg.close();
        }
        ;

        return build;
    }

    int i;

    @Override
    public Map<String, ArrayList<String>> queryTempAllBuilding() {
        // Creation d'un dictionnaire avec des keys Hour et Degre
        buildValueMap.put("time", new ArrayList<String>());
        buildValueMap.put("room", new ArrayList<String>());
        buildValueMap.put("location", new ArrayList<String>());
        buildValueMap.put("detector", new ArrayList<String>());
        buildValueMap.put("sensor", new ArrayList<String>());
        buildValueMap.put("value", new ArrayList<String>());
        buildValueMap.put("date", new ArrayList<String>());
        try (
                RDFConnection conneg = RDFConnection.connect(sparqlEndpoint)) {
            System.out.println("Query start!!");
            String qq = prefix +
                    "SELECT Distinct ?date ?hasvalue ?time ?room ?location ?detector ?sensor WHERE {\n" +
                    "?subject ex:date  ?date ;\n" +
                    "sosa:hasResult ?hasvalue ;\n" +
                    "sosa:isHostedBy ?location;\n" +
                    "sosa:resultTime ?time;\n" +
                    "sosa:Sensor ?sensor\n ;"+
                    "territoire: ?room;\n" +
                    "ssn:detect ?detector\n}\n" +
                    "Order by(?room)";

            QueryExecution q = conneg.query(qq);
            System.out.println("Query passe!!");
            ResultSet rs = q.execSelect();
            List<String> value = List.of();
            while (rs.hasNext()) {
                i++;

                QuerySolution qs = rs.next();
                var date = qs.getLiteral("date");
                var time = qs.getLiteral("time");
                var room = qs.getLiteral("room");
                var detector = qs.getLiteral("detector");
                var sensor = qs.getLiteral("sensor");
                var location = qs.getLiteral("location");
                var temp_val = qs.getLiteral("hasvalue");
                Double temp = Double
                        .parseDouble(temp_val.toString().replace("^^http://www.w3.org/2001/XMLSchema#decimal", ""));

                if (i > 1) {
                    if (value.contains(temp.toString()) && value.contains(room.toString())
                            && value.contains(detector.toString())) {
                        continue;
                    }
                }
                value = List.of(temp.toString(), room.toString(), detector.toString());

                buildValueMap.get("time").add(time.toString());
                buildValueMap.get("room").add(room.toString());
                buildValueMap.get("location").add(location.toString());
                buildValueMap.get("detector").add(detector.toString());
                buildValueMap.get("sensor").add(sensor.toString());
                buildValueMap.get("value").add(temp.toString());
                buildValueMap.get("date").add(date.toString().replace("^^http://www.w3.org/2001/XMLSchema#date", ""));
                System.out.println(date.toString().replace("^^http://www.w3.org/2001/XMLSchema#date", "") + " " + time
                        + "\n" + room + "\nRoom temp =" + Math.round(temp) + "\nLocation : "
                        + location + "\nDetector : " + detector + "\n****************8dz9d4f6z54f98erf465e4c65e===    "
                        + i);
            }
            q.close();
            conneg.close();
        };
        System.out.println(buildValueMap.get("date").size());
        return buildValueMap;
    }

}

/*
 * "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"+
 * "PREFIX shma: <http://schema.org/>\n"+
 * "PREFIX sosa: <http://www.w3.org/ns/sosa/>\n"+
 * "PREFIX territoire: <https://territoire.emse.fr/kg/emse/fayol/>\n"+
 * "Select Distinct ?subject ?hasValue ?time where{\n"+
 * "?subject shma:date '"+dateQ+"'^^xsd:date;\n"+
 * "shma:time ?time;\n"+
 * "sosa:detector 'TEMP';\n"+
 * "sosa:hasValue ?hasValue;\n"+
 * "territoire:room '"+room+"'^^xsd:Name.\n"+
 * "FILTER regex(str(?time), '^"+timeQ+"','i').\n"+
 * "}LIMIT 10"
 */

/**
 * 
 * "Select Distinct (Count(?hasValue) as ?count) (AVG(?hasValue) as ?mean) {\n"+
 * "?subject shma:date '"+dateQ+"'^^xsd:date;\n"+
 * "sosa:detector 'TEMP';\n"+
 * "sosa:hasValue ?hasValue;\n"+
 * "territoire:room ?room\n"+
 * "FILTER regex(str(?room), '"+room+"','i').\n"+
 * 
 * "}\n group by (?room)";
 */