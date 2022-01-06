package ujmstudentproject.sementicweb.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping(path={"/listeQuery"})
    public String listeQuery(Model model){ 
        model.addAttribute("flowers", "je suis content");
        return "listequery.html";
    }

    
    @GetMapping("informationquery2")
    public String informationquery2(Model model){
        String[] flowers = new String[] { "Rose", "Lily", "Tulip", "Carnation", "Hyacinth" }; 
        model.addAttribute("flowers", flowers);
        Map<String,String> contacts = new HashMap<String,String>();
        contacts.put("110033", "tom");
        contacts.put("110055", "jerry");
        contacts.put("110077", "donald");
        contacts.put("110034", "tom");
        contacts.put("110056", "jerry");
        contacts.put("110078", "donald");
        model.addAttribute("contacts", contacts);
        model.addAttribute("data","comment");
        return "informationquery2.html";
    }

}