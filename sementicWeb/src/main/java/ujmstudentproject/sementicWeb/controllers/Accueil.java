package ujmstudentproject.sementicWeb.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
<<<<<<< HEAD
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
=======
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
>>>>>>> 18179d80336d998e441b08e821279206c9ab7cd8

import ujmstudentproject.sementicWeb.services.*;

<<<<<<< HEAD
import ujmstudentproject.sementicweb.dao.InitData;

import ujmstudentproject.sementicweb.models.BuildingForm;
import ujmstudentproject.sementicweb.services.*;
=======
>>>>>>> 18179d80336d998e441b08e821279206c9ab7cd8

@RestController
@RequestMapping("/")
public class Accueil{
    @Autowired
    ServiceMeteo service;
<<<<<<< HEAD

    @Autowired
    InitData dao;

    @RequestMapping("/")
=======
    @GetMapping("/home")
>>>>>>> 18179d80336d998e441b08e821279206c9ab7cd8
    public String  scraping(){
       service.parserWebString();
        return "index.html";
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