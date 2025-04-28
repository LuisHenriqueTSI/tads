package cstsi.ifsul.edu.br.luishenrique_tads.repository;

import cstsi.ifsul.edu.br.luishenrique_tads.model.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Long> {
}
