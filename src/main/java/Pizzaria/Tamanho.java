package Pizzaria;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Tamanho {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private int tamanho;

    @OneToMany(mappedBy = "tamanho")
    private List<Pizza> pizzas;

    public Tamanho () {};

    public Tamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }
}
