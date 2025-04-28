package cstsi.ifsul.edu.br.luishenrique_tads.repository;

import cstsi.ifsul.edu.br.luishenrique_tads.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {
}
