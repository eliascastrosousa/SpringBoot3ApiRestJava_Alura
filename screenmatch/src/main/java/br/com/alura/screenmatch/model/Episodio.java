package br.com.alura.screenmatch.model;

import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Episodio {
    private String titulo;
    private Integer numTemporada;
    private Integer numEpisodio;
    private double avaliacao;
    private LocalDate dataLancamento;


    public Episodio(Integer numTemporada, DadosEpisodio dadosEpisodio) {
        this.titulo = dadosEpisodio.titulo();
        this.numEpisodio = dadosEpisodio.episodio();
        this.numTemporada = numTemporada;
        try {
            this.avaliacao = Double.parseDouble(dadosEpisodio.avaliacao());
        } catch (NumberFormatException ex){
            this.avaliacao = 0.0;
        }
        try{
            this.dataLancamento = LocalDate.parse(dadosEpisodio.dataLancamento());
        } catch (DateTimeParseException ex){
            this.dataLancamento = null;
        }
    }

}
