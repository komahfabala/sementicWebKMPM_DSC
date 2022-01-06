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
        
        salle.add(new String("400"));
        salle.add(new String("401D"));
        salle.add(new String("401F"));
        salle.add(new String("401H"));
        salle.add(new String("403"));
        salle.add(new String("404"));
        salle.add(new String("405"));
        salle.add(new String("406"));
        salle.add(new String("407"));
        salle.add(new String("408"));
        salle.add(new String("409"));
        salle.add(new String("410"));
        salle.add(new String("411"));
        salle.add(new String("412"));
        salle.add(new String("414"));
        salle.add(new String("416"));
        salle.add(new String("418"));
        salle.add(new String("420A"));
        salle.add(new String("420B"));
        salle.add(new String("421"));
        salle.add(new String("422"));
        salle.add(new String("423"));
        salle.add(new String("424"));
        salle.add(new String("425"));
        salle.add(new String("426"));
        salle.add(new String("428"));
        salle.add(new String("429"));
        salle.add(new String("430"));
        salle.add(new String("431F"));
        salle.add(new String("431H"));
        salle.add(new String("432"));
        salle.add(new String("434"));
    }

    public List<String> getSalle(){
        return salle;
    }

}
