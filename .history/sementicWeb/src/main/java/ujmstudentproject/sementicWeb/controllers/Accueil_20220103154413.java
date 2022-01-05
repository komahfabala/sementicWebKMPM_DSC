package ujmstudentproject.sementicweb.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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


    @GetMapping(value={"/selectBuildingRoom"})
    public String selectBuildingRoom(Model model){
        model.addAttribute("buildingForm", new Building());
        return "selectBuildingRoom.html";
    }

    @PostMapping("/informationBuilding")
    public String submissionSelectBuildingRoom(@ModelAttribute("buildingForm") Building building){

        return "informationBuildingRoom.html";
    }

}