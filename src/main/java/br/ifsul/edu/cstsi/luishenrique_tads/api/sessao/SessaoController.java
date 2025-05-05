package br.ifsul.edu.cstsi.luishenrique_tads.api.sessao;

import br.ifsul.edu.cstsi.luishenrique_tads.api.filme.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sessoes")
@RequiredArgsConstructor
public class SessaoController {

    private final SessaoRepository sessaoRepository;
    private final FilmeRepository filmeRepository;

    @PostMapping
    public ResponseEntity<SessaoDTOResponse> criar(
            @RequestBody SessaoDTOPost dto,
            UriComponentsBuilder uriBuilder) {

        var sessao = new Sessao();
        sessao.setDtSessao(dto.dtSessao());
        sessao.setHorSessao(dto.horSessao());
        sessao.setValorInteira(dto.valorInteira());
        sessao.setValorMeia(dto.valorMeia());
        sessao.setFilme(filmeRepository.findById(dto.filmeId())
                .orElseThrow(() -> new RuntimeException("Filme não encontrado")));

        var salva = sessaoRepository.save(sessao);
        URI uri = uriBuilder.path("/api/sessoes/{id}").buildAndExpand(salva.getId()).toUri();

        return ResponseEntity.created(uri).body(toResponse(salva));
    }

    @GetMapping
    public ResponseEntity<List<SessaoDTOResponse>> listarTodos() {
        return ResponseEntity.ok(
                sessaoRepository.findAll().stream()
                        .map(this::toResponse)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessaoDTOResponse> buscarPorId(@PathVariable Long id) {
        return sessaoRepository.findById(id)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessaoDTOResponse> atualizar(
            @PathVariable Long id,
            @RequestBody SessaoDTOPut dto) {

        var sessao = sessaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada"));

        if (dto.dtSessao() != null) sessao.setDtSessao(dto.dtSessao());
        if (dto.horSessao() != null) sessao.setHorSessao(dto.horSessao());
        if (dto.valorInteira() != null) sessao.setValorInteira(dto.valorInteira());
        if (dto.valorMeia() != null) sessao.setValorMeia(dto.valorMeia());

        var atualizada = sessaoRepository.save(sessao);
        return ResponseEntity.ok(toResponse(atualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!sessaoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        sessaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/por-filme/{filmeId}")
    public ResponseEntity<List<SessaoDTOResponse>> porFilme(@PathVariable Long filmeId) {
        return ResponseEntity.ok(
                sessaoRepository.findByFilmeId(filmeId).stream()
                        .map(this::toResponse)
                        .toList()
        );
    }

    @GetMapping("/futuras")
    public ResponseEntity<List<SessaoDTOResponse>> futuras() {
        return ResponseEntity.ok(
                sessaoRepository.findSessoesFuturas().stream()
                        .map(this::toResponse)
                        .toList()
        );
    }

    private SessaoDTOResponse toResponse(Sessao sessao) {
        return new SessaoDTOResponse(
                sessao.getId(),
                sessao.getDtSessao() + " " + sessao.getHorSessao(),
                sessao.getValorInteira(),
                sessao.getValorMeia(),
                new SessaoDTOResponse.FilmeInfo(
                        sessao.getFilme().getId(),
                        sessao.getFilme().getTitulo()
                )
        );
    }
}