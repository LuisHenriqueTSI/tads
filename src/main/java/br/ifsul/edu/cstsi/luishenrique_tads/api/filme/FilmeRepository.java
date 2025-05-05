package br.ifsul.edu.cstsi.luishenrique_tads.api.filme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
    List<Filme> findByTituloContainingIgnoreCase(String titulo);

    @Query("SELECT f FROM Filme f WHERE f.genero = :genero ORDER BY f.titulo")
    List<Filme> findByGenero(String genero);

    List<Filme> findByClassificacao(String classificacao);
}