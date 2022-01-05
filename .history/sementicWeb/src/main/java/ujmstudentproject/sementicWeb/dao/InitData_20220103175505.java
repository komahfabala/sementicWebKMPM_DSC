package ujmstudentproject.sementicweb.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class InitData {
    private static final List<String> salle = new ArrayList<String>();
    static{
        initData();
    }
    private static void initData(){
        
        salle.add( new String("e s223"));
        salle.add(new String("e s224"));
        salle.add(new String("e s225"));
        salle.add(new String("e s226"));
        salle.add(new String("e s227"));
    }

    public List<String> getSalle(){
        return salle;
    }

    public ArrayList<String> getMapSalles() {
       ArrayList<String> myListe = new ArrayList<String>();
        for (String r : myListe) {
            myListe.add(r);
        }
        return myListe;
    }
}
