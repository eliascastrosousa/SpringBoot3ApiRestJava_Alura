package br.com.alura.screenMatchDesafio.repository;

import br.com.alura.screenMatchDesafio.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    @Query("SELECT s FROM Serie s order by function('RANDOM') LIMIT 1")
    Optional<Serie> findAnyOne();
}
