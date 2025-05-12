package br.ifsul.edu.cstsi.luishenrique_tads.api.ingresso;

import br.ifsul.edu.cstsi.luishenrique_tads.api.sessao.Sessao;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
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

    // Construtor padrão
    public Ingresso() {
    }

    // Construtor com todos os campos
    public Ingresso(Long id, Integer tipo, LocalDateTime dataCompra, Sessao sessao) {
        this.id = id;
        this.tipo = tipo;
        this.dataCompra = dataCompra;
        this.sessao = sessao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    // Método auxiliar para descrição do tipo
    public String getTipoDescricao() {
        return switch (tipo) {
            case 1 -> "Inteira";
            case 2 -> "Meia";
            default -> "Desconhecido";
        };
    }

    // toString opcional (sem incluir sessao para evitar lazy loading)
    @Override
    public String toString() {
        return "Ingresso{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", dataCompra=" + dataCompra +
                '}';
    }
}
