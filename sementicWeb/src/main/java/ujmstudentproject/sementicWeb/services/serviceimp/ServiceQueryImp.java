package ujmstudentproject.sementicWeb.services.serviceimp;

import ujmstudentproject.sementicWeb.services.ServiceQuery;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.vocabulary.XSD;

public class ServiceQueryImp implements ServiceQuery {

    @Override
    public void queryTempBuilding(String dateQ, String timeQ, String room) {
        String datasetURL = "http://localhost:3030/territoire";
        String sparqlEndpoint = datasetURL + "/sparql";
        System.out.println(dateQ);
        //String sparqlUpdate = datasetURL + "/update";
        //String graphStore = datasetURL + "/data";
        try(
            RDFConnection conneg = RDFConnection.connect(sparqlEndpoint)){
            System.out.println("Query start!!");
            String qq = "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"+
            "PREFIX shma: <http://schema.org/>\n"+
            "PREFIX sosa: <http://www.w3.org/ns/sosa/>\n"+
            "PREFIX territoire: <https://territoire.emse.fr/kg/emse/fayol/>\n"+
            "Select Distinct (Count(?hasValue) as ?count) (AVG(?hasValue) as ?mean) {\n"+
                "?subject shma:date '"+dateQ+"'^^xsd:date;\n"+
                "sosa:detector 'TEMP';\n"+
                "sosa:hasValue ?hasValue;\n"+
                "territoire:room ?room\n"+
                "FILTER regex(str(?room), '"+room+"','i').\n"+

                "}\n group by (?room)";
            QueryExecution q = conneg.query(qq);
            
            ResultSet rs = q.execSelect();
            System.out.println(rs.getRowNumber());
            QuerySolution qs = rs.next() ;
            var res = qs.getLiteral("count");
            var resT = qs.getLiteral("mean");
            Double temp = Double.parseDouble(resT.toString().replace("^^http://www.w3.org/2001/XMLSchema#decimal", ""));
            System.out.println(res+" | "+Math.round(temp)) ;
           
            q.close() ;
            conneg.close() ;
        };
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