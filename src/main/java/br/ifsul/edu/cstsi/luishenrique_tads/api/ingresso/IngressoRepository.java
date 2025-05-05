package br.ifsul.edu.cstsi.luishenrique_tads.api.ingresso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IngressoRepository extends JpaRepository<Ingresso, Long> {

    // Busca ingressos por tipo
    List<Ingresso> findByTipo(Integer tipo);

    // Busca ingressos vendidos em uma data específica
    @Query("SELECT i FROM Ingresso i WHERE DATE(i.dataCompra) = :data")
    List<Ingresso> findByDataCompra(@Param("data") LocalDate data);

    // Contagem de ingressos por sessão
    @Query("SELECT COUNT(i) FROM Ingresso i WHERE i.sessao.id = :sessaoId")
    Long countBySessaoId(@Param("sessaoId") Long sessaoId);
}