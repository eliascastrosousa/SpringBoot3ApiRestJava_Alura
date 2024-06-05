package br.com.alura.screenmatch.util;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class Funcoes_Series {
    private Scanner sc = new Scanner(System.in);
//    private ConsumoAPI consumo = new ConsumoAPI();
//    private ConverteDados conversor = new ConverteDados();
//    private final String ENDERECO = "https://www.omdbapi.com/?t=";
//    private final String API_KEY = "&apikey=6585022c";
//
//    private List<DadosSerie> dadosSeries = new ArrayList<>();
//
//
//    public DadosSerie getDadosSerie() throws JsonProcessingException {
//        System.out.println("\nDigite o nome da série para busca: ");
//        var nomeSerie = sc.nextLine();
//        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
//        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
//        return dados;
//    }
//    @Transactional
//    public void buscarSerieWeb() throws JsonProcessingException {
//        DadosSerie dados = getDadosSerie();
//        Serie serie = new Serie(dados);
//        System.out.println("aqui");
//        serieRepository.save(serie);
//        System.out.println("aqui 2");
//        dadosSeries.add(dados);
//        System.out.println(dados);
//    }
//    public void buscarEpisodioPorSerie() throws JsonProcessingException {
//        DadosSerie dadosSerie = getDadosSerie();
//        List<DadosTemporada> temporadas = new ArrayList<>();
//
//        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
//            var json = consumo.obterDados(ENDERECO + dadosSerie.titulo().replace(" ", "+") + "&season=" + i + API_KEY);
//            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
//            temporadas.add(dadosTemporada);
//        }
//        temporadas.forEach(System.out::println);
//    }
//
//
//    public void listarSeriesBuscadas() {
//        List<Serie> series = new ArrayList<>();
//        series = dadosSeries.stream()
//                .map(d -> new Serie(d))
//                .collect(Collectors.toList());
//        series.stream()
//                .sorted(Comparator.comparing(Serie::getGenero))
//
//                .forEach(System.out::println);
//
//    }
}
