package ujmstudentproject.sementicWeb.Connection;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Fuseki {
    private String datasetURL ;
    private String sparqlEndpoint;
    private String sparqlUpdate;
    private String graphStore;
/*
String datasetURL = "http://localhost:3030/station";
String sparqlEndpoint = datasetURL + "/sparql";
String sparqlUpdate = datasetURL + "/update";
String graphStore = datasetURL + "/data";
*/
}
