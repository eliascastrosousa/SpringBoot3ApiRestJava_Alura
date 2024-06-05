package br.com.alura.screenmatch.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "episodios")
public class Episodio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private Integer numTemporada;
    private Integer numEpisodio;
    private Double avaliacao;
    private LocalDate dataLancamento;
    @ManyToOne
    private Serie serie;

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
    @Override
    public String toString() {
        return
                "\nSerie: " + serie.getTitulo() +
                "\nTitulo: " + titulo  +
                "\nTemporada: " + numTemporada +
                "\nNumero do episodio: " + numEpisodio +
                "\nAvaliacao: " + avaliacao +
                "\nData Lancamento: " + dataLancamento ;
    }


}
