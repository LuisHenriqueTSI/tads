package br.ifsul.edu.cstsi.luishenrique_tads.api.ingresso;

import br.ifsul.edu.cstsi.luishenrique_tads.api.sessao.Sessao;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingresso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer tipo; // 1 = Inteira, 2 = Meia

    @CreationTimestamp
    private LocalDateTime dataCompra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sessao_id", nullable = false)
    private Sessao sessao;

    // Método auxiliar para descrição do tipo
    public String getTipoDescricao() {
        return switch (tipo) {
            case 1 -> "Inteira";
            case 2 -> "Meia";
            default -> "Desconhecido";
        };
    }
}