package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.DadosDetalhamentoSerie;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    boolean existsByTitulo(String titulo);

    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, Double avaliacao);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findByGenero(Categoria categoria);

    List<Serie> findBytotalTemporadasLessThanEqual(int qtd);

    @Query(value = "select s from Serie s WHERE s.totalTemporadas <= :qtdTemporadas AND s.avaliacao >= :avaliacao")
    List<Serie> seriesPorTemporadaEAvaliacao(Integer qtdTemporadas, Double avaliacao);

    List<Serie> findTop5ByOrderByEpisodiosDataLancamentoDesc();

    @Query("SELECT s FROM Serie s " +
            "JOIN s.episodios e " +
            "GROUP BY s " +
            "ORDER BY MAX(e.dataLancamento) DESC LIMIT 5")
    List<Serie> encontrarEpisodiosMaisRecentes();

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s.id = :id AND e.numTemporada = :numero")
    List<Episodio> obterEpisodiosPorTemporada(Long id, Long numero);

}
