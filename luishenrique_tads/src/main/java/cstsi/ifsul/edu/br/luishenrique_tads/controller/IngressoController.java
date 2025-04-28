package cstsi.ifsul.edu.br.luishenrique_tads.controller;

import cstsi.ifsul.edu.br.luishenrique_tads.model.Ingresso;
import cstsi.ifsul.edu.br.luishenrique_tads.repository.IngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingressos")
public class IngressoController {

    @Autowired
    private IngressoRepository ingressoRepository;

    @GetMapping
    public List<Ingresso> listarIngressos() {
        return ingressoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Ingresso buscarIngresso(@PathVariable Long id) {
        return ingressoRepository.findById(id).orElseThrow(() -> new RuntimeException("Ingresso não encontrado"));
    }

    @PostMapping
    public Ingresso criarIngresso(@RequestBody Ingresso ingresso) {
        return ingressoRepository.save(ingresso);
    }

    @PutMapping("/{id}")
    public Ingresso atualizarIngresso(@PathVariable Long id, @RequestBody Ingresso ingressoAtualizado) {
        Ingresso ingresso = ingressoRepository.findById(id).orElseThrow(() -> new RuntimeException("Ingresso não encontrado"));
        ingresso.setTipo(ingressoAtualizado.getTipo());
        ingresso.setSessao(ingressoAtualizado.getSessao());
        return ingressoRepository.save(ingresso);
    }

    @DeleteMapping("/{id}")
    public void deletarIngresso(@PathVariable Long id) {
        ingressoRepository.deleteById(id);
    }
}
