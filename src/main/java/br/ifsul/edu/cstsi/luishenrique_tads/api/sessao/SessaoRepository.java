package br.ifsul.edu.cstsi.luishenrique_tads.api.sessao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {
    List<Sessao> findByDtSessao(LocalDate data);

    @Query("SELECT s FROM Sessao s WHERE s.filme.id = :filmeId")
    List<Sessao> findByFilmeId(@Param("filmeId") Long filmeId);

    @Query("SELECT s FROM Sessao s WHERE s.dtSessao >= CURRENT_DATE")
    List<Sessao> findSessoesFuturas();
}