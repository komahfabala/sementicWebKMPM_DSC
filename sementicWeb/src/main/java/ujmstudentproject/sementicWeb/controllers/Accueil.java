package ujmstudentproject.sementicWeb.controllers;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdfconnection.RDFConnectionRemote;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ujmstudentproject.sementicWeb.Connection.Fuseki;

@RestController
@RequestMapping("/")
public class Accueil{
    @GetMapping
    public void createCon(){
        String datasetURL = "http://localhost:3030/station";
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

/*
   RDFConnection conn0 = RDFConnectionRemote.newBuilder()
        .destination(datasetURL)
        .queryEndpoint("station").acceptHeaderSelectQuery("application/sparql-results+json, application/sparql-results+xml;q=0.9").build();
        Query query = QueryFactory.create("Select ?subject Where{?subject ?predicate ?object}");
        try ( RDFConnection conn = conn0 ) {
            conn.queryResultSet(query, ResultSetFormatter::out);
        }
*/