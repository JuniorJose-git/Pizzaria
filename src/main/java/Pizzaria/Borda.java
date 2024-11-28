package Pizzaria;

import jakarta.persistence.*;

@Entity
public class Borda {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String sabor;

    @Column(nullable = false)
    private Integer valor;

    public Borda () {}

    public Borda(String sabor, Integer valor) {
        this.sabor = sabor;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }
}
