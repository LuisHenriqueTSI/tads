package br.ifsul.edu.cstsi.luishenrique_tads.api.sala;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalaRepository extends JpaRepository<Sala, Long> {

    // Verifica se já existe uma sala com o mesmo número
    boolean existsByNumero(Integer numero);

    // Verifica se existe outra sala com o mesmo número (excluindo a atual)
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Sala s WHERE s.numero = :numero AND s.id != :id")
    boolean existsByNumeroAndIdNot(@Param("numero") Integer numero, @Param("id") Long id);

    // Consulta para buscar salas com capacidade mínima (exemplo adicional)
    @Query("SELECT s FROM Sala s WHERE s.capacidade >= :capacidadeMinima ORDER BY s.numero ASC")
    List<Sala> findByCapacidadeMinima(@Param("capacidadeMinima") Integer capacidadeMinima);
}