package cstsi.ifsul.edu.br.luishenrique_tads.repository;

import cstsi.ifsul.edu.br.luishenrique_tads.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {
}
