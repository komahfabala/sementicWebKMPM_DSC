package ujmstudentproject.sementicweb.models;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Building  {
    
    private String nameBuilding;
    private int idBuiding;
    private int jour;
    private int mois;
    private int annee;
    private int date; 
    static List<String> liste_salle = new ArrayList<String>();
    
    static{
        liste_salle.add( new String("e s223"));
        liste_salle.add(new String("e s224"));
        liste_salle.add(new String("e s225"));
        liste_salle.add(new String("e s226"));
        liste_salle.add(new String("e s227"));
    }

}
