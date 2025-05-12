package br.ifsul.edu.cstsi.luishenrique_tads.api.sala;

import br.ifsul.edu.cstsi.luishenrique_tads.api.sessao.Sessao;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer numero;

    @Column(nullable = false)
    private Integer capacidade;

    private String descricao;

    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sessao> sessoes;

    // --- Construtores ---
    public Sala() {
        // Construtor padrão (obrigatório para JPA)
    }

    // Construtor com campos básicos (opcional)
    public Sala(Integer numero, Integer capacidade, String descricao) {
        this.numero = numero;
        this.capacidade = capacidade;
        this.descricao = descricao;
    }

    // --- Getters e Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Sessao> getSessoes() {
        return sessoes;
    }

    public void setSessoes(List<Sessao> sessoes) {
        this.sessoes = sessoes;
    }

    // --- Métodos adicionais (opcionais) ---
    @Override
    public String toString() {
        return "Sala{" +
                "id=" + id +
                ", numero=" + numero +
                ", capacidade=" + capacidade +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}