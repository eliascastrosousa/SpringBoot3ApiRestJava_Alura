package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EpisodioRepository extends JpaRepository<Episodio, Long> {
    boolean existsBySerieId(Long id);

    List<Episodio> findAllBySerieId(Long id);

    @Query("select e FROM Episodio e WHERE e.titulo ILIKE %:nomeEpisodio%")
    List<Episodio> findAllByTituloContains(String nomeEpisodio);

    @Query("select e FROM Episodio e WHERE e.serie = :serie ORDER BY e.avaliacao DESC LIMIT 5")
    List<Episodio> topEpisodiosPorSerie(Serie serie);

    @Query("select e FROM Episodio e WHERE e.serie = :serie AND YEAR(e.dataLancamento) >= :anoLancamento ")
    List<Episodio> episodiosPorSerieEData(Serie serie, int anoLancamento);
}
