package ujmstudentproject.sementicweb.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ujmstudentproject.sementicweb.models.Building;
import ujmstudentproject.sementicweb.services.*;



@Controller 
public class Accueil{
    @Autowired
    ServiceMeteo service;

    @RequestMapping("/")
    public String  scraping(){
       service.parserWebString();
        return "index.html";
    }


    @RequestMapping(value={"/selectBuildingRoom"}, method= RequestMethod.GET)
    public String selectBuildingRoom(@ModelAttribute("buildingForm") Building building){

        return "selectBuildingRoom.html";
    }

}