package cstsi.ifsul.edu.br.luishenrique_tads.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int tipo; // 0 - Inteira | 1 - Meia

    @ManyToOne
    @JoinColumn(name = "sessao_id")
    private Sessao sessao;
}
