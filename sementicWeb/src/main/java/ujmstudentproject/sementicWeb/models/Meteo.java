package ujmstudentproject.sementicweb.models;
import lombok.Data;

@Data
public class Meteo{
    /**
     *  ce model est pour les données de la meteo
     */
    private String Day_date; // date de la prise de la temperature
    private long  maxTemperature;
    private long minTemperature;
}