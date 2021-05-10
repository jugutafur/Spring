package com.store.Web.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludar")
public class HolaController {

    @GetMapping("/Ramirez")
    public String saludar(){
        return "hola Juan Guillermo Tafur";
    }



}
