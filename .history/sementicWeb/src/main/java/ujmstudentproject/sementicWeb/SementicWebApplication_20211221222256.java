package ujmstudentproject.sementicweb;
import ujmstudentproject.sementicweb.services.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SementicWebApplication {

	public static void main(String[] args) {
		parserWebString();
		SpringApplication.run(SementicWebApplication.class, args);
	}

}
