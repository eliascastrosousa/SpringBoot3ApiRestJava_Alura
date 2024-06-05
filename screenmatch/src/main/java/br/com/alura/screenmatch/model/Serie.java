package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.service.ConsultaChatGPT;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;


import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
@Getter
@Setter
@Entity
@Table(name = "series")
@AllArgsConstructor
@NoArgsConstructor
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private Integer totalTemporadas = 1;

    @Enumerated(EnumType.STRING)
    private Categoria genero;

    private Double avaliacao;
    private String atores;
    private String sinopse;
    private String poster;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episodio> episodios = new ArrayList<>();

    public void setEpisodios(List<Episodio> episodios) {
        episodios.forEach(e -> e.setSerie(this));
        this.episodios = episodios;
    }

    public Serie(DadosSerie dados){
        this.titulo = dados.titulo();
        this.totalTemporadas = dados.totalTemporadas();
        this.avaliacao = OptionalDouble.of(Double.valueOf(dados.avaliacao())).orElse(0);
        this.atores = dados.atores();
        this.sinopse = dados.sinopse();
        //this.sinopse = ConsultaChatGPT.obterTraducao(dados.sinopse());
        this.poster = dados.poster();
        this.genero = Categoria.fromString(dados.genero().split(",")[0].trim()); //metodo trim retira todos os espaços em branco
    }
    @Override
    public String toString() {
        return
                "\n############################################"+
                "\nTitulo: "+ titulo +
                        "\nGenero: " + genero +
                        "\nTotal de Temporadas: " + totalTemporadas +
                        "\nAtores: " + atores +
                        "\nSinopse: " + sinopse +
                        "\nPoster: " + poster +
                        "\nAvaliacao: " + avaliacao +
                        "\nEpisodios: \n" + episodios +
                "\n\n############################################";


    }

    public void exibeSerie(){
        System.out.println(
                "\nGenero: " + genero +
                "\nTitulo: "+ titulo +
                "\nTotal de Temporadas: " + totalTemporadas);


    }

    public void exibeSerieDetalhada() {
        System.out.println("\nTitulo: "+ titulo +
                "\nGenero: " + genero +
                "\nTotal de Temporadas: " + totalTemporadas +
                "\nAtores: " + atores +
                "\nSinopse: " + sinopse +
                "\nPoster: " + poster +
                "\nAvaliacao: " + avaliacao);
    }
}
