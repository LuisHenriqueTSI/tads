package br.ifsul.edu.cstsi.luishenrique_tads.api.ingresso;

import br.ifsul.edu.cstsi.luishenrique_tads.api.sessao.SessaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/ingressos")
public class IngressoController {

    private final IngressoRepository ingressoRepository;
    private final SessaoRepository sessaoRepository;

    // Construtor manual para inicializar os repositórios
    public IngressoController(IngressoRepository ingressoRepository, SessaoRepository sessaoRepository) {
        this.ingressoRepository = ingressoRepository;
        this.sessaoRepository = sessaoRepository;
    }

    // ========== DTOs ==========
    public record IngressoDTOPost(Integer tipo, Long sessaoId) {}
    public record IngressoDTOPut(Integer tipo) {}
    public record IngressoResponse(
            Long id,
            String tipoDescricao,
            LocalDateTime dataCompra,
            SessaoResponse sessao
    ) {
        public record SessaoResponse(Long id, String tituloFilme) {}
    }

    // ========== CRUD ==========
    @PostMapping
    public ResponseEntity<IngressoResponse> criar(
            @RequestBody IngressoDTOPost dto,
            UriComponentsBuilder uriBuilder) {

        if (dto.tipo() == null || dto.sessaoId() == null) {
            throw new RuntimeException("Campos obrigatórios não informados");
        }
        if (dto.tipo() != 1 && dto.tipo() != 2) {
            throw new RuntimeException("Tipo deve ser 1 (Inteira) ou 2 (Meia)");
        }

        var ingresso = new Ingresso();
        ingresso.setTipo(dto.tipo());
        ingresso.setSessao(
                sessaoRepository.findById(dto.sessaoId())
                        .orElseThrow(() -> new RuntimeException("Sessão não encontrada"))
        );

        var salvo = ingressoRepository.save(ingresso);
        URI uri = uriBuilder.path("/api/ingressos/{id}").buildAndExpand(salvo.getId()).toUri();

        return ResponseEntity.created(uri).body(toResponse(salvo));
    }

    @GetMapping
    public ResponseEntity<List<IngressoResponse>> listarTodos() {
        return ResponseEntity.ok(
                ingressoRepository.findAll().stream()
                        .map(this::toResponse)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngressoResponse> buscarPorId(@PathVariable Long id) {
        return ingressoRepository.findById(id)
                .map(ingresso -> ResponseEntity.ok(toResponse(ingresso)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngressoResponse> atualizar(
            @PathVariable Long id,
            @RequestBody IngressoDTOPut dto) {

        if (dto.tipo() == null) {
            throw new RuntimeException("Tipo não informado");
        }
        if (dto.tipo() != 1 && dto.tipo() != 2) {
            throw new RuntimeException("Tipo deve ser 1 (Inteira) ou 2 (Meia)");
        }

        var ingresso = ingressoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingresso não encontrado"));

        ingresso.setTipo(dto.tipo());
        return ResponseEntity.ok(toResponse(ingressoRepository.save(ingresso)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!ingressoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ingressoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ========== Método auxiliar ==========
    private IngressoResponse toResponse(Ingresso ingresso) {
        return new IngressoResponse(
                ingresso.getId(),
                ingresso.getTipoDescricao(),
                ingresso.getDataCompra(),
                new IngressoResponse.SessaoResponse(
                        ingresso.getSessao().getId(),
                        ingresso.getSessao().getFilme().getTitulo()
                )
        );
    }
}
