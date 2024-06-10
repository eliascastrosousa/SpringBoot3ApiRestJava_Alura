package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.model.*;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private SerieService service;

    @GetMapping
    public List<DadosDetalhamentoSerie> obterSeries(){
        return service.obterSeries();
    }

    @GetMapping("/top5")
    public List<DadosDetalhamentoSerie> obterTop5Series(){
        return service.obterTop5Series();
    }

    @GetMapping("/lancamentos")
    public List<DadosDetalhamentoSerie> obterLancamentos(){
        return service.obterLancamentos();
    }

    @GetMapping("/{id}")
    public DadosDetalhamentoSerie detalharSerie(@PathVariable Long id) {
        return service.detalharSerie(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> obterTodasTemporadas(@PathVariable Long id) {
        return service.obterTodasTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{numero}")
    public List<EpisodioDTO> obterTemporadasPorNumero(@PathVariable Long id, @PathVariable Long numero){
        return service.obterTemporadasPorNumero(id, numero);
    }

    @GetMapping("/categoria/{categoria}")
    public List<DadosDetalhamentoSerie> obterSeriesPorCategoria(@PathVariable String categoria){
        return service.obterSeriesPorCategoria(categoria);
    }

    @GetMapping("/{id}/temporadas/top")
    public List<EpisodioDTO> obterTopEpisodios(@PathVariable Long id){
        return service.obterTopEpisodios(id);
    }

}
