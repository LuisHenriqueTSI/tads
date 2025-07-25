package br.ifsul.edu.cstsi.luishenrique_tads.api.filme;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/filmes")
//@PreAuthorize("hasRole('ADMIN')")
public class FilmeController {

    private final FilmeRepository repository;

    // Injeção de dependência via construtor
    public FilmeController(FilmeRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<FilmeDTOResponse> criar(
            @RequestBody @Valid FilmeDTOPost dto,
            UriComponentsBuilder uriBuilder) {

        // Conversão do DTO para entidade
        Filme filme = new Filme();
        filme.setTitulo(dto.titulo());
        filme.setDuracao(dto.duracao());
        filme.setGenero(dto.genero());
        filme.setClassificacao(dto.classificacao());
        filme.setDiretor(dto.diretor());

        // Validação adicional se necessário
        validarFilme(filme);

        Filme salvo = repository.save(filme);
        URI uri = uriBuilder.path("/api/filmes/{id}").buildAndExpand(salvo.getId()).toUri();

        return ResponseEntity.created(uri).body(toResponse(salvo));
    }

    @GetMapping
    public ResponseEntity<List<FilmeDTOResponse>> listarTodos() {
        List<Filme> filmes = repository.findAll();
        return ResponseEntity.ok(
                filmes.stream()
                        .map(this::toResponse)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeDTOResponse> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmeDTOResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid FilmeDTOPut dto) {

        Filme filme = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));

        // Atualiza apenas os campos que foram fornecidos
        if (dto.titulo() != null) filme.setTitulo(dto.titulo());
        if (dto.duracao() != null) filme.setDuracao(dto.duracao());
        if (dto.genero() != null) filme.setGenero(dto.genero());
        if (dto.classificacao() != null) filme.setClassificacao(dto.classificacao());
        if (dto.diretor() != null) filme.setDiretor(dto.diretor());

        // Validação adicional se necessário
        validarFilme(filme);

        Filme atualizado = repository.save(filme);
        return ResponseEntity.ok(toResponse(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<FilmeDTOResponse>> buscarPorTitulo(
            @RequestParam @NotBlank String titulo) {
        return ResponseEntity.ok(
                repository.findByTituloContainingIgnoreCase(titulo).stream()
                        .map(this::toResponse)
                        .toList()
        );
    }

    @GetMapping("/por-genero/{genero}")
    public ResponseEntity<List<FilmeDTOResponse>> porGenero(
            @PathVariable @NotBlank String genero) {
        return ResponseEntity.ok(
                repository.findByGenero(genero).stream()
                        .map(this::toResponse)
                        .toList()
        );
    }

    // Método auxiliar para validações adicionais
    private void validarFilme(Filme filme) {
        // Exemplo de validação adicional
        if (filme.getTitulo().length() < 2) {
            throw new IllegalArgumentException("Título muito curto");
        }
        // Adicione outras validações conforme necessário
    }

    // Método auxiliar para conversão para DTO de resposta
    private FilmeDTOResponse toResponse(Filme filme) {
        return new FilmeDTOResponse(
                filme.getId(),
                filme.getTitulo(),
                FilmeDTOResponse.formatDuration(filme.getDuracao()),
                filme.getGenero(),
                filme.getClassificacao(),
                filme.getDiretor(),
                filme.getDataCriacao(),
                filme.getSessoes() != null ? filme.getSessoes().size() : 0
        );
    }
}