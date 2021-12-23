package ujmstudentproject.scrapingweb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ScrapingWeb {
    public final String url = "https://www.meteociel.fr/temps-reel/obs_villes.php?code2=7475&jour2=13&mois2=10&annee2=2021";
    try {
        final Document doc = Jsoup.connect(url).get();
    } catch (Exception e) {
       e.printStackTrace();
    }
}
