package br.ifsul.edu.cstsi.luishenrique_tads.api.sessao;

import br.ifsul.edu.cstsi.luishenrique_tads.api.filme.Filme;
import br.ifsul.edu.cstsi.luishenrique_tads.api.sala.Sala;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Sessao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dtSessao;

    @Column(nullable = false)
    private LocalTime horSessao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorInteira;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorMeia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filme_id", nullable = false)
    private Filme filme;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;

    // Construtor padrão (equivalente ao @NoArgsConstructor)
    public Sessao() {
    }

    // Construtor com todos os campos (equivalente ao @AllArgsConstructor)
    public Sessao(Long id, LocalDate dtSessao, LocalTime horSessao, BigDecimal valorInteira, BigDecimal valorMeia, Filme filme, Sala sala) {
        this.id = id;
        this.dtSessao = dtSessao;
        this.horSessao = horSessao;
        this.valorInteira = valorInteira;
        this.valorMeia = valorMeia;
        this.filme = filme;
        this.sala = sala;
    }

    // Getters e Setters (manuais)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDtSessao() {
        return dtSessao;
    }

    public void setDtSessao(LocalDate dtSessao) {
        this.dtSessao = dtSessao;
    }

    public LocalTime getHorSessao() {
        return horSessao;
    }

    public void setHorSessao(LocalTime horSessao) {
        this.horSessao = horSessao;
    }

    public BigDecimal getValorInteira() {
        return valorInteira;
    }

    public void setValorInteira(BigDecimal valorInteira) {
        this.valorInteira = valorInteira;
    }

    public BigDecimal getValorMeia() {
        return valorMeia;
    }

    public void setValorMeia(BigDecimal valorMeia) {
        this.valorMeia = valorMeia;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}