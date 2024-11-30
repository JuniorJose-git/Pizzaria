package Pizzaria;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Borda {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String sabor;

    @Column(nullable = false)
    private Double preco;

    @OneToMany(mappedBy = "borda")
    private List<Pizza> pizzas;

    public Borda () {}

    public Borda(String sabor, Double preco) {
        this.sabor = sabor;
        this.preco = preco;
    }

    public Borda(String sabor, Integer preco) {
        this.sabor = sabor;
        this.preco = preco + .0;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void setPreco(Integer preco) {
        this.preco = preco + .0;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }
}
