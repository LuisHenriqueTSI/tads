package cstsi.ifsul.edu.br.luishenrique_tads.controller;

import cstsi.ifsul.edu.br.luishenrique_tads.model.Filme;
import cstsi.ifsul.edu.br.luishenrique_tads.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository filmeRepository;

    @GetMapping
    public List<Filme> listarFilmes() {
        return filmeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Filme buscarFilme(@PathVariable Long id) {
        return filmeRepository.findById(id).orElseThrow(() -> new RuntimeException("Filme não encontrado"));
    }

    @PostMapping
    public Filme criarFilme(@RequestBody Filme filme) {
        return filmeRepository.save(filme);
    }

    @PutMapping("/{id}")
    public Filme atualizarFilme(@PathVariable Long id, @RequestBody Filme filmeAtualizado) {
        Filme filme = filmeRepository.findById(id).orElseThrow(() -> new RuntimeException("Filme não encontrado"));
        filme.setTitulo(filmeAtualizado.getTitulo());
        filme.setDuracao(filmeAtualizado.getDuracao());
        return filmeRepository.save(filme);
    }

    @DeleteMapping("/{id}")
    public void deletarFilme(@PathVariable Long id) {
        filmeRepository.deleteById(id);
    }
}
