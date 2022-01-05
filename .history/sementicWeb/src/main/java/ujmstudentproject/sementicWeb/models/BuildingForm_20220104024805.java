package ujmstudentproject.sementicweb.models;


import java.util.Date;

import lombok.Data;
/**
 *  cette classe c'est comme un  dto
 * il va juste recuperer le contenu du formulaire
 */
@Data
public class BuildingForm{
    
    private String nameBuilding;
    private Date idate;

    public String toString(){
        return nameBuilding + idate;
    }
}