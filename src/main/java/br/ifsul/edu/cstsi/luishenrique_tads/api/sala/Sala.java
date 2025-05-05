package br.ifsul.edu.cstsi.luishenrique_tads.api.sala;

import br.ifsul.edu.cstsi.luishenrique_tads.api.sessao.Sessao;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
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
    @ToString.Exclude
    private List<Sessao> sessoes;

    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;
}
