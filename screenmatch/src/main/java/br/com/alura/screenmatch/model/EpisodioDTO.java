package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodioDTO(@JsonAlias("Title") String titulo,
                          @JsonAlias("numTemporada") Integer temporada,
                          @JsonAlias("Episode") Integer numeroEpisodio) {
    public EpisodioDTO(Episodio episodio){
        this(
                episodio.getTitulo(),
                episodio.getNumTemporada(),
                episodio.getNumEpisodio());

    }
}
