package ujmstudentproject.sementicWeb.controllers;

import org.apache.jena.rdf.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import ujmstudentproject.sementicWeb.services.*;


@RestController
@RequestMapping("/")
public class Accueil{
    @Autowired
    ServiceMeteo service;
    @GetMapping("/home")
    public String  scraping(){
       // service.parserWebString();
       Model model = service.rdfModel();
       service.loaderRDF(model);
        return "index.html";
    }
    

}