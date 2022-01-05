package ujmstudentproject.sementicweb.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class InitData {
    private static final List<String> salle = new ArrayList<String>();
    static{
        initData();
    }
    private static void initData(){
        
        salle.add(new String("emse/fayol/e4/S424"));
        salle.add(new String("emse/fayol/e4/S405"));
        salle.add(new String("emse/fayol/e4/Hall4Nord"));
        salle.add(new String("emse/fayol/e4/S431F"));
        salle.add(new String("emse/fayol/e4/S425"));
        salle.add(new String("emse/fayol/e4/S431H"));
        salle.add(new String("emse/fayol/e4/S423"));
        salle.add(new String("emse/fayol/e4/S421"));
        salle.add(new String("emse/fayol/e4/S422"));
        salle.add(new String("emse/fayol/e4/S416"));
        salle.add(new String("emse/fayol/e4/S432"));
        salle.add(new String("emse/fayol/Mobile4"));
        salle.add(new String("emse/fayol/Mobile5"));
        salle.add(new String("emse/fayol/Mobile2"));
        salle.add(new String("emse/fayol/Mobile3"));
        salle.add(new String("emse/fayol/Mobile1"));
    }

    public List<String> getSalle(){
        return salle;
    }

}
