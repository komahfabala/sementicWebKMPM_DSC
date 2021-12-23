@Data
public class Meteo{
    /**
     *  ce model est pour les données de la meteo
     */
    private String Day_date; // date de la prise de la temperature
    private long  maxTemperature;
    private long minTemperature;

    public Meteo(String dateTime, long maxTp, long minTp){
        this.Day_date = dateTime;
        this.maxTemperature = maxTp;
        this.minTemperature = minTp;
    }

    public String getDay_date() {
        return this.Day_date;
    }

    public void setDay_date(String Day_date) {
        this.Day_date = Day_date;
    }

    public long getMaxTemperature() {
        return this.maxTemperature;
    }

    public void setMaxTemperature(long maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public long getMinTemperature() {
        return this.minTemperature;
    }

    public void setMinTemperature(long minTemperature) {
        this.minTemperature = minTemperature;
    }

}