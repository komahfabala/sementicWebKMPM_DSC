package ujmstudentproject.sementicweb.models;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
/**
 *  cette classe c'est comme un  dto
 * il va juste recuperer le contenu du formulaire
 */
@Data
public class BuildingForm{
    
    private String nameBuilding;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date idate;

    
}