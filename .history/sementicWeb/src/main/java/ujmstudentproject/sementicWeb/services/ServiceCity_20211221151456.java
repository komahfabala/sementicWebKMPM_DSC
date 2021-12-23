import ujmstudentproject.sementicweb.models.Meteo;
public interface  ServiceCity {
    /**
     * 
     * @param meteo
     * @param city
     * @return un object rdf 
     */
    public org.apache.jena.rdf.model.Model CityToRDF(Meteo meteo, String city);
}