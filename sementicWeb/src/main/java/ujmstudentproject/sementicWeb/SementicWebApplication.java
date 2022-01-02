package ujmstudentproject.sementicWeb;
import java.io.*;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ujmstudentproject.sementicWeb.services.ServiceFuseki;
import ujmstudentproject.sementicWeb.services.ServiceQuery;
import ujmstudentproject.sementicWeb.services.serviceimp.ServiceFusekiImpl;
import ujmstudentproject.sementicWeb.services.serviceimp.ServiceQueryImp;

@SpringBootApplication
public class SementicWebApplication {
		public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(SementicWebApplication.class, args);
		ServiceFuseki server = new ServiceFusekiImpl();
		ServiceQuery query = new ServiceQueryImp();

		query.queryTempBuilding("2021-11-15", "15","e4_S421");
		//server.readCSV_From_Territoire("src/main/java/ujmstudentproject/data/20211116-daily-sensor-measures.csv");
		//server.loadTTL("src/main/java/ujmstudentproject/data/output-building-model.ttl");

		System.out.println("Every thing done!");

	}
}


/*
String src = "src/main/java/ujmstudentproject";
String data =src+"/data";
Process p;
String s;
System.out.println("Try");

		File file = new File(src+"/data/kg/ontology.ttl");
		if(!file.exists()){
			try {
				p = Runtime.getRuntime().exec("wget -P "+src+"/data" +" -r https://territoire.emse.fr/kg/");
				p.waitFor();
				System.out.println ("exit: " + p.exitValue());
				p.destroy();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(file.getAbsolutePath());
		}
	}

*/