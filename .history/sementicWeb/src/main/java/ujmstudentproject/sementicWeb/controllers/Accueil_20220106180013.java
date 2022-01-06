package ujmstudentproject.sementicweb.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ujmstudentproject.sementicweb.dao.InitData;
import ujmstudentproject.sementicweb.models.BuildingForm;
import ujmstudentproject.sementicweb.services.ServiceMeteo;




@Controller
public class Accueil{
    @Autowired
    ServiceMeteo service;

    @Autowired
    InitData dao;

    @RequestMapping("/")
    public String  scraping(){
       //service.parserWebString();
        return "index.html";
    }


    @GetMapping(path={"/listeQuery"})
    public String listeQuery(Model model){
        String[] flowers = new String[] { "Rose", "Lily", "Tulip", "Carnation", "Hyacinth" }; 
        model.addAttribute("flowers", flowers);
        return "listequery.html";
    }


    @GetMapping(path={"/selectBuildingRoom"})
    public String selectBuildingRoom(Model model){
        model.addAttribute("buildingForm", new BuildingForm());
        List<String> listeSalles = dao.getSalle();
        model.addAttribute("listeSalles",listeSalles);
        return "selectBuildingRoom.html";
    }

    // requestmapping method for submit building formulaire
    @PostMapping(path="/selectBuildingRoom")
    public String submissionSelectBuildingRoom(@ModelAttribute 
                    BuildingForm buildingForm, Model model){
            model.addAttribute("data", buildingForm);
        return "informationBuildingRoom.html";
    }

}