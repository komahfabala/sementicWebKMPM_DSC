package ujmstudentproject.sementicWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ujmstudentproject.sementicWeb.controllers.Accueil;

@SpringBootApplication
public class SementicWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SementicWebApplication.class, args);

		Accueil con = new Accueil();
		con.readCSV("sementicWeb/src/data/exemple.csv");
	}

}
