package ujmstudentproject.sementicweb.controllers;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ujmstudentproject.sementicweb.dao.BuildDao;
import ujmstudentproject.sementicweb.dao.InitData;
import ujmstudentproject.sementicweb.models.Building;
import ujmstudentproject.sementicweb.models.BuildingForm;
import ujmstudentproject.sementicweb.services.ServiceMeteo;

import java.util.ArrayList;

import ujmstudentproject.sementicweb.services.ServiceQuery;
import ujmstudentproject.sementicweb.services.serviceimp.ServiceQueryImp;

@Controller
public class Accueil{
    @Autowired
    ServiceMeteo service;

    @Autowired
    InitData dao;

    @Autowired
    BuildDao bDao;
    
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
        return "listequery.html";
    }

    
    @GetMapping("informationquery2")
    public String informationquery2(Model model){
        ServiceQuery query = new ServiceQueryImp();
        /*
            ici tu remplaces cette table par le resulat de ton query 
            si tu change son nom il faudra changer aussi sur la page information query
        */
        Map<String, ArrayList<String>> data = new HashMap<String, ArrayList<String>>();
        data = query.queryTempAllBuilding();

        model.addAttribute("room", data.get("room"));
        model.addAttribute("dateL", data.get("date"));
        model.addAttribute("allData", data);
        return "informationquery2.html";
    }

}