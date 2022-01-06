package ujmstudentproject.sementicweb.models;


import java.util.Date;

import lombok.Data;

@Data
public class Building  {
    
    private String nameBuilding;
    private int idBuiding;
    private float minTemp;
    private float maxTemp;
    private Date date;

}