package ujmstudentproject.sementicweb.models;
import java.util.Date;
/**
 *  cette classe c'est comme un  dto
 * il va juste recuperer le contenu du formulaire
 */
public class BuildingForm{
    
    private String nameBuilding;
    private Date date_jj_MM_yyy;

    public BuildingForm(String nameBuilding, Date d_date ) {
        this.nameBuilding = nameBuilding;
        this.date_jj_MM_yyy= d_date;
    }

    public String getNameBuilding() {
        return this.nameBuilding;
    }

    public void setNameBuilding(String nameBuilding) {
        this.nameBuilding = nameBuilding;
    }

    public Date getDate_jj_MM_yyy() {
        return this.date_jj_MM_yyy;
    }

    public void setDate_jj_MM_yyy(Date date_jj_MM_yyy) {
        this.date_jj_MM_yyy = date_jj_MM_yyy;
    }

}