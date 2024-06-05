package br.com.alura.screenmatch.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(@JsonAlias("Title") String titulo,
                            @JsonAlias("Episode") Integer episodio,
                            @JsonAlias("imdbRating") String avaliacao,
                            @JsonAlias("Released") String dataLancamento
) {
    @Override
    public String toString() {
        return "\nDados dos episódios: " +
                "\nTitulo: " + titulo +
                "\nEpisódio: " + episodio +
                "\nAvaliacão: " + avaliacao +
                "\nData do lançamento: " + dataLancamento + "\n" ;
    }
}
