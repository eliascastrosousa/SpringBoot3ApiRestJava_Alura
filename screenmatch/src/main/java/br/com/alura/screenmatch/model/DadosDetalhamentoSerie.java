package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DadosDetalhamentoSerie(Long id,
                                     String titulo,
                                     Integer totalTemporadas,

                                     String sinopse,
                                     Double avaliacao,
                                     String poster,
                                     String atores

                                     ) {
    public DadosDetalhamentoSerie(Serie serie){
        this(serie.getId(),
                serie.getTitulo(),
                serie.getTotalTemporadas(),
                serie.getSinopse(),
                serie.getAvaliacao(),
                serie.getPoster(),
                serie.getAtores()
                );
    }

}
