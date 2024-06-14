package controller;

import model.SerieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.SerieService;

@RestController
@RequestMapping("/series")
public class SerieController {
    @Autowired
    private SerieService service;
    @GetMapping
    public String hello(){
        return "Hello World";
    }

//    @GetMapping("/frases")
//    public SerieDTO exibeFrase(){
//        return service.exibeFrase();
//    }

}
