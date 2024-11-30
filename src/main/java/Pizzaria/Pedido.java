package Pizzaria;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<Pizza> pizzas;

    public Pedido(Cliente cliente, List<Pizza> pizzas) {
        this.cliente = cliente;
        this.pizzas = pizzas;
    }

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pedido () {}

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}
