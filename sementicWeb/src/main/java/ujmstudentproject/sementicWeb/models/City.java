package ujmstudentproject.sementicWeb.models;
import lombok.Data;

@Data
public class City{
    private String nameCity;
    private double latitude;
    private double longitude;
    
    public City(String nameCity, double latitude, double longitude){
        this.nameCity = nameCity;
        this.latitude = latitude;
        this.longitude = longitude;   
    }
}