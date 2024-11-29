package Pizzaria;

import jakarta.persistence.*;
import org.hibernate.annotations.Check;

@Entity
@Check(constraints = "tipo = 'doce' OR tipo = 'salgado'")
public class Sabor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private String descricao;


    @Column(nullable = false, length = 7)
    private String tipo;


    public Sabor(String nome, Double preco, String descricao, String tipo) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public Sabor () {};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
