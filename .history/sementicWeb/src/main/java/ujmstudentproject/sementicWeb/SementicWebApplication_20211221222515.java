package ujmstudentproject.sementicweb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SementicWebApplication {

	public static void main(String[] args) {
		try {
            final Document doc = Jsoup.connect(url).get();
            System.out.println(doc.outerHtml());
        } catch (Exception e) {
           e.printStackTrace();
        }
		SpringApplication.run(SementicWebApplication.class, args);
	}

}
