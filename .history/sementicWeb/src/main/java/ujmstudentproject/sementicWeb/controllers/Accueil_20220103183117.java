package ujmstudentproject.sementicweb.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ujmstudentproject.sementicweb.dao.InitData;
import ujmstudentproject.sementicweb.models.Building;
import ujmstudentproject.sementicweb.services.*;



@Controller 
public class Accueil{
    @Autowired
    ServiceMeteo service;

    @Autowired
    InitData dao;

    @RequestMapping("/")
    public String  scraping(){
       service.parserWebString();
        return "index.html";
    }


    @GetMapping(value={"/selectBuildingRoom"})
    public String selectBuildingRoom(Model model){
        model.addAttribute("buildingForm", new Building());
        List<String> mybag = dao.getSalle();
        model.addAttribute("liste_salle", mybag);
        return "selectBuildingRoom.html";
    }

    @PostMapping("/selectBuildingRoom")
    public String submissionSelectBuildingRoom(@ModelAttribute("buildingForm") Building building){

        return "informationBuildingRoom.html";
    }

}