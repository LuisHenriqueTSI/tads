package br.ifsul.edu.cstsi.luishenrique_tads.api.sessao;

import br.ifsul.edu.cstsi.luishenrique_tads.api.filme.Filme;
import br.ifsul.edu.cstsi.luishenrique_tads.api.sala.Sala;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    // Métodoo auxiliar para descrição completa
    public String getDescricaoCompleta() {
        return filme.getTitulo() + " - " + dtSessao + " " + horSessao;
    }
}