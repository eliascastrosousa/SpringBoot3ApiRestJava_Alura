package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.model.*;
import br.com.alura.screenmatch.repository.EpisodioRepository;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;
    @Autowired
    private EpisodioRepository episodioRepository;
    public List<DadosDetalhamentoSerie> obterSeries(){
        return converteDados(serieRepository.findAll());
    }
    public List<DadosDetalhamentoSerie> obterTop5Series(){
        return converteDados(serieRepository.findTop5ByOrderByAvaliacaoDesc());
    }
    public List<DadosDetalhamentoSerie> obterLancamentos() {
        return converteDados(serieRepository.encontrarEpisodiosMaisRecentes());
    }
    public DadosDetalhamentoSerie detalharSerie(Long id){
        var serie = serieRepository.findById(id);
        return serie.map(DadosDetalhamentoSerie::new).orElse(null);

    }

    public List<DadosDetalhamentoSerie> converteDados(List<Serie> series){
        return series.stream().map(DadosDetalhamentoSerie::new).collect(Collectors.toList());
    }

    public List<EpisodioDTO> obterTodasTemporadas(Long id) {
        var serie = serieRepository.findById(id);
        if (serie.isPresent()) {
            Serie s = serie.get();
            return s.getEpisodios().stream().map(EpisodioDTO::new).collect(Collectors.toList());
        }
        return null;

    }

    public List<EpisodioDTO> obterTemporadasPorNumero(Long id, Long numero) {
        return serieRepository.obterEpisodiosPorTemporada(id, numero).stream()
                .map(EpisodioDTO::new).collect(Collectors.toList());
    }

    public List<DadosDetalhamentoSerie> obterSeriesPorCategoria(String categoria) {
        Categoria tipo = Categoria.fromStringPortugues(categoria);
        return converteDados(serieRepository.findByGenero(tipo));
    }

    public List<EpisodioDTO> obterTopEpisodios(Long id) {
        var serie = serieRepository.findById(id);
        if (serie.isPresent()) {
            Serie s = serie.get();
            var topEpisodios = episodioRepository.topEpisodiosPorSerie(s);
            return topEpisodios.stream().map(EpisodioDTO::new).collect(Collectors.toList());

        }
        return null;
    }
}
