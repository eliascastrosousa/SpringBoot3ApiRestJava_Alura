package service;

import model.Serie;
import model.SerieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.SerieRepository;

import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

//    Random gerador = new Random();
//
//    public SerieDTO exibeFrase(){
//        System.out.println(gerador.nextInt(3));
//        Integer numeroAleatorio = gerador.nextInt(3);
//
//        Optional<Serie> serie = serieRepository.findById(Long.valueOf(numeroAleatorio));
//
//        if (serie.isPresent()) {
//            var serieEncontrada = serie.get();
//            return new SerieDTO(serieEncontrada);
//        }
//        return null;
//    }






}
