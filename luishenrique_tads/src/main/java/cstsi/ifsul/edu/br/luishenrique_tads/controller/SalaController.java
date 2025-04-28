package cstsi.ifsul.edu.br.luishenrique_tads.controller;

import cstsi.ifsul.edu.br.luishenrique_tads.model.Sala;
import cstsi.ifsul.edu.br.luishenrique_tads.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    @Autowired
    private SalaRepository salaRepository;

    @GetMapping
    public List<Sala> listarSalas() {
        return salaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Sala buscarSala(@PathVariable Long id) {
        return salaRepository.findById(id).orElseThrow(() -> new RuntimeException("Sala não encontrada"));
    }

    @PostMapping
    public Sala criarSala(@RequestBody Sala sala) {
        return salaRepository.save(sala);
    }

    @PutMapping("/{id}")
    public Sala atualizarSala(@PathVariable Long id, @RequestBody Sala salaAtualizada) {
        Sala sala = salaRepository.findById(id).orElseThrow(() -> new RuntimeException("Sala não encontrada"));
        sala.setNrosala(salaAtualizada.getNrosala());
        sala.setCapacidade(salaAtualizada.getCapacidade());
        return salaRepository.save(sala);
    }

    @DeleteMapping("/{id}")
    public void deletarSala(@PathVariable Long id) {
        salaRepository.deleteById(id);
    }
}

