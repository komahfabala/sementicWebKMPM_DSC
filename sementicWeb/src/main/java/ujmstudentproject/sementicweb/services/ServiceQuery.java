package ujmstudentproject.sementicweb.services;

import org.springframework.stereotype.Service;

@Service
public interface ServiceQuery {
    public void queryTempBuilding(String dateQ, String timeQ, String room);
}
