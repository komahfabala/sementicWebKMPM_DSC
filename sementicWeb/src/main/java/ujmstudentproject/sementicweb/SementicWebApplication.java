package ujmstudentproject.sementicweb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ujmstudentproject.sementicweb.services.ServiceFuseki;
import ujmstudentproject.sementicweb.services.ServiceQuery;
import ujmstudentproject.sementicweb.services.serviceimp.ServiceFusekiImpl;
import ujmstudentproject.sementicweb.services.serviceimp.ServiceQueryImp;




@SpringBootApplication
public class SementicWebApplication {
		public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(SementicWebApplication.class, args);
		ServiceFuseki server = new ServiceFusekiImpl();
		ServiceQuery query = new ServiceQueryImp();
		//var data = query.queryTempAllBuilding();
		//query.queryTempBuilding("2021-11-15");
		//server	.readCSV_From_Meteo("src/main/java/ujmstudentproject/data/meteo.csv");
		//server.readCSV_From_Territoire("src/main/java/ujmstudentproject/data/20211116-daily-sensor-measures.csv");
		//server.loadTTL("src/main/java/ujmstudentproject/data/output-building-model.ttl");
		//server.loadTTL("src/main/java/ujmstudentproject/data/meteo-output-model.ttl");

		//System.out.println("Every thing done!" + data.get("time").get(1));

		}
}
/*
ex:observation_LUMI_422_71
        ex:date      "2021-11-15"^^xsd:date ;
        ex:detector  "LUMI" ;
        ex:hasValue  8.0 ;
        ex:time      "05:31:35"^^xsd:time ;
        territoire:  "422" ;
        seas:        "luminosity" .
*/