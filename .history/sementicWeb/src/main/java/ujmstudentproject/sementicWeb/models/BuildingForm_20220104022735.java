package ujmstudentproject.sementicweb.models;


import lombok.Data;
/**
 *  cette classe c'est comme un  dto
 * il va juste recuperer le contenu du formulaire
 */
@Data
public class BuildingForm{
    
    private String nameBuilding;
    private String date_jj_MM_yyy;

}