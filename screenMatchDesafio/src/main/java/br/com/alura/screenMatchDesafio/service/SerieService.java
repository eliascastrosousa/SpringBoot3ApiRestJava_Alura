package br.com.alura.screenMatchDesafio.service;

import br.com.alura.screenMatchDesafio.model.Serie;
import br.com.alura.screenMatchDesafio.model.SerieDTO;
import br.com.alura.screenMatchDesafio.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    Random gerador = new Random();

    public SerieDTO exibeFrase(){

        Optional<Serie> serie = serieRepository.findAnyOne();

        if (serie.isPresent()) {
            var serieEncontrada = serie.get();
            return new SerieDTO(serieEncontrada);
        }
        return null;
    }
}