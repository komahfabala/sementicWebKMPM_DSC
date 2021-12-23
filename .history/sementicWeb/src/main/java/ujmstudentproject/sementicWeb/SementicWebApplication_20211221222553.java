package ujmstudentproject.sementicweb;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SementicWebApplication {
	public final static String url = "https://www.meteociel.fr/temps-reel/obs_villes.php?code2=7475&jour2=13&mois2=10&annee2=2021";
    
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
