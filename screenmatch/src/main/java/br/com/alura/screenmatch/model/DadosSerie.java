package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DadosSerie(@JsonAlias("Title") String titulo,
                         @JsonAlias("totalSeasons") Integer totalTemporadas,
                         @JsonAlias("Genre") String genero,
                         @JsonAlias("imdbRating") String avaliacao,
                         @JsonAlias("Actors") String atores,
                         @JsonAlias("Plot") String sinopse,
                         @JsonAlias("Poster") String poster

                         ) {


    @Override
    public String toString() {
        return
                "Titulo: "+ titulo +
                "\nTotal de Temporadas: " + totalTemporadas +
                "\nGenero: " + genero +
                "\nAtores: " + atores +
                "\nSinopse: " + sinopse +
                "\nPoster: " + poster +
                "\nAvaliacao: " + avaliacao + "\n" ;
    }

//    public DadosSerie(Serie serie){
//        this(
//                serie.getTitulo(),
//                serie.getTotalTemporadas(),
//                serie.getGenero(),
//                serie.getAtores(),
//                serie.getSinopse(),
//                serie.getPoster(),
//                serie.getAvaliacao()
//        );
//    }
}
