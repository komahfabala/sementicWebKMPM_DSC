<<<<<<< HEAD
package ujmstudentproject.sementicweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ujmstudentproject.sementicweb.services.*;



@Controller 
public class Accueil{
    @Autowired
    ServiceMeteo service;
    @RequestMapping("/")
    public String  scraping(){
        service.parserWebString();
        return " vas yyyy";
    }
    

}
