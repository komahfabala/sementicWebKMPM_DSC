package ujmstudentproject.sementicWeb;
import java.io.*;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ujmstudentproject.sementicWeb.services.ServiceFuseki;
import ujmstudentproject.sementicWeb.services.serviceimp.ServiceFusekiImpl;

@SpringBootApplication
public class SementicWebApplication {
		public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(SementicWebApplication.class, args);
		ServiceFuseki server = new ServiceFusekiImpl();
		//server.loadTTL("src/main/java/ujmstudentproject/data/file_ttl.txt");
		server.readCSV_From_Territoire("src/main/java/ujmstudentproject/data/20211116-daily-sensor-measures.csv");
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