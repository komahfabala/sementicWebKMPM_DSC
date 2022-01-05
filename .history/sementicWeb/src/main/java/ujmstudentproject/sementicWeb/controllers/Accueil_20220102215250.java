package ujmstudentproject.sementicweb.controllers;

import org.apache.jena.rdf.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


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
    

}