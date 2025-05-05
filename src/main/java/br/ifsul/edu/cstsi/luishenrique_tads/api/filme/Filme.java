package br.ifsul.edu.cstsi.luishenrique_tads.api.filme;

import br.ifsul.edu.cstsi.luishenrique_tads.api.sessao.Sessao;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"sessoes"})
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false)
    private Duration duracao;

    @Column(length = 50)
    private String genero;

    @Column(length = 20)
    private String classificacao;

    @Column(length = 100)
    private String diretor;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;

    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL)
    private List<Sessao> sessoes;
}