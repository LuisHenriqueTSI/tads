package br.ifsul.edu.cstsi.luishenrique_tads.api.sala;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/salas")
public class SalaController {

    private final SalaRepository repository;

    // Construtor manual para injeção de dependência
    public SalaController(SalaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<SalaDTOResponse> create(
            @RequestBody SalaDTOPost dto,
            UriComponentsBuilder uriBuilder) {

        // Validação manual
        if (dto.numero() == null || dto.capacidade() == null) {
            throw new RuntimeException("Número e capacidade são obrigatórios");
        }

        if (repository.existsByNumero(dto.numero())) {
            throw new RuntimeException("Número de sala já existe");
        }

        Sala sala = new Sala();
        sala.setNumero(dto.numero());
        sala.setCapacidade(dto.capacidade());
        sala.setDescricao(dto.descricao());

        Sala saved = repository.save(sala);

        URI uri = uriBuilder.path("/api/salas/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaDTOResponse> atualizarSala(
            @PathVariable Long id,
            @RequestBody SalaDTOPut dto) {

        Sala sala = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));

        // Atualiza apenas os campos informados
        if (dto.numero() != null) {
            if (repository.existsByNumeroAndIdNot(dto.numero(), id)) {
                throw new RuntimeException("Número de sala já está em uso");
            }
            sala.setNumero(dto.numero());
        }
        if (dto.capacidade() != null) sala.setCapacidade(dto.capacidade());
        if (dto.descricao() != null) sala.setDescricao(dto.descricao());

        Sala atualizada = repository.save(sala);
        return ResponseEntity.ok(toDTO(atualizada));
    }

    private SalaDTOResponse toDTO(Sala sala) {
        return new SalaDTOResponse(
                sala.getId(),
                sala.getNumero(),
                sala.getCapacidade(),
                sala.getDescricao()
        );
    }
}
