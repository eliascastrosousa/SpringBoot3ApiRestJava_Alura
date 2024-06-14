package br.com.alura.screenMatchDesafio.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SerieDTO(
        String titulo,
        String frase,
        String personagem,
        String poster) {

    public SerieDTO(Serie serie){
        this(
                serie.getTitulo(), serie.getFrase(), serie.getPersonagem(), serie.getPoster()
        );
    }

}