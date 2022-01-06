package ujmstudentproject.sementicweb.services.serviceimp;

import java.util.Date;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.sparql.sse.builders.BuilderOp.Build;
import org.apache.jena.vocabulary.XSD;

import ujmstudentproject.sementicweb.models.Building;
import ujmstudentproject.sementicweb.services.ServiceQuery;

public class ServiceQueryImp implements ServiceQuery {

    @Override
    public Building queryTempBuilding(String dateQ, String timeQ, String room) {
        Building newBuild = new Building();
        String datasetURL = "http://localhost:3030/territoire";
        String sparqlEndpoint = datasetURL + "/sparql";
        System.out.println(dateQ);
        //String sparqlUpdate = datasetURL + "/update";
        //String graphStore = datasetURL + "/data";

        /**
         *
         shma:observation_TEMP_421_7
        shma:date        "2021-11-15"^^xsd:date ;
        shma:time        "05:31:29"^^xsd:time ;
        territoire:room  "421"^^xsd:Name ;
        sosa:detector    "TEMP" ;
        sosa:hasValue    23.12 ;
        sosa:seas        "temperature" .
         */
        try(
            RDFConnection conneg = RDFConnection.connect(sparqlEndpoint)){
            System.out.println("Query start!!");
            String qq = "PREFIX e4:         <https://territoire.emse.fr/kg/emse/fayol/4ET/>\n"+
            "PREFIX ex:         <http://exemple.org/> \n"+
            "PREFIX rdfs:       <http://www.w3.org/2000/01/rdf-schema#> \n"+
            "PREFIX seas:       <https://w3id.org/seas/> \n"+
            "PREFIX territoire: <https://territoire.emse.fr/kg/emse/fayol/4ET/> \n"+
            "PREFIX xsd:        <http://www.w3.org/2001/XMLSchema#> \n"+
            "SELECT Distinct ?hasvalue  {\n"+
            "?s ex:date '"+dateQ+"'^^xsd:date;\n"+
                        "ex:time ?time;\n"+
                        "ex:hasValue ?hasvalue;\n"+
                         "e4:          '"+room+"' ;\n"+
                         "seas:'temperature'.\n}";
            QueryExecution q = conneg.query(qq);
            
            ResultSet rs = q.execSelect();
            QuerySolution qs = rs.next() ;
            var res = qs.getLiteral("hasvalue");
            Double temp = Double.parseDouble(res.toString().replace("^^http://www.w3.org/2001/XMLSchema#decimal", ""));
            System.out.println(+Math.round(temp)) ;
          /*  newBuild.getMaxTemp((float) temp)
            newBuild.setDate(new Date(dateQ));
            newBuild.setNameBuilding(room);*/
            q.close() ;
            conneg.close() ;
        };

        return newBuild;
    }

}

/*"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"+
            "PREFIX shma: <http://schema.org/>\n"+
            "PREFIX sosa: <http://www.w3.org/ns/sosa/>\n"+
            "PREFIX territoire: <https://territoire.emse.fr/kg/emse/fayol/>\n"+
            "Select Distinct ?subject ?hasValue ?time where{\n"+
                "?subject shma:date '"+dateQ+"'^^xsd:date;\n"+
                "shma:time ?time;\n"+
                "sosa:detector 'TEMP';\n"+
                "sosa:hasValue ?hasValue;\n"+
                "territoire:room '"+room+"'^^xsd:Name.\n"+
                "FILTER regex(str(?time), '^"+timeQ+"','i').\n"+
                "}LIMIT 10"  */



                /**
                 * 
                 *  "Select Distinct (Count(?hasValue) as ?count) (AVG(?hasValue) as ?mean) {\n"+
                "?subject shma:date '"+dateQ+"'^^xsd:date;\n"+
                "sosa:detector 'TEMP';\n"+
                "sosa:hasValue ?hasValue;\n"+
                "territoire:room ?room\n"+
                "FILTER regex(str(?room), '"+room+"','i').\n"+

                "}\n group by (?room)";
                 */