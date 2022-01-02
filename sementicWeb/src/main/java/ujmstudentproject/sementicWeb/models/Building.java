package ujmstudentproject.sementicWeb.models;
import java.util.*;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Building  {
    
 String nameBuiding;
 int idBuiding;
 Double temp;
 Date date_tm;
}
