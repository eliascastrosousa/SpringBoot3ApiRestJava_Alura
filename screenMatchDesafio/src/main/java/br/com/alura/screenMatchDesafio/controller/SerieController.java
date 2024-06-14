package br.com.alura.screenMatchDesafio.controller;

import br.com.alura.screenMatchDesafio.model.SerieDTO;
import br.com.alura.screenMatchDesafio.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("series")
public class SerieController {
    @Autowired
    private SerieService service;

    @GetMapping("/frases")
    public SerieDTO exibeFrase(){
        return service.exibeFrase();
    }
}