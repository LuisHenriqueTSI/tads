package cstsi.ifsul.edu.br.luishenrique_tads.repository;

import cstsi.ifsul.edu.br.luishenrique_tads.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
}

