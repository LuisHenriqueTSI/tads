package cstsi.ifsul.edu.br.luishenrique_tads.controller;

import cstsi.ifsul.edu.br.luishenrique_tads.model.Sessao;
import cstsi.ifsul.edu.br.luishenrique_tads.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessoes")
public class SessaoController {

    @Autowired
    private SessaoRepository sessaoRepository;

    @GetMapping
    public List<Sessao> listarSessoes() {
        return sessaoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Sessao buscarSessao(@PathVariable Long id) {
        return sessaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Sessão não encontrada"));
    }

    @PostMapping
    public Sessao criarSessao(@RequestBody Sessao sessao) {
        return sessaoRepository.save(sessao);
    }

    @PutMapping("/{id}")
    public Sessao atualizarSessao(@PathVariable Long id, @RequestBody Sessao sessaoAtualizada) {
        Sessao sessao = sessaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Sessão não encontrada"));
        sessao.setDtSessao(sessaoAtualizada.getDtSessao());
        sessao.setHorSessao(sessaoAtualizada.getHorSessao());
        sessao.setValorInteira(sessaoAtualizada.getValorInteira());
        sessao.setValorMeia(sessaoAtualizada.getValorMeia());
        sessao.setEncerrada(sessaoAtualizada.getEncerrada());
        sessao.setSala(sessaoAtualizada.getSala());
        sessao.setFilme(sessaoAtualizada.getFilme());
        return sessaoRepository.save(sessao);
    }

    @DeleteMapping("/{id}")
    public void deletarSessao(@PathVariable Long id) {
        sessaoRepository.deleteById(id);
    }
}
