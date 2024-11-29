package Pizzaria;

import jakarta.persistence.*;

@Entity
public class Tamanho {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private int tamanho;

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
}
